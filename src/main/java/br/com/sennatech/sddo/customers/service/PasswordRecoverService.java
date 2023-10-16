package br.com.sennatech.sddo.customers.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.config.Config;
import br.com.sennatech.sddo.customers.exception.InvalidRecoveryHashException;
import br.com.sennatech.sddo.customers.function.RecoveryRequestDTOtoPwdRecoveryReq;
import br.com.sennatech.sddo.customers.model.*;
import br.com.sennatech.sddo.customers.model.dto.*;
import br.com.sennatech.sddo.customers.repository.*;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PasswordRecoverService {

  @Autowired
  private PasswordRecoveryRepository passwordRecoveryRepository;

  @Autowired
  private EmailService emailService;

  @Autowired
  private RecoveryRequestDTOtoPwdRecoveryReq recoveryRequestDTOtoPwdRecoveryReq;

  @Autowired
  private CustomerRepository customerRepository;

  @Transactional
  public void request(Logger logger, RecoveryRequestDTO recoveryRequestDTO) throws IOException, MessagingException {
    String email = recoveryRequestDTO.getEmail();
    logger.info("Checking if customer is existent in repository from email...");
    if (!customerRepository.existsByEmail(email)) throw new EntityNotFoundException("Customer not found");
    logger.info("Customer found, deleting existing requests and saving recovery request temporarily until request is completed or invalid...");
    passwordRecoveryRepository.deleteAllByEmail(email);
    PwdRecoveryReq request = recoveryRequestDTOtoPwdRecoveryReq.apply(recoveryRequestDTO);
    passwordRecoveryRepository.save(request);
    logger.info("Request saved into repository, sending email to customer...");
    emailService.send("Password recovery - Recuperação de senha", Config.EMAIL_FROM, recoveryRequestDTO.getEmail(), request.getHash());
    logger.info("Email sent to customer");
  }

  @Transactional
  public void recover(Logger logger, String hash, RecoveryDTO recoveryDTO) {
    logger.info("Retrieving recovery request from repository...");
    var request =  passwordRecoveryRepository.findById(hash).orElseThrow(() -> new EntityNotFoundException("Password recovery request not found"));
    logger.info("Recovery request retrieved, retrieving customer by email from repository...");
    var customer = customerRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    logger.info("Customer found, all valid, changing password...");
    customer.setPassword(recoveryDTO.getNewPassword());
    logger.info("Password changed, saving into repository...");
    customerRepository.save(customer);
    logger.info("Saved into repository, deleting request from repository aswell...");
    passwordRecoveryRepository.deleteById(hash);
    logger.info("Recovery request deleted");
  }

  public void validate(Logger logger, String hash) {
      logger.info("Retrieving recovery request");
      PwdRecoveryReq request = passwordRecoveryRepository.findById(hash).orElseThrow(() -> new EntityNotFoundException("Password recovery request not found"));
      LocalDateTime timestamp = request.getTimestamp().toLocalDateTime();
      logger.info("Checking recovery request validity");
      if (LocalDateTime.now().minus(Config.RECOVER_EXPIRATION_TIME, ChronoUnit.MINUTES).isAfter(timestamp)) {
        logger.info("Recovery request is expired");
        throw new InvalidRecoveryHashException("Password recovery request is expired");
      } logger.info("Recovery request is valid");
  }
}
 
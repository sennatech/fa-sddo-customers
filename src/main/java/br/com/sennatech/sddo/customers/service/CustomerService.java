package br.com.sennatech.sddo.customers.service;

import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.exception.*;
import br.com.sennatech.sddo.customers.function.*;
import br.com.sennatech.sddo.customers.model.*;
import br.com.sennatech.sddo.customers.model.dto.*;
import br.com.sennatech.sddo.customers.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerDTOToCustomer customerDTOToCustomer;

  @Autowired
  private CustomerUpdateEntity updateCustomer;

  @Autowired
  private CustomerToCustomerDTO customerToCustomerDTO;

  public Customer create(Logger logger, CustomerDTO customerDTO) {
    if (customerRepository.existsById(customerDTO.getDocumentNumber())) throw new DuplicatedEntityException("Customer already exists");
    logger.info("Converting to entity...");
    Customer customer = customerDTOToCustomer.apply(customerDTO);
    logger.info("DTO converted to entity: " + customer);
    logger.info("Saving to repository...");
    customerRepository.save(customer);
    logger.info("Saved, customer created");
    return customer;
  }

  @Transactional
  public void update(Logger logger, CustomerDTO customerDTO) {
    logger.info("Initializing customer update");
    Customer customer = customerRepository.findById(customerDTO.getDocumentNumber()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    logger.info("Customer retrieved with document number: " + customerDTO.getDocumentNumber());
    updateCustomer.accept(customer, customerDTO);
    logger.info("Updating");
    customerRepository.save(customer);
    logger.info("Finalizing customer update");
  }

  public void validate(Logger logger, String hash) {
    logger.info("Validating from hash: " + hash);
    logger.info("Decoding hash...");
    String decodedHash = new String(Base64.getDecoder().decode(hash));
    logger.info("Hash has been decoded: " + decodedHash);
    if (!decodedHash.contains(":")) {
      logger.info("Invalid format in decoded hash");
      throw new CustomerValidationException("Wrong format");
    }
    logger.info("Splitting decoded hash into email and password vars...");
    String[] decodedHashSplitted = decodedHash.split(":");
    String email = decodedHashSplitted[0];
    logger.info("Email: " + email);
    String password = decodedHashSplitted[1];
    logger.info("Password: " + password);
    logger.info("Decoded hash has been splitted");
    logger.info("Retrieving Customer by email...");
    Customer Customer = customerRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    logger.info("Customer with email: " + email + "has been retrieved");
    logger.info("Comparing passwords...");
    if (!Customer.getPassword().equals(password)) throw new CustomerValidationException("Wrong password");
    logger.info("Customer validated");
  }

  public List<CustomerDTO> get(Logger logger) {
    logger.info("Retrieving existing Customers");
    return customerRepository.findAll().stream().map(customer -> customerToCustomerDTO.apply(customer)).collect(Collectors.toList());
  }

  public CustomerDTO getFromDocumentNumber(Logger logger, String documentNumber) {
    logger.info("Initializing Customer retrieving by document number");
    Customer customer = customerRepository.findById(documentNumber).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    logger.info("Finalizing Customer retrieving by document number");
    CustomerDTO customerDTO = customerToCustomerDTO.apply(customer);
    return customerDTO;
  }

  public void deleteFromDocumentNumber(Logger logger, String documentNumber) {
    logger.info("Initializing Customer deletion");
    if(!customerRepository.existsById(documentNumber)) {
      throw new EntityNotFoundException("Customer not found");
    }
    customerRepository.deleteById(documentNumber);
    logger.info("Customer deleted");
    logger.info("Finalizing Customer deletion");
  }
}

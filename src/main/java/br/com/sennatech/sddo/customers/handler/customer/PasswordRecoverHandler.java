package br.com.sennatech.sddo.customers.handler.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.domain.dto.RecoveryDTO;
import br.com.sennatech.sddo.customers.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.service.recovery.RecoverPassword;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import br.com.sennatech.sddo.customers.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;

@Component
public class PasswordRecoverHandler {

  @Autowired
  private RecoverPassword service;

  @FunctionName("customers-password-recovery")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.PUT }, authLevel = AuthorizationLevel.FUNCTION, route = "customers/password/recovery/{hash}") HttpRequestMessage<RecoveryDTO> request,
      @BindingName("hash") String hash,
      final ExecutionContext context) throws InterruptedException {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      service.run(hash, request.getBody().getNewPassword());
      return request.createResponseBuilder(HttpStatus.ACCEPTED).build();
    } catch (EntityNotFoundException e) {
      return request.createResponseBuilder(HttpStatus.NOT_FOUND).body(ResponseDTO.create(e.getMessage()))
          .build();
    } catch (Exception e) {
      logger.info("Error:\n" + ExceptionUtil.stackTraceToString(e));
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}

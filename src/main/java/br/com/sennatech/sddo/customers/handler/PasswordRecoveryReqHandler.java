package br.com.sennatech.sddo.customers.handler;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import br.com.sennatech.sddo.customers.model.dto.RecoveryRequestDTO;
import br.com.sennatech.sddo.customers.model.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.service.PasswordRecoverService;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import jakarta.persistence.EntityNotFoundException;

@Component
public class PasswordRecoveryReqHandler {

  @Autowired
  private PasswordRecoverService service;

  @FunctionName("request-password-recovery")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.POST }, authLevel = AuthorizationLevel.FUNCTION, route = "customers/password/recovery") HttpRequestMessage<RecoveryRequestDTO> request,
      final ExecutionContext context) {

    Logger logger = context.getLogger();
    logger.info("Starting service\n");

    try {
      service.request(logger, request.getBody());
      return request.createResponseBuilder(HttpStatus.CREATED).build();
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

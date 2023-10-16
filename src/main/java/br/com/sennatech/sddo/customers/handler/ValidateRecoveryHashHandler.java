package br.com.sennatech.sddo.customers.handler;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.exception.InvalidRecoveryHashException;
import br.com.sennatech.sddo.customers.model.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.service.PasswordRecoverService;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import jakarta.persistence.EntityNotFoundException;

@Component
public class ValidateRecoveryHashHandler {

  @Autowired
  private PasswordRecoverService service;

  @FunctionName("customers-recovery-hash-validate")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.GET}, authLevel = AuthorizationLevel.FUNCTION, route = "customers/password/hash-validation/{hash}") HttpRequestMessage<Optional<String>> request,
      @BindingName("hash") String hash,
      final ExecutionContext context) {

    Logger logger = context.getLogger();
    logger.info("Starting service\n");

    try {
      service.validate(logger, hash);
      return request.createResponseBuilder(HttpStatus.OK).build();
    } catch (EntityNotFoundException | InvalidRecoveryHashException e) {
      int code = (e instanceof EntityNotFoundException) ? 404 : 400; 
      return request.createResponseBuilder(HttpStatus.valueOf(code)).body(ResponseDTO.create(e.getMessage()))
          .build();
    } catch (Exception e) {
      logger.info("Error:\n" + ExceptionUtil.stackTraceToString(e));
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}

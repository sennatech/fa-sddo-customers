package br.com.sennatech.sddo.customers.handler.recovery;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.exception.InvalidRecoveryHashException;
import br.com.sennatech.sddo.customers.service.recovery.ValidateRecoverHash;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import br.com.sennatech.sddo.customers.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidateRecoveryHashHandler {

  private final ValidateRecoverHash service;

  @FunctionName("customers-recovery-hash-validate")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.GET}, authLevel = AuthorizationLevel.FUNCTION, route = "customers/password/hash-validation/{hash}") HttpRequestMessage<Optional<String>> request,
      @BindingName("hash") String hash,
      final ExecutionContext context) {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      service.run(hash);
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

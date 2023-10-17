package br.com.sennatech.sddo.customers.handler.recovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.domain.dto.RecoveryRequestDTO;
import br.com.sennatech.sddo.customers.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.service.recovery.RequestRecovery;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import br.com.sennatech.sddo.customers.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;

@Component
public class PasswordRecoveryReqHandler {

  @Autowired
  private RequestRecovery service;

  @FunctionName("customers-password-recovery-request")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.POST }, authLevel = AuthorizationLevel.FUNCTION, route = "customers/password/recovery") HttpRequestMessage<RecoveryRequestDTO> request,
      final ExecutionContext context) throws InterruptedException {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      service.run(request.getBody());
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

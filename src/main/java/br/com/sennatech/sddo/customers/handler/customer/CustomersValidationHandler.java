package br.com.sennatech.sddo.customers.handler.customer;

import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.exception.CustomerValidationException;
import br.com.sennatech.sddo.customers.service.customer.ValidateCustomer;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import br.com.sennatech.sddo.customers.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomersValidationHandler {

  private final ValidateCustomer service;

  @FunctionName("customers-validate")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "customers-validation/{hash}") HttpRequestMessage<String> request,
      @BindingName("hash") String hash,
      final ExecutionContext context) {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      service.run(hash);
      return request.createResponseBuilder(HttpStatus.OK).build();
    } catch (CustomerValidationException | EntityNotFoundException e) {
      int code = (e instanceof EntityNotFoundException) ? 404 : 400;
      return request.createResponseBuilder(HttpStatus.valueOf(code)).body(ResponseDTO.create(e.getMessage())).build();
    } catch (Exception e) {
      logger.info("Error:\n" + ExceptionUtil.stackTraceToString(e));
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}
package br.com.sennatech.sddo.customers.handler.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.service.customer.GetCustomer;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import br.com.sennatech.sddo.customers.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;

@Component
public class CustomersDetailsHandler {

  @Autowired
  private GetCustomer service;

  @FunctionName("customers-details")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "customers/{documentNumber}") HttpRequestMessage<String> request,
      @BindingName("documentNumber") String documentNumber,
      final ExecutionContext context) throws InterruptedException {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      return request.createResponseBuilder(HttpStatus.OK)
          .body(service.run(documentNumber)).build();
    } catch (EntityNotFoundException e) {
      return request.createResponseBuilder(HttpStatus.NOT_FOUND).body(ResponseDTO.create(e.getMessage())).build();
    } catch (Exception e) {
      logger.info("Error:\n" + ExceptionUtil.stackTraceToString(e));
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}

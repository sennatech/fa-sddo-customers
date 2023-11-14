package br.com.sennatech.sddo.customers.handler.customer;

import java.util.*;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.domain.dto.CustomerListDTO;
import br.com.sennatech.sddo.customers.service.customer.ListCustomers;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import br.com.sennatech.sddo.customers.util.LoggerUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomersListHandler {

  private final ListCustomers service;
  private final ObjectMapper mapper;

  @FunctionName("customers-list")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS, route = "customers") HttpRequestMessage<String> request,
      final ExecutionContext context) {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      List<CustomerListDTO> customerListDTOs = service.run();
      return request.createResponseBuilder(HttpStatus.OK).body(mapper.writeValueAsString(customerListDTOs)).build();
    } catch (Exception e) {
      logger.info("Error:\n" + ExceptionUtil.stackTraceToString(e));
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}

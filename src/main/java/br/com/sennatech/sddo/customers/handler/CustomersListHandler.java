package br.com.sennatech.sddo.customers.handler;

import java.util.logging.Logger;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.function.CustomerDTOToCustomerListDTO;
import br.com.sennatech.sddo.customers.model.dto.CustomerListDTO;
import br.com.sennatech.sddo.customers.service.*;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;

@Component
public class CustomersListHandler {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private CustomerDTOToCustomerListDTO customerToCustomerListDTO;

  @FunctionName("customers-list")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS, route = "customers") HttpRequestMessage<String> request,
      final ExecutionContext context) {

    Logger logger = context.getLogger();
    logger.info("Headers:\n" + request.getHeaders());
    logger.info("Listing all existing customers.");

    try {
      List<CustomerListDTO> customerListDTOs = customerService.get(logger).stream().map(customer -> customerToCustomerListDTO.apply(customer)).collect(Collectors.toList());
      return request.createResponseBuilder(HttpStatus.OK).body(customerListDTOs).build();
    } catch (Exception e) {
      logger.info("Error:\n" + ExceptionUtil.stackTraceToString(e));
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}

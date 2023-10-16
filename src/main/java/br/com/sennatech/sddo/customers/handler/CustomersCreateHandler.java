package br.com.sennatech.sddo.customers.handler;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.model.*;
import br.com.sennatech.sddo.customers.model.dto.*;
import br.com.sennatech.sddo.customers.config.Config;
import br.com.sennatech.sddo.customers.exception.*;
import br.com.sennatech.sddo.customers.model.dto.event.EventDTO;
import br.com.sennatech.sddo.customers.model.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.util.*;
import br.com.sennatech.sddo.customers.service.CustomerService;

@Component
public class CustomersCreateHandler {

  @Autowired
  private CustomerService customerService;

  @FunctionName("customers-create")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "customers") HttpRequestMessage<CustomerDTO> request,
      @EventHubOutput(name = "event", eventHubName = Config.EVENT_HUB_NAME, connection = Config.CONN_STRING) OutputBinding<EventDTO> outputItem,
      final ExecutionContext context) {

    Logger logger = context.getLogger();
    logger.info("Starting service\n");

    try {
      Customer customer = customerService.create(logger, request.getBody());
      outputItem.setValue(EventDTO.create(context, customer));
      return request.createResponseBuilder(HttpStatus.CREATED).build();
    } catch (DuplicatedEntityException e) {
      return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(ResponseDTO.create(e.getMessage())).build();
    } catch (Exception e) {
      logger.info("Error:\n" + ExceptionUtil.stackTraceToString(e));
      return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } finally {
      logger.info("Ending service");
    }
  }
}

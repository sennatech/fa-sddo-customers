package br.com.sennatech.sddo.customers.handler.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.config.Config;
import br.com.sennatech.sddo.customers.domain.dto.*;
import br.com.sennatech.sddo.customers.domain.dto.event.EventDTO;
import br.com.sennatech.sddo.customers.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.exception.*;
import br.com.sennatech.sddo.customers.util.*;
import br.com.sennatech.sddo.customers.service.customer.CreateCustomer;

@Component
public class CustomersCreateHandler {

  @Autowired
  private CreateCustomer service;

  @Autowired
  ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).registerModule(new Jdk8Module());

  @FunctionName("customers-create")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "customers") HttpRequestMessage<String> request,
      @EventHubOutput(name = "event", eventHubName = Config.EVENT_HUB_NAME, connection = Config.CONN_STRING) OutputBinding<EventDTO> outputItem,
      final ExecutionContext context) throws InterruptedException {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      CustomerDTO customerDTO = mapper.readValue(request.getBody(), CustomerDTO.class);
      service.run(customerDTO);
      outputItem.setValue(EventDTO.create(context, request.getBody()));
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

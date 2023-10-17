package br.com.sennatech.sddo.customers.handler.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.config.Config;
import br.com.sennatech.sddo.customers.domain.dto.*;
import br.com.sennatech.sddo.customers.domain.dto.event.EventDTO;
import br.com.sennatech.sddo.customers.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.function.*;
import br.com.sennatech.sddo.customers.service.customer.UpdateCustomer;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import br.com.sennatech.sddo.customers.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;

@Component
public class CustomersUpdateHandler {

  @Autowired
  private UpdateCustomer service;

  @Autowired
  private CustomerUpdateDTOtoCustomerDTO userUpdateDTOtoUserDTO;

  @FunctionName("customers-update")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.PUT }, authLevel = AuthorizationLevel.ANONYMOUS, route = "customers/{documentNumber}") HttpRequestMessage<CustomerUpdateDTO> request,
      @EventHubOutput(name = "event", eventHubName = Config.EVENT_HUB_NAME, connection = Config.CONN_STRING) OutputBinding<EventDTO> outputItem,
      @BindingName("documentNumber") String documentNumber,
      final ExecutionContext context) throws InterruptedException {

    LoggerUtil logger = LoggerUtil.create(context, request);
    logger.logReq();

    try {
      CustomerDTO customerDTO = userUpdateDTOtoUserDTO.apply(request.getBody(), documentNumber);
      service.run(customerDTO);
      outputItem.setValue(EventDTO.create(context, "Customer [" + documentNumber + "] updated"));
      return request.createResponseBuilder(HttpStatus.ACCEPTED).build();
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

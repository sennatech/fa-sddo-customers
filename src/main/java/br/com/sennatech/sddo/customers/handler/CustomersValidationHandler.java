package br.com.sennatech.sddo.customers.handler;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import br.com.sennatech.sddo.customers.exception.CustomerValidationException;
import br.com.sennatech.sddo.customers.model.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.service.CustomerService;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import jakarta.persistence.EntityNotFoundException;

@Component
public class CustomersValidationHandler {

  @Autowired
  private CustomerService customerService;

  @FunctionName("user-validate")
  public HttpResponseMessage run(
      @HttpTrigger(name = "req", methods = {
          HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS, route = "customers-validation/{hash}") HttpRequestMessage<String> request,
      @BindingName("hash") String hash,
      final ExecutionContext context) {

    Logger logger = context.getLogger();
    logger.info("Starting service.");

    try {
      customerService.validate(logger,hash);
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
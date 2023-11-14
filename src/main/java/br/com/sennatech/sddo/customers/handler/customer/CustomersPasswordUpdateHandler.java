package br.com.sennatech.sddo.customers.handler.customer;

import br.com.sennatech.sddo.customers.domain.dto.PasswordChangeDTO;
import br.com.sennatech.sddo.customers.domain.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.exception.InvalidCredentialException;
import br.com.sennatech.sddo.customers.service.customer.UpdateCustomerPassword;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import br.com.sennatech.sddo.customers.util.LoggerUtil;
import jakarta.persistence.EntityNotFoundException;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomersPasswordUpdateHandler {

    @Autowired
    private UpdateCustomerPassword service;

    @FunctionName("customers-password-update")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {
                    HttpMethod.PUT }, authLevel = AuthorizationLevel.FUNCTION, route = "customers/password/{documentNumber}") HttpRequestMessage<PasswordChangeDTO> request,
            @BindingName("documentNumber") String documentNumber,
            final ExecutionContext context) throws InterruptedException {

        LoggerUtil logger = LoggerUtil.create(context, request);
        logger.logReq();

        try {
            service.run(documentNumber, request.getBody());
            return request.createResponseBuilder(HttpStatus.OK).build();
        } catch (EntityNotFoundException | InvalidCredentialException e) {
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


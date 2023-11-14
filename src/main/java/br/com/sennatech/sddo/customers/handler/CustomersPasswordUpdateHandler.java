package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.exception.InvalidCredentialException;
import br.com.sennatech.sddo.customers.model.dto.PasswordRequestDTO;
import br.com.sennatech.sddo.customers.model.dto.util.ResponseDTO;
import br.com.sennatech.sddo.customers.service.PasswordService;
import br.com.sennatech.sddo.customers.util.ExceptionUtil;
import jakarta.persistence.EntityNotFoundException;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CustomersPasswordUpdateHandler {

    @Autowired
    private PasswordService service;

    @FunctionName("customers-password-update")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {
                    HttpMethod.PUT }, authLevel = AuthorizationLevel.FUNCTION,
                    route = "customers/password/{documentNumber}")
            HttpRequestMessage<PasswordRequestDTO> request,
            @BindingName("documentNumber") String documentNumber,
            final ExecutionContext context) {

        Logger logger = context.getLogger();
        logger.info("Starting service\n");

        try {
            service.update(logger, documentNumber, request.getBody());
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

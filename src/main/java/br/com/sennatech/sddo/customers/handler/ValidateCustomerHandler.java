package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.service.ValidateCustomer;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class ValidateCustomerHandler {

    private final ValidateCustomer validateCustomer;

    @FunctionName("customerValidation")
    public HttpResponseMessage validateCustomer(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "customers/{hash}"
            ) HttpRequestMessage request,
            @BindingName("hash") String hash,
            final ExecutionContext context) {

        Logger logger = context.getLogger();
        logger.info("Incoming request, validating user with hash: [" + hash + "].");

        try {
            validateCustomer.validate(hash);
            return request.createResponseBuilder(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()).build();
        }
    }
}

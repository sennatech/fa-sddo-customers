package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.Error;
import br.com.sennatech.sddo.customers.domain.dto.PasswordRequestDTO;
import br.com.sennatech.sddo.customers.service.UpdateCustomerPassword;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateCustomerPasswordHandler {
    private final UpdateCustomerPassword updateCustomerPassword;
    @FunctionName("customerUpdatePassword")
    public HttpResponseMessage updateCustomerPassword(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.PUT},
                    authLevel = AuthorizationLevel.FUNCTION,
                    route = "customer/password/{documentNumber}"
            ) HttpRequestMessage<Optional<PasswordRequestDTO>> request,
            @BindingName("documentNumber") String documentNumber,
            final ExecutionContext context
    ) {
        try {
            context.getLogger().info("Java HTTP trigger processed a request.");
            var passwordRequestDTO = request.getBody().get();
            var updatedCustomer = updateCustomerPassword.execute(documentNumber, passwordRequestDTO);
            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .body(updatedCustomer)
                    .build();
        } catch (Exception e){
            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Error.builder().message(e.getMessage()))
                    .build();
        }
    }
}


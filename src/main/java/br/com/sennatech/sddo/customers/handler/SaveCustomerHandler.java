package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.Error;
import br.com.sennatech.sddo.customers.service.SaveCustomer;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SaveCustomerHandler {

    private final SaveCustomer saveCustomer;
    @FunctionName("customerCreation")
    public HttpResponseMessage saveCustomer(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.FUNCTION,
                    route = "customers/{hash}"
            ) HttpRequestMessage<Optional<CustomerDTO>> request,
            final ExecutionContext context
    ) {
        try {
            context.getLogger().info("Java HTTP trigger processed a request.");
            var customer = request.getBody().get();
            var savedCustomer = saveCustomer.responseConvert(customer);
            return request
                    .createResponseBuilder(HttpStatus.CREATED)
                    .body(savedCustomer)
                    .build();
        } catch (Exception e){
            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Error.builder().message(e.getMessage()))
                    .build();
        }
    }
}


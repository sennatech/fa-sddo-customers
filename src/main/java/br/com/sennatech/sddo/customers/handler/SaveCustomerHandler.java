package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.service.SaveCustomer;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@RequiredArgsConstructor
@Component
public class SaveCustomerHandler {

    private final SaveCustomer saveCustomer;
    @FunctionName("customerCreate")
    public HttpResponseMessage save(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.POST },
                    authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<CustomerDTO>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        try {
            var customer = request.getBody().get();
            saveCustomer.responseConvert(customer);
            return request
                    .createResponseBuilder(HttpStatus.CREATED)
                    .body(customer)
                    .build();
        } catch (Exception e){
            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}


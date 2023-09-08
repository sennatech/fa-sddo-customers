package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.domain.CustomerDTO;
import br.com.sennatech.sddo.customers.service.AddCustomer;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@RequiredArgsConstructor
@Component
public class CustomerHandler {

    private final AddCustomer addCustomer;
    @FunctionName("customerCreate")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.POST },
                    authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<CustomerDTO>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        try {
            var customer = request.getBody().get();
            context.getLogger().info(customer.toString());
            addCustomer.responseConvert(customer);
            return request.createResponseBuilder(HttpStatus.CREATED).body(customer).build();

        } catch (Exception e){
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()).build();

        }

    }

}





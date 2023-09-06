package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.domain.CustomerDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

@Component
public class CustomerHandler {

    private static final ObjectMapper mapper = new ObjectMapper();


    @FunctionName("customerCreate")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = { HttpMethod.GET,
                    HttpMethod.POST }, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<CustomerDTO>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        var customer = request.getBody().get();
        context.getLogger().info(customer.toString());
        return request.createResponseBuilder(HttpStatus.OK).body(customer).build();
    }
}





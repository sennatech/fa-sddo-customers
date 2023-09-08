package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.domain.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.Error;
import br.com.sennatech.sddo.customers.service.AddCustomer;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
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
    public HttpResponseMessage save(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.POST },
                    authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<CustomerDTO>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        try {
            var customer = request.getBody().get();
            addCustomer.responseConvert(customer);
            return request
                    .createResponseBuilder(HttpStatus.CREATED)
                    .body(customer)
                    .build();
        } catch (Exception e){
            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Error.builder().message(e.getMessage()))
                    .build();
        }
    }

    @FunctionName("customerList")
    public HttpResponseMessage get(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET },
                    authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<CustomerDTO>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        try {
            var customer = request.getBody().get();
            context.getLogger().info(customer.toString());
            addCustomer.responseConvert(customer);
            return request
                    .createResponseBuilder(HttpStatus.CREATED)
                    .body(customer)
                    .build();
        } catch (Exception e){
            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Error.builder().message(e.getMessage()))
                    .build();
        }

    }

    @FunctionName("userGet")
    public String get(
            @HttpTrigger(name = "userGetRequest",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage request,
            @BindingName("id") Integer id, ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        return id.toString();
    }
}






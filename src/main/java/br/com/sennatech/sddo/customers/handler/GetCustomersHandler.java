package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.domain.dto.Error;
import br.com.sennatech.sddo.customers.service.GetCustomers;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetCustomersHandler {

    private final GetCustomers getCustomers;

    @FunctionName("customersList")
    public HttpResponseMessage getCustomer(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "customers"
            ) HttpRequestMessage request,
            ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        try{
            return request
                    .createResponseBuilder(HttpStatus.CREATED)
                    .body(getCustomers.execute())
                    .build();
        } catch (Exception e){
            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Error.builder().message(e.getMessage()).build())
                    .build();
        }
    }
}






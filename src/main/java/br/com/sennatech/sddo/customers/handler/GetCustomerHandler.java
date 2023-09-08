package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.service.GetCustomerService;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetCustomerHandler {


    private final GetCustomerService getCustomer;

    @FunctionName("getCustomer")
    public HttpResponseMessage getCustomer(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS, route = "customers/{documentNumber}")
            HttpRequestMessage request,
            @BindingName("documentNumber") String documentNumber, ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request." + documentNumber);
        try{
            return request
                    .createResponseBuilder(HttpStatus.CREATED)
                    .body(getCustomer.execute(documentNumber))
                    .build();
        } catch (Exception e){
            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}




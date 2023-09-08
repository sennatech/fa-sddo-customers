package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.domain.dto.Error;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCustomer {

    private final br.com.sennatech.sddo.customers.service.GetCustomer getCustomer;

    @FunctionName("getCustomer")
    public HttpResponseMessage getCustomer(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "customers/{documentNumber}"
            ) HttpRequestMessage request,
            @BindingName("documentNumber") String documentNumber,
            ExecutionContext context
    ) {
        try{
            context.getLogger().info("Java HTTP trigger processed a request." + documentNumber);
            return request
                    .createResponseBuilder(HttpStatus.CREATED)
                    .body(getCustomer.execute(documentNumber))
                    .build();
        } catch (Exception e){
            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Error.builder().message(e.getMessage()))
                    .build();
        }
    }
}






package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.domain.Error;
import br.com.sennatech.sddo.customers.service.DeleteCustomerByDocNumber;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteCustomerHandler {
    private  final DeleteCustomerByDocNumber delete;
    @FunctionName("deleteCustomer")
    public HttpResponseMessage deleteCustomer(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.DELETE},
                    authLevel = AuthorizationLevel.ANONYMOUS, route = "customers/{documentNumber}")
            HttpRequestMessage request,
            @BindingName("documentNumber") String documentNumber, ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request." + documentNumber);
        try{
            delete.execute(documentNumber);
            return request
                    .createResponseBuilder(HttpStatus.OK)
                    .build();
        } catch (Exception e){
            return request
                    .createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Error.builder().message(e.getMessage()))
                    .build();
        }
    }
}

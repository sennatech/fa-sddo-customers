package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.config.Config;
import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.Error;
import br.com.sennatech.sddo.customers.domain.dto.event.EventDTO;
import br.com.sennatech.sddo.customers.service.SaveCustomer;
import br.com.sennatech.sddo.customers.service.UpdateCustomer;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateCustomerHandler {

    private final UpdateCustomer updateCustomer;
    @FunctionName("customerUpdate")
    public HttpResponseMessage updateCustomer(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.PUT},
                    authLevel = AuthorizationLevel.FUNCTION,
                    route = "customers/{documentNumber}"
            ) HttpRequestMessage<Optional<CustomerDTO>> request,
            @EventHubOutput(name = "event", eventHubName = Config.EVENT_HUB_NAME, connection = Config.CONN_STRING) OutputBinding<EventDTO> outputItem,
            @BindingName("documentNumber") String documentNumber,
            final ExecutionContext context
    ) {
        try {
            context.getLogger().info("Java HTTP trigger processed a request.");
            var customer = request.getBody().get();
            var updatedCustomer = updateCustomer.execute(documentNumber, customer);
            outputItem.setValue(EventDTO.create(context, "Customer [" + documentNumber + "] updated"));
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


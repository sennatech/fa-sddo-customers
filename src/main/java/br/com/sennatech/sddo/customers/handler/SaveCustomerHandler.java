package br.com.sennatech.sddo.customers.handler;

import br.com.sennatech.sddo.customers.config.Config;
import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.Error;
import br.com.sennatech.sddo.customers.domain.dto.event.EventDTO;
import br.com.sennatech.sddo.customers.service.SaveCustomer;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerDTOToCustomerResponseDTO;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.EventHubOutput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SaveCustomerHandler {

    private final SaveCustomer saveCustomer;
    private final ConvertCustomerDTOToCustomerResponseDTO convertRequestToDTO;
    @FunctionName("customerCreation")
    public HttpResponseMessage saveCustomer(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.FUNCTION,
                    route = "customers"
            ) HttpRequestMessage<Optional<CustomerDTO>> request,
            @EventHubOutput(name = "event", eventHubName = Config.EVENT_HUB_NAME, connection = Config.CONN_STRING) OutputBinding<EventDTO> outputItem,
            final ExecutionContext context
    ) {
        try {
            context.getLogger().info("Java HTTP trigger processed a request.");
            var customer = request.getBody().get();
            var savedCustomer = saveCustomer.execute(customer);
            var responseSavedCustomer = convertRequestToDTO.convert(savedCustomer);
            outputItem.setValue(EventDTO.create(context, customer));
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


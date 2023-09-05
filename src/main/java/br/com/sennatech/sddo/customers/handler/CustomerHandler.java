package br.com.sennatech.sddo.customers.handler;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CustomerHandler {

    @FunctionName("HttpTriggerJava")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        String query = request.getQueryParameters().get("name");
        String name = request.getBody().orElse(query);

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
        }
    }

//    @FunctionName("userCreate")
//    public HttpResponseMessage runUserCreate(
//            @HttpTrigger(name = "req", methods = {
//                    HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "users") HttpRequestMessage<String> request,
//            final ExecutionContext context) {
//
//        Logger logger = context.getLogger();
//        logger.info("Headers:\n" + request.getHeaders());
//        logger.info("User to be created with received info from request:\n" + request.getBody());
//        try {
//            UserDTO userDTO = mapper.readValue(request.getBody(), UserDTO.class);
//            userCreate.accept(userDTO.toEntity());
//            logger.info("User created.");
//            return request.createResponseBuilder(HttpStatus.CREATED).build();
//        } catch (Exception e) {
//            logger.info("User not created.");
//            e.printStackTrace();
//            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()).build();
//        }
//    }

}

package br.com.sennatech.sddo.customers.util;

import java.util.logging.Logger;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;

public class LoggerUtil {

    private ExecutionContext context;
    private HttpRequestMessage<?> requestMessage;
    private Logger logger;

    private LoggerUtil(ExecutionContext context, HttpRequestMessage<?> requestMessage) {
        this.context = context;
        this.logger = context.getLogger();
        this.requestMessage = requestMessage;
    }

    public static LoggerUtil create(ExecutionContext context, HttpRequestMessage<?> requestMessage) {
        return new LoggerUtil(context, requestMessage);
    }

    public void info(String message) {
        if (message != null) {
            String info = String.format("[%S] - %s", context.getFunctionName(), message);
            logger.info(info);
        }
    }

    public void logError(Exception e) {
        info(ExceptionUtil.stackTraceToString(e));
    }

    public void logReq() {
        info("Query Parameters: " + requestMessage.getQueryParameters()); //Logando paramêtros query
        info("Headers: " + requestMessage.getHeaders()); // Logando headers
        info("Payload: " + requestMessage.getBody()); // Logando payload
    }
}

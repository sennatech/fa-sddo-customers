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

    public void info(String message) throws InterruptedException {
        Thread.sleep(1); // Thread aguarda 1 seg, assim os logs saem sincronizados e em sequência
        StringBuilder logMsgStringBuilder = new StringBuilder("[" + context.getFunctionName() + "] - "); // Com isso todos os log conterá o nome da função atual
        if (!message.isEmpty()) {
            logMsgStringBuilder.append(message);
            String messageBuilt = logMsgStringBuilder.toString();
            logger.info(messageBuilt);
        }
    }

    public void logReq() throws InterruptedException {
        info("\nHeaders: " + requestMessage.getHeaders()); // Logando headers
        info("\nPayload: " + requestMessage.getBody()); // Logando payload
    }
}

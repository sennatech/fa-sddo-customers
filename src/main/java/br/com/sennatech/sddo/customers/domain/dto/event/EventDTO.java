package br.com.sennatech.sddo.customers.domain.dto.event;

import com.microsoft.azure.functions.ExecutionContext;

import br.com.sennatech.sddo.customers.domain.entity.Operation;
import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class EventDTO {
  private Object data;
  private Operation operation;
  private String domain;
  private final String origin = System.getenv("origin");
  private String timestamp;

  private EventDTO(ExecutionContext context, Object... dataObjects) {
    if (dataObjects.length > 1) {
      this.data = dataObjects;
    } else {
      this.data = dataObjects[0];
    }
    this.domain = "Seguros";
    this.operation = Operation.get(context.getFunctionName());
    this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }

  public static EventDTO create(ExecutionContext context, Object... dataObjects) {
    EventDTO event = new EventDTO(context, dataObjects);
    String logMsg = new StringBuilder("Event created from template: ").append(event).toString();
    context.getLogger().info(logMsg);
    return event;
  }
}


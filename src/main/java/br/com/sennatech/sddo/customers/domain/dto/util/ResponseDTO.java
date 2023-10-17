package br.com.sennatech.sddo.customers.domain.dto.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.*;

@Data
public class ResponseDTO {
  private String message;
  private String timestamp;

  private ResponseDTO(String message) {
    this.message = message;
    this.timestamp = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
  }

  public static ResponseDTO create(String message) {
    return new ResponseDTO(message);
  }
}

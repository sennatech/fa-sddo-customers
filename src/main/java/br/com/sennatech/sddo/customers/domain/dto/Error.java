package br.com.sennatech.sddo.customers.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private String message;
}

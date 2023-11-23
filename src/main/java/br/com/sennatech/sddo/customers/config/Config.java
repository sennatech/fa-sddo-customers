package br.com.sennatech.sddo.customers.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class Config {

    public static final String CONN_STRING = "AzureEventHubConnection";
    public static final String EVENT_HUB_NAME = "topico-sddo-clientes";
    public static final Integer RECOVER_EXPIRATION_TIME = Integer.parseInt(System.getenv("EXPIRATION_TIME_IN_MINUTES"));
    public static final String RECOVERY_HOSTNAME = System.getenv("RECOVERY_HOSTNAME");
    public static final String EMAIL_FROM = System.getenv("EMAIL_FROM");

    @Bean
    public ModelMapper modelMapper() {
        return new org.modelmapper.ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule()).registerModule(new Jdk8Module());
    }
}
package br.com.sennatech.sddo.customers.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    public static final String CONN_STRING = "AzureEventHubConnection";
    public static final String EVENT_HUB_NAME = "topico-sddo-clientes";
    @Bean
    public ModelMapper modelMapper() {
        return new org.modelmapper.ModelMapper();
    }
}
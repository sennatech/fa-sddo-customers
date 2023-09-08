package br.com.sennatech.sddo.customers.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Teste {
    public static void main(String[] args) {
        String data = "1992-01-01";
        LocalDate teste ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        teste = LocalDate.parse(data);
        System.out.println(teste);
    }
}

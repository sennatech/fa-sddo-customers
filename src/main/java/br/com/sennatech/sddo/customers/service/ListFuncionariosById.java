//package br.com.sennatech.sddo.customers.service;
//
//import br.com.sennatech.wib.controller.resources.FuncionarioResource;
//import br.com.sennatech.wib.repository.FuncionarioRepository;
//import br.com.sennatech.wib.service.converters.ConvertFuncionarioToFuncionarioResource;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class ListFuncionariosById {
//
//    private final FuncionarioRepository repository;
//    private final ConvertFuncionarioToFuncionarioResource convertFuncionarioToFuncionarioResource;
//    public FuncionarioResource execute(Long id){
//        final var funcionario = repository.findById(id).orElseThrow(() ->new EntityNotFoundException("Desculpe, dado não encontrado."));
//        return convertFuncionarioToFuncionarioResource.convert(funcionario);
//    }
//}
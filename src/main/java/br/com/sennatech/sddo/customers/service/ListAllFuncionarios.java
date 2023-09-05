//package br.com.sennatech.sddo.customers.service;
//
//import br.com.sennatech.wib.controller.resources.FuncionarioResponse;
//import br.com.sennatech.wib.repository.FuncionarioRepository;
//import br.com.sennatech.wib.service.converters.ConvertFuncionarioToFuncionarioResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class ListAllFuncionarios {
//
//    private final FuncionarioRepository repository;
//    private final ConvertFuncionarioToFuncionarioResponse convertFuncionarioToResponse;
//
//    public Page<FuncionarioResponse> execute(Pageable pagination){
//        return repository
//                .findAll(pagination)
//                .map(funcionario -> convertFuncionarioToResponse.responseConvert(funcionario));
//    }
//}

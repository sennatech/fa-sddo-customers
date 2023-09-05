//package br.com.sennatech.sddo.customers.service;
//
//import br.com.sennatech.wib.controller.resources.FuncionarioResource;
//import br.com.sennatech.wib.domain.Funcionario;
//import br.com.sennatech.wib.repository.FuncionarioRepository;
//import br.com.sennatech.wib.service.converters.ConvertFuncionarioRequestToFuncionario;
//import br.com.sennatech.wib.service.converters.ConvertFuncionarioToFuncionarioResource;
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UpdateFuncionario {
//
//    private final ConvertFuncionarioRequestToFuncionario convertRequestToFuncionario;
//    private final ConvertFuncionarioToFuncionarioResource convertFuncionarioToFuncionarioResource;
//    private final FuncionarioRepository repository;
//    @Transactional
//    public FuncionarioResource execute(Long id, FuncionarioResource request){
//        repository.getReferenceById(id);
//        Funcionario funcionario = convertRequestToFuncionario.convert(request);
//        return convertFuncionarioToFuncionarioResource.convert(repository.save(funcionario));
//    }
//}

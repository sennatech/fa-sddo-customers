package br.com.sennatech.sddo.customers.function;

import java.util.function.BiConsumer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerUpdateEntity implements BiConsumer<Customer, CustomerDTO>{

  private final ModelMapper mapper;

  @Override
  public void accept(Customer toBeUpdatedCustomer, CustomerDTO incomingCustomer) {
     mapper.map(incomingCustomer, toBeUpdatedCustomer);
  }
}

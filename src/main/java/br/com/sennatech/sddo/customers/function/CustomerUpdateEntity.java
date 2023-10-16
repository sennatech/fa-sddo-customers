package br.com.sennatech.sddo.customers.function;

import java.util.function.BiConsumer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.model.Customer;
import br.com.sennatech.sddo.customers.model.dto.CustomerDTO;

@Component
public class CustomerUpdateEntity implements BiConsumer<Customer, CustomerDTO>{

  @Autowired
  private ModelMapper mapper;

  @Override
  public void accept(Customer toBeUpdatedCustomer, CustomerDTO incomingCustomer) {
     mapper.map(incomingCustomer, toBeUpdatedCustomer);
  }
}

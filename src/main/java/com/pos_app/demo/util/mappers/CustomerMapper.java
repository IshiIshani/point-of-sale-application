package com.pos_app.demo.util.mappers;

import com.pos_app.demo.dto.CustomerDto;
import com.pos_app.demo.dto.Response.ResponseActiveCustomersDTO;
import com.pos_app.demo.dto.Response.ResponseCustomerId;
import com.pos_app.demo.dto.request.CustomerSaveDto;
import com.pos_app.demo.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer saveDtoToEntity(CustomerSaveDto saveDto);

    ResponseCustomerId getCustomerById(Customer customer);

    List<CustomerDto> entityListToDtoList(List<Customer> all);

    List<ResponseActiveCustomersDTO> getCustomerByState(List<Customer> getCustomers);
}

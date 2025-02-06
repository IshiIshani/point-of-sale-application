package com.pos_app.demo.service;

import com.pos_app.demo.dto.CustomerDto;
import com.pos_app.demo.dto.Response.ResponseActiveCustomersDTO;
import com.pos_app.demo.dto.Response.ResponseCustomerId;
import com.pos_app.demo.dto.request.CustomerSaveDto;
import com.pos_app.demo.dto.request.CustomerUpadateDto;

import java.util.List;

public interface CustomerService {

    String addCustomerSaveDto(CustomerSaveDto saveDto);

    String updateCustomer(CustomerUpadateDto upadateDto, int id);

    ResponseCustomerId getCustomerById(int id);

    List<CustomerDto> getAllCustomers();
    List<ResponseActiveCustomersDTO> getCustomerByActiveState(String state);
}

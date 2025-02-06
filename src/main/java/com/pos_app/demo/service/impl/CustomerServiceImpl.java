package com.pos_app.demo.service.impl;

import com.pos_app.demo.dto.CustomerDto;
import com.pos_app.demo.dto.Response.ResponseActiveCustomersDTO;
import com.pos_app.demo.dto.Response.ResponseCustomerId;
import com.pos_app.demo.dto.request.CustomerSaveDto;
import com.pos_app.demo.dto.request.CustomerUpadateDto;
import com.pos_app.demo.repo.CustomerRepo;
import com.pos_app.demo.service.CustomerService;
import com.pos_app.demo.util.mappers.CustomerMapper;
import com.pos_app.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String addCustomerSaveDto(CustomerSaveDto saveDto) {
        Customer customer = customerMapper.saveDtoToEntity(saveDto);
        customer.setActiveState(true);
        customerRepo.save(customer);
        return "OK..";
    }

    @Override
    public String updateCustomer(CustomerUpadateDto updateDto, int id) {

        if (customerRepo.existsById(id)) {
            Customer customer = customerRepo.findById(id).orElseThrow();

            customer.setCustomerName(updateDto.getCustomerName());
            customer.setCustomerAddress(updateDto.getCustomerAddress());
            customer.setContactNumber(updateDto.getContactNumbers());
            customer.setActiveState(updateDto.isActiveState());

            customerRepo.save(customer);
            return "ok..";

            //update query eka mysql walinma repo eke generate kala
            /*customerRepo.updateCustomerDetails(
                    updateDto.getCustomerName(), updateDto.getCustomerAddress(), updateDto.getContactNumbers(),
                    updateDto.isActiveState(), id
            );

            return "Ok...";*/


        } else {
            throw new EntityNotFoundException("Invalid Customer Id...");
        }
    }

    @Override
    public ResponseCustomerId getCustomerById(int id) {
        Optional<Customer> getCustomer = customerRepo.findById(id);
        if (getCustomer.isPresent()) {
            ResponseCustomerId res = customerMapper.getCustomerById(getCustomer.get());
            return res;
        } else
            throw new EntityNotFoundException("Invalid customer id....");
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer> getAll = customerRepo.findAll();
        if (getAll.size() > 0) {
            List<CustomerDto> dtoList = customerMapper.entityListToDtoList(getAll);
            return dtoList;
        } else
            throw new EntityNotFoundException("No entry....");
//        return customerMapper.entityListToDtoList(customerRepo.findAll());

    }

    @Override
    public List<ResponseActiveCustomersDTO> getCustomerByActiveState(String state) {

        if(state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")){
            boolean b = state.equalsIgnoreCase("active") ? true : false;
            List<Customer> getCustomers = customerRepo.findAllByActiveStateEquals(b);
            List<ResponseActiveCustomersDTO> dtos  = customerMapper.getCustomerByState(getCustomers);
            return dtos;
//            return customerMapper.getCustomerByState(getCustomers);

        }else if (state.equalsIgnoreCase("all")){
            List<Customer> customerList = customerRepo.findAll();
            List<ResponseActiveCustomersDTO> dtos  = customerMapper.getCustomerByState(customerList);
            return dtos;
//            return customerMapper.getCustomerByState(customerRepo.findAll());

        }else
            throw new EntityNotFoundException("Invalid user input");
    }


}







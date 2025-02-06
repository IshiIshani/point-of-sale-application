package com.pos_app.demo.controller;


import com.pos_app.demo.dto.CustomerDto;
import com.pos_app.demo.dto.request.CustomerSaveDto;
import com.pos_app.demo.dto.request.CustomerUpadateDto;
import com.pos_app.demo.service.CustomerService;
import com.pos_app.demo.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/save-customer")
    public ResponseEntity<StandardResponse> addCustomer(@RequestBody CustomerSaveDto saveDto) {
        String save = customerService.addCustomerSaveDto(saveDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, saveDto.getCustomerName() + " save successfully...", save),
                HttpStatus.CREATED
        );

    }

    @PutMapping(path = {"/update-customer"}, params = {"id"})
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpadateDto upadateDto,
                                                           @RequestParam(value = "id") int id) {
        String update = customerService.updateCustomer(upadateDto, id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, upadateDto.getCustomerName() + " update successfully...", update),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-customer-by-id"},
            params = {"id"})
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(value = "id") int id) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "customer details of : " + id, customerService.getCustomerById(id)),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-all-customer")
    public ResponseEntity<StandardResponse> getAllCustomer() {
        List<CustomerDto> list = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Customer", list),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-customer-by-active-state"},
            params = {"state"})
    public ResponseEntity<StandardResponse> getCustomerByActiveState(@RequestParam (value = "state") String state){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,  state + " customers....",
                        customerService.getCustomerByActiveState(state)),
                HttpStatus.OK
        );
    }




}



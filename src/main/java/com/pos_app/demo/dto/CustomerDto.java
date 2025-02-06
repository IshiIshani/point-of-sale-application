package com.pos_app.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String contactNumber;
    private String nic;
    private boolean activeState;

}

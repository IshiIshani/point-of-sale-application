package com.pos_app.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerSaveDto {

    private String customerName;
    private String customerAddress;
    private String contactNumber;
    private String nic;


}

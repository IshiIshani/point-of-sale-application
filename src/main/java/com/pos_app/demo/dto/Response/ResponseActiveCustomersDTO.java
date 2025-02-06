package com.pos_app.demo.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseActiveCustomersDTO {

    private String customerName;
    private String customerAddress;
    private String contactNumber;
    private String nic;
}

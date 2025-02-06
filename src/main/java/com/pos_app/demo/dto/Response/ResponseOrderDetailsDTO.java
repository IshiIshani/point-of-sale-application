package com.pos_app.demo.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsDTO {

    //customer table
    private String customerName;
    private String customerAddress;
    private String contactNumbers;

    //from order table
    private Date date;
    private double total;
}

package com.pos_app.demo.dto.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerUpadateDto {

        private String customerName;
        private String customerAddress;
        private String contactNumbers;
        private boolean activeState;
    }







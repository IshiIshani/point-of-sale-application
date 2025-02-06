package com.pos_app.demo.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseActiveItemsDto {

    private String itemName;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;

}

package com.pos_app.demo.dto.request;


import com.pos_app.demo.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemSaveDto {

    private String itemName;
    private MeasuringUnitType measuringUnit;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
    private String itemDescription;


}

// Request ==> table ekata data saving or updating (fetch)
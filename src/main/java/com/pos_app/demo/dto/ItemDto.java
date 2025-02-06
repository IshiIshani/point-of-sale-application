package com.pos_app.demo.dto;

import com.pos_app.demo.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemDto {

    private int itemId;
    private String itemName;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
    private boolean activeState;
    private MeasuringUnitType measuringUnit;

}


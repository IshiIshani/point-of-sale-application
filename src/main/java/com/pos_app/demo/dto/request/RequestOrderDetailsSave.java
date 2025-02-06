package com.pos_app.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSave {

    private String itemName;
    private double Qty;
    private double amount;
    private int itemId;
}

package com.pos_app.demo.entity;

import com.pos_app.demo.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

    @Id
    @Column(name = "item_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "balance_qty", length = 100, nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price", length = 100, nullable = false)
    private double supplierPrice;

    @Column(name = "selling_price", length = 100, nullable = false)
    private double sellingPrice;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_unit", length = 100)
    private MeasuringUnitType measuringUnit;

    @OneToMany(mappedBy="item")
    private Set<OrderDetails> orderDetails;


}

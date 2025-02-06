package com.pos_app.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", length = 45)
    private int customerId;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 225, nullable = false)
    private String customerAddress;

    @Column(name = "contact_number", length = 15, unique = true, nullable = false)
    private String contactNumber;

    @Column(name = "nic", length = 15, unique = true)
    private String nic;

    @Column(name = "active_status", columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @OneToMany(mappedBy="customer")
    private Set<Order> orders;

}

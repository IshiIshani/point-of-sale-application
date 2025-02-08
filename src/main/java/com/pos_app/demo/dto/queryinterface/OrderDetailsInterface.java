package com.pos_app.demo.dto.queryinterface;
import java.util.Date;
public interface OrderDetailsInterface {

    String getCustomerName();
    String getCustomerAddress();
    String getContactNumbers();
    Date getDate();
    double  getTotal();


}

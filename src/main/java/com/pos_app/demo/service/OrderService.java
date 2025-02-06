package com.pos_app.demo.service;

import com.pos_app.demo.dto.paginated.PaginatedResponseOrderDetails;
import com.pos_app.demo.dto.request.OrderSaveDTO;

public interface OrderService {
    String addOrder(OrderSaveDTO saveDTO);

    PaginatedResponseOrderDetails getOrderDetails(String state, int page);

}

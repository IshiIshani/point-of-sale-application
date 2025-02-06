package com.pos_app.demo.controller;

import com.pos_app.demo.dto.paginated.PaginatedResponseOrderDetails;
import com.pos_app.demo.dto.request.OrderSaveDTO;
import com.pos_app.demo.service.OrderService;
import com.pos_app.demo.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save-order")
    public ResponseEntity<StandardResponse> addOrder(@RequestBody OrderSaveDTO saveDTO) {
        String save = orderService.addOrder(saveDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "success", save),
                HttpStatus.CREATED
        );
    }

    // front end eken state eka awama order details eka ganna ona
    @GetMapping(path = {"/get-order-details"},
            params = {"state", "page"})
    public ResponseEntity<StandardResponse> getOrderDetails(@RequestParam (value = "state") String state,
                                                            @RequestParam (value = "page") int page) {

        PaginatedResponseOrderDetails details = orderService.getOrderDetails(state, page);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, state + " order details", details),
                HttpStatus.CREATED
        );
    }
}

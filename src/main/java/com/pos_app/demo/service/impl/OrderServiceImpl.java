package com.pos_app.demo.service.impl;

import com.pos_app.demo.dto.Response.ResponseOrderDetailsDTO;
import com.pos_app.demo.dto.paginated.PaginatedResponseOrderDetails;
import com.pos_app.demo.dto.queryinterface.OrderDetailsInterface;
import com.pos_app.demo.dto.request.OrderSaveDTO;
import com.pos_app.demo.exception.EntryNotFoundException;
import com.pos_app.demo.repo.CustomerRepo;
import com.pos_app.demo.repo.ItemRepo;
import com.pos_app.demo.repo.OrderDetailsRepo;
import com.pos_app.demo.repo.OrderRepo;
import com.pos_app.demo.service.OrderService;
import com.pos_app.demo.entity.Order;
import com.pos_app.demo.entity.OrderDetails;
import org.hibernate.boot.model.source.spi.Orderable;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public String addOrder(OrderSaveDTO saveDTO) {

        Order saveOrder = new Order(
                customerRepo.getReferenceById(saveDTO.getCustomerId()),
                saveDTO.getDate(),
                saveDTO.getTotal()
        );

        saveOrder.setActiveStatus(true);
        orderRepo.save(saveOrder);

        System.out.println(saveOrder + " saved");

        //save order details
        if (orderRepo.existsById(saveOrder.getOrderId())) {

            //set order details
            List<OrderDetails> saveOrderDetails = modelMapper.map(saveDTO.getOrderDetailsSaves(),
                    new TypeToken<List<OrderDetails>>() {
                    }.getType());

            //set order id and item id into order details table
            for (int i = 0; i < saveOrderDetails.size(); i++) {
                saveOrderDetails.get(i).setOrders(saveOrder);
                saveOrderDetails.get(i).setItem(itemRepo.getReferenceById(saveDTO.getOrderDetailsSaves().get(i).getItemId()));
            }

            if (saveOrderDetails.size() > 0) {
                orderDetailsRepo.saveAll(saveOrderDetails);
            }

            return "done";

        } else
            throw new EntryNotFoundException("Order didn't complete");

    }

    @Override
    public PaginatedResponseOrderDetails getOrderDetails(String state, int page) {

        if (state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")) {
            boolean val = state.equalsIgnoreCase("active") ? true : false;
            List<OrderDetailsInterface> orderDetailsInterfaces = orderRepo.getOrderDetails(val, PageRequest.of(page, 2));
            List<ResponseOrderDetailsDTO> responseOrderDetailsDTOS = new ArrayList<>();

            for (OrderDetailsInterface o : orderDetailsInterfaces) {
                ResponseOrderDetailsDTO detailsDTO = new ResponseOrderDetailsDTO(
                        o.getCustomerName(), o.getCustomerAddress(), o.getContactNumbers(),
                        o.getDate(), o.getTotal()
                );

                responseOrderDetailsDTOS.add(detailsDTO);
            }
            long dataCount = orderRepo.countOrderDetailsByState(val);
            return new PaginatedResponseOrderDetails(
                    responseOrderDetailsDTOS,
                    dataCount
            );

        } else if (state.equalsIgnoreCase("all")) {
            List<OrderDetailsInterface> orderDetailsInterfaces = orderRepo.getAllOrderDetails(PageRequest.of(page, 2));
            List<ResponseOrderDetailsDTO> responseOrderDetailsDTOS = new ArrayList<>();

            for (OrderDetailsInterface o : orderDetailsInterfaces) {
                ResponseOrderDetailsDTO detailsDTO = new ResponseOrderDetailsDTO(
                        o.getCustomerName(), o.getCustomerAddress(), o.getContactNumbers(),
                        o.getDate(), o.getTotal()
                );

                responseOrderDetailsDTOS.add(detailsDTO);
            }
            long dataCount = orderRepo.countALLOrderDetails();
            return new PaginatedResponseOrderDetails(
                    responseOrderDetailsDTOS,
                    dataCount
            );
        }
        throw new EntryNotFoundException("Invalid User Input.....");

    }
}

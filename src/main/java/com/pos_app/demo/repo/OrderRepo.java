package com.pos_app.demo.repo;

import com.pos_app.demo.dto.queryinterface.OrderDetailsInterface;
import com.pos_app.demo.entity.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order, Integer> {
    //query by status
    @Query(value = "SELECT\n" +
            "    c.customer_name AS CustomerName,\n" +
            "    c.customer_address AS CustomerAddress,\n" +
            "    c.contact_number AS ContactNumbers,\n" +
            "    o.order_date AS date,\n" +
            "    o.total\n" +
            "FROM customer c\n" +
            "         INNER JOIN orders o ON c.customer_id = o.customer_id\n" +
            "WHERE o.active_state =:val", nativeQuery = true)
    List<OrderDetailsInterface> getOrderDetails(@Param("val")boolean val,
                                                PageRequest of);

    //count query for status
    @Query(value = "SELECT COUNT(*) AS TotalCount\n" +
            "FROM customer c\n" +
            "         INNER JOIN orders o ON c.customer_id = o.customer_id\n" +
            "WHERE o.active_state =:val", nativeQuery = true)
    long countOrderDetailsByState(@Param("val") boolean val);

    //query for get all orders
    @Query(value ="SELECT\n" +
            "    c.customer_name AS CustomerName,\n" +
            "    c.customer_address AS CustomerAddress,\n" +
            "    c.contact_number AS ContactNumbers,\n" +
            "    o.order_date AS date,\n" +
            "    o.total\n" +
            "FROM customer c\n" +
            "         INNER JOIN orders o ON c.customer_id = o.customer_id", nativeQuery = true)
    List<OrderDetailsInterface> getAllOrderDetails(PageRequest of);

    @Query(value = "SELECT COUNT(*) AS TotalCount\n" +
            "FROM customer c\n" +
            "         INNER JOIN orders o ON c.customer_id = o.customer_id", nativeQuery = true)
    long countALLOrderDetails();
}

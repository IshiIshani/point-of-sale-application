package com.pos_app.demo.repo;

import com.pos_app.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByActiveStateEquals(boolean b);

    //native query ekak generate karanawa mysql use karala
    /*@Modifying
    @Transactional
    @Query(value = "UPDATE customer SET customer_name = :customerName, customer_address = :customerAddress, " +
            "contact_number = :contactNumber, active_status = :activeState WHERE customer_id = :id", nativeQuery = true)
    void updateCustomerDetails( @Param("customerName") String customerName,
                                @Param("customerAddress") String customerAddress,
                                @Param("contactNumber") String contactNumber,
                                @Param("activeState") boolean activeState,
                                @Param("id") int id);*/
}

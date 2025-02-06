package com.pos_app.demo.repo;

import com.pos_app.demo.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update item set balance_qty =:balanceQty, selling_price =:sellingPrice, " +
            "supplier_price =:supplierPrice, active_state =:activeState where item_id =:id", nativeQuery = true)
    void updateItem( @Param("balanceQty") double balanceQty,
                     @Param("sellingPrice") double sellingPrice,
                     @Param("supplierPrice") double supplierPrice,
                     @Param("activeState") boolean activeState,
                     @Param("id") int id);

    List<Item> findAllByActiveStateEquals(boolean b);

    long countAllByActiveStateEquals(boolean val);

    Page<Item> findAllByActiveStateEquals(boolean s, PageRequest pageRequest);
}

package com.cbt.cbtjan24;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByOfferid(String offerid);

    Order findByOrderid(String orderid);

    List<Order> findByBuyername(String buyername);
}
package com.cbt.cbtjan24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
public class OrderRestController
{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderstatusRepository orderstatusRepository;

    @PostMapping("save/order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order)
    {
        order.setOrderid(String.valueOf((int) (Math.random() * 10000)));
        orderRepository.save(order);

        Orderstatus orderstatus = new Orderstatus();
        orderstatus.setId(String.valueOf((int) (Math.random() * 10000)));
        orderstatus.setOrderid(order.getOrderid());
        orderstatus.setStatus("OPEN");

        orderstatusRepository.save(orderstatus);
        return ResponseEntity.ok(order);
    }

}

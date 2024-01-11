package com.cbt.cbtjan24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerOfferOrdersService
{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderstatusRepository orderstatusRepository;
    @Autowired
    ProductofferRepository productofferRepository;
    @Autowired
    SellerOrderViewService sellerOrderViewService;

    public SellerOfferOrdersView createOrderList(String offerid)
    {
        List<Order> orders = orderRepository.findByOfferid(offerid);

        SellerOfferOrdersView view = new SellerOfferOrdersView();
        view.setOfferid(offerid);
        view.setOffername(productofferRepository.findById(offerid).get().getOffername());
        view.setOrderViewList( orders.stream().
                filter(order -> orderstatusRepository.findById(order.getOrderid()).get().
                        getStatus().equalsIgnoreCase("OPEN")).
                map(order -> sellerOrderViewService.createSellerOrderView(order.getOrderid())).
                collect(Collectors.toList()));

        return  view;

    }

}

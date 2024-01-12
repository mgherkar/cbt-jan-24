package com.cbt.cbtjan24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/seller")
public class SellerController
{
    @Autowired
    ProductofferRepository productofferRepository;
    @Autowired
    SellerOfferOrdersService sellerOfferOrdersService;
    @Autowired
    ProductofferstatusRepository productofferstatusRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderstatusRepository orderstatusRepository;
    @Autowired
    UsernamewalletlinkRepository usernamewalletlinkRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PaymentwalletlinkRepository paymentwalletlinkRepository;

    @GetMapping("get/orders/{sellername}")
    public ResponseEntity<List<SellerOfferOrdersView>> getOrdersSellerwise(@PathVariable String sellername)
    {
        List<Productoffer> productofferList = productofferRepository.findBySellernameIgnoreCase(sellername);
        List<SellerOfferOrdersView> view = productofferList.stream().
                map(productoffer -> sellerOfferOrdersService.createOrderList(productoffer.getId())).
                collect(Collectors.toList());

        return ResponseEntity.ok(view);
    }

    @PostMapping("accept/order/{orderid}")
    public ResponseEntity<String> acceptOrder(@PathVariable String orderid)
    {
        Order order =  orderRepository.findByOrderid(orderid);
        String offerid = order.getOfferid();
        productofferstatusRepository.updateStatusByOfferid("PROCESSING",offerid);

        List<Order> orders = orderRepository.findByOfferid(offerid);
        orders.stream().
                forEach(tempOrder ->
                {
                    if(!(tempOrder.getOrderid().equals(orderid)))
                    {
                        orderstatusRepository.updateStatusByOrderid("REJECTED",tempOrder.getOrderid());
                    }
                    else {orderstatusRepository.updateStatusByOrderid("ACCEPTED",tempOrder.getOrderid());}
                });

        String sellername = productofferRepository.findById(offerid).get().getSellername();
        String buyername = orderRepository.findById(orderid).get().getBuyername();

        Paymentwalletlink paymentwalletlink = new Paymentwalletlink();
        paymentwalletlink.setLinkid(String.valueOf((int) (Math.random() * 10000)));
        paymentwalletlink.setPaymenttype("ORDER");
        paymentwalletlink.
                setPayerwallet(usernamewalletlinkRepository.findById(buyername).get().getWalletid());
        paymentwalletlink.
                setPayeewallet(usernamewalletlinkRepository.findById(sellername).get().getWalletid());
        paymentwalletlink.
                setEscrowwallet(usernamewalletlinkRepository.findById("indiagator").get().getWalletid());
        paymentwalletlink.setAmount(orderRepository.findById(orderid).get().getBid());


        Payment payment = new Payment();

        payment.setId(String.valueOf((int) (Math.random() * 10000)));
        payment.setOrderid(orderid);
        payment.setOfferid(offerid);
        payment.setStatus("DUE");
        payment.setPaymentwalletlink(paymentwalletlink.getLinkid());

        paymentwalletlink.setPaymentrefid(payment.getId());


        paymentwalletlinkRepository.save(paymentwalletlink);
        paymentRepository.save(payment);

        return ResponseEntity.ok("Order Accepted and Payment Created");

    }
}

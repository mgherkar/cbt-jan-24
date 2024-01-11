package com.cbt.cbtjan24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("get/orders/{sellername}")
    public ResponseEntity<List<SellerOfferOrdersView>> getOrdersSellerwise(@PathVariable String sellername)
    {
        List<Productoffer> productofferList = productofferRepository.findBySellernameIgnoreCase(sellername);


        return ResponseEntity.ok(productofferList.stream().
                map(productoffer -> sellerOfferOrdersService.createOrderList(productoffer.getId())).
                collect(Collectors.toList()));

    }
}

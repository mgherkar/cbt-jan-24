package com.cbt.cbtjan24;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/offer")
public class OfferRestController
{

    @Autowired
    ProductofferRepository productofferRepository;
    @Autowired
    ProductofferstatusRepository productofferstatusRepository;
    @Autowired
    ProductOfferViewService productOfferViewService;



    @PostMapping("save/offer")
    public ResponseEntity<Productoffer> saveOffer(@RequestBody Productoffer offer)
    {
        offer.setId(String.valueOf((int) (Math.random() * 10000)));
        productofferRepository.save(offer);

        Productofferstatus productofferstatus = new Productofferstatus();
        productofferstatus.setId(String.valueOf((int) (Math.random() * 10000)));
        productofferstatus.setOfferid(offer.getId());
        productofferstatus.setStatus("OPEN");

        productofferstatusRepository.save(productofferstatus);

        return ResponseEntity.ok(offer);
    }

    @GetMapping("get/offers/{offer_status}")
    public ResponseEntity<List<ProductOfferView>> getOffers(@PathVariable String offer_status )
    {
        List<Productofferstatus> requestedOffers =  productofferstatusRepository.findByStatusIgnoreCase(offer_status);
        List<ProductOfferView> finalOffers =  requestedOffers.stream().map(productofferstatus -> productOfferViewService.createProductOfferView(productofferstatus.getOfferid())).collect(Collectors.toList());
        return ResponseEntity.ok(finalOffers);
    }



}

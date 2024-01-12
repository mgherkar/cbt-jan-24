package com.cbt.cbtjan24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticViewService
{
    @Autowired
    LogisticrfqRepository logisticrfqRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductofferRepository productofferRepository;
    @Autowired
    PortRepository portRepository;
    @Autowired
    ProductRepository productRepository;

    public LogisticView createLogisticView( String rfqid )
    {
        Logisticrfq logisticrfq =  logisticrfqRepository.findById(rfqid).get();
        String orderid =  logisticrfq.getOrderid();
        String offerid = orderRepository.findById(orderid).get().getOfferid();

        LogisticView view = new LogisticView();
        view.setRfqid(rfqid);
        view.setStatus(logisticrfq.getStatus());
        view.setProductname(productRepository.
                findById(productofferRepository.
                        findById(offerid).get().getHscode()).get().getName());

        view.setOriginport(portRepository.findById(logisticrfq.getOriginport()).get().getName());
        view.setDestinationport(portRepository.findById(logisticrfq.getDestinationport()).get().getName());

        view.setAmount(orderRepository.findById(orderid).get().getBid());

        view.setBuyername(orderRepository.findById(orderid).get().getBuyername());
        view.setSellername(productofferRepository.findById(offerid).get().getSellername());
        view.setCurrency(productofferRepository.findById(offerid).get().getCurrency());

        return view;

    }

}

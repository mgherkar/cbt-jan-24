package com.cbt.cbtjan24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/logistics")
public class LogisticsController
{
    @Autowired
    LogisticrfqRepository logisticrfqRepository;
    @Autowired
    PortRepository portRepository;
    @Autowired
    LogisticViewService logisticViewService;

    @PostMapping("save/logisticrfq/{originport}/{destinationport}/{orderid}")
    public ResponseEntity<String> createLogisticRfq
            (
            @PathVariable String originport,
            @PathVariable String destinationport,
            @PathVariable String orderid
            )
    {

        Logisticrfq logisticrfq = new Logisticrfq();
        logisticrfq.setRfqid(String.valueOf((int) (Math.random() * 10000)));
        logisticrfq.setOrderid(orderid);
        logisticrfq.setOriginport(originport);
        logisticrfq.setDestinationport(destinationport);
        logisticrfq.setStatus("OPEN");

        logisticrfqRepository.save(logisticrfq);

        return ResponseEntity.ok("LOGISTIC RFQ CREATED");

    }

    @GetMapping("get/port")
    public ResponseEntity<List<Port>> getPorts()
    {
        return ResponseEntity.ok(portRepository.findAll());
    }

    @GetMapping("get/logisticrfq")
    public ResponseEntity<List<LogisticView>> getLogisticRfq()
    {
        List<LogisticView> views =  logisticrfqRepository.findAll().stream().
                map(logisticrfq -> logisticViewService.createLogisticView(logisticrfq.getRfqid())).collect(Collectors.toList());

        return ResponseEntity.ok(views);
    }

}

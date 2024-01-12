package com.cbt.cbtjan24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController
{
    @Autowired
    PaymentwalletlinkRepository paymentwalletlinkRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    WalletRepository walletRepository;

    @PostMapping("make/payment/{paymentid}")
    public ResponseEntity<String> makePayment(@PathVariable String paymentid)
    {
        Paymentwalletlink paymentwalletlink = paymentwalletlinkRepository.findByPaymentrefid(paymentid);
        Paymentxn paymentxn = new Paymentxn();

        //DEBIT
        Integer payerBalance =  walletRepository.findById(paymentwalletlink.getPayerwallet()).get().getBalance();
        walletRepository.updateBalanceByWalletid((payerBalance - paymentwalletlink.getAmount()),paymentwalletlink.getPayerwallet());
        //CREDIT
        Integer payeeBalance =  walletRepository.findById(paymentwalletlink.getEscrowwallet()).get().getBalance();
        walletRepository.updateBalanceByWalletid((payeeBalance + paymentwalletlink.getAmount()), paymentwalletlink.getEscrowwallet());

        paymentRepository.updateStatusById("ESCROW",paymentid);

        return ResponseEntity.ok("Payment made to Escrow");
    }

}

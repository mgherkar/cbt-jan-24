package com.cbt.cbtjan24;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "paymentxns")
public class Paymentxn {
    @Id
    @Column(name = "txnid", nullable = false, length = 10)
    private String txnid;

    @Column(name = "paymenttype", length = 10)
    private String paymenttype;

    @Column(name = "pymntrefid", length = 10)
    private String pymntrefid;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "payerwallet", length = 10)
    private String payerwallet;

    @Column(name = "payeewallet", length = 10)
    private String payeewallet;

    @Column(name = "time")
    private Instant time;

}
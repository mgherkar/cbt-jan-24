package com.cbt.cbtjan24;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment
{
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "orderid", length = 10)
    private String orderid;

    @Column(name = "offerid", length = 10)
    private String offerid;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "paymentwalletlink", length = 10)
    private String paymentwalletlink;

}
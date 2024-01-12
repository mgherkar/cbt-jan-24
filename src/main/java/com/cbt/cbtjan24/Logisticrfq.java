package com.cbt.cbtjan24;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "logisticrfqs")
public class Logisticrfq
{
    @Id
    @Column(name = "rfqid", nullable = false, length = 10)
    private String rfqid;

    @Column(name = "originport", length = 10)
    private String originport;

    @Column(name = "destinationport", length = 10)
    private String destinationport;

    @Column(name = "orderid" , length = 10)
    private String orderid;

    @Column(name = "status", length = 10)
    private String status;

}
package com.cbt.cbtjan24;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ports")
public class Port {
    @Id
    @Column(name = "portid", nullable = false, length = 10)
    private String portid;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "capacity", length = 10)
    private String capacity;

}
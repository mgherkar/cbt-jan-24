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
@Table(name = "usernamewalletlinks")
public class Usernamewalletlink
{
    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "walletid", nullable = false, length = 50)
    private String walletid;

}
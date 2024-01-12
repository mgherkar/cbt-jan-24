package com.cbt.cbtjan24;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogisticView {

    String rfqid;
    String status;
    String productname;
    String originport;
    String destinationport;
    String currency;
    Integer amount;
    String buyername;
    String sellername;

}

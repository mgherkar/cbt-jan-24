package com.cbt.cbtjan24;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig
{

    @Bean
    @Scope("prototype")
    public MyNumber getNum1() // Defining a Domain Bean
    {
        MyNumber num = new MyNumber(); // Object Instantiation
        num.setI(78);
        return num;
    }

    @Bean
    public MyNumber getNum2() // Defining a Domain Bean
    {
        MyNumber num = new MyNumber(); // Object Instantiation
        num.setI(99);
        return num;
    }

}

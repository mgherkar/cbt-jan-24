package com.cbt.cbtjan24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MainRestController
{
    @Autowired              // Dependency Injection in Action
    @Qualifier("getNum1")
    MyNumber num1;

    @Autowired              // Dependency Injection in Action
    @Qualifier("getNum1")
    MyNumber num2;

    @Autowired              // Dependency Injection in Action
    @Qualifier("getNum2")
    MyNumber num3;

    @GetMapping("greet")
    public String greet()
    {
        return "Hello World! We are learning about Microservices";
    }

    @GetMapping("inc/num/1")
    public Integer incrementNum1()
    {
        num1.increment();
        return num1.getI();
    }

    @GetMapping("inc/num/2")
    public Integer incrementNum2()
    {
        num2.increment();
        return num2.getI();
    }
}

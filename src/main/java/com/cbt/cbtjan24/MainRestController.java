package com.cbt.cbtjan24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v0")
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
        return "Hello World! We are learning about Microservices"; // Return of the VIEW
    }

    @GetMapping("inc/num/1")    // Routing on the basis of URI to the appropriate MODEL: Business Logic
    public Integer incrementNum1()
    {
        num1.increment();                               // Business Logic
        return num1.getI(); // Return of the VIEW
    }

    @GetMapping("inc/num/2")
    public Integer incrementNum2()
    {
        num2.increment();
        return num2.getI(); // Return of the VIEW
    }
}

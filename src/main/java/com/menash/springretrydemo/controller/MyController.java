package com.menash.springretrydemo.controller;

import com.menash.springretrydemo.service.MyServiceVr1;
import com.menash.springretrydemo.service.MyServiceVr4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/v1/retry")
public class MyController {


    @Autowired
    MyServiceVr4 myService;

    @GetMapping(value = "/{id}")
    public void getId(@PathVariable int id) throws SQLException {

        System.out.println("hello");
        myService.retryServiceWithRandom(id);
    }
}

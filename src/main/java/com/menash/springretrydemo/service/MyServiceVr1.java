package com.menash.springretrydemo.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MyServiceVr1 {
    final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");


    @Retryable(value = SQLException.class)
    public void retryServiceWithRecovery(int id) throws SQLException {
        System.out.println("ATTEMPT!");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        throw new SQLException();
    }
}

package com.menash.springretrydemo.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MyServiceVr2 {

    @Retryable(value = SQLException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    void retryServiceWithCustomization(int id) throws SQLException{
        System.out.println("ATTEMPT!");
        throw new SQLException();
    }


    //add recover!
    @Recover
    void recover(SQLException e, int id) {
        System.out.println("RECOVER");
    }
}

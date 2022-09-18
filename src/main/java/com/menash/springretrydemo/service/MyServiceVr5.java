package com.menash.springretrydemo.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyServiceVr5 {
    final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
    @Retryable(
            value = {SQLException.class},
            maxAttempts = 5,
            backoff = @Backoff(random = true, delay = 100, maxDelay = 1000, multiplier = 2),
            listeners = {"retryListener"}
    )
    public void retryServiceWithRandom(int id) throws SQLException {
        System.out.println("ATTEMPT!");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        throw new SQLException();
    }

    @Recover
    void recover(SQLException e, int id) {
        System.out.println("IN RECOVER");
    }
}

package com.menash.springretrydemo.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MyServiceVr3 {
    @Retryable(value = SQLException.class, maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    void retryServiceWithExternalConfiguration(int id) throws SQLException{
        System.out.println("ATTEMPT!");
        throw new SQLException();
    }

    @Recover
    void recover(SQLException e, int id) {
        System.out.println("IN RECOVER");

    }
}

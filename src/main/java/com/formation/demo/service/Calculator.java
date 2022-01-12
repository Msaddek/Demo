package com.formation.demo.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    public int sum(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

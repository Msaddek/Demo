package com.formation.demo;

import com.formation.demo.service.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    private Calculator calculator = new Calculator();

    @Test
    void testsum() {

        Assertions.assertEquals(5, calculator.sum(2,3));
    }

}

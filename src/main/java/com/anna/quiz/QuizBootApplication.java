package com.anna.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizBootApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(QuizBootApplication.class, args);
    }
}

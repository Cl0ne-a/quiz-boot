package com.anna.quiz;

import com.anna.quiz.testinitializer.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizBootApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(QuizBootApplication.class, args);
        ctx.getBean(Initializer.class).init();
    }
}

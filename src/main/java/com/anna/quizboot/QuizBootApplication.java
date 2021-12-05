package com.anna.quizboot;

import com.anna.quizboot.quizservice.QuizService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizBootApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(QuizBootApplication.class, args);
        context.getBean(QuizService.class);
    }
}

package com.anna.quizboot;

import com.anna.quizboot.dao.QuizDao;
import com.anna.quizboot.dao.QuizDaoImpl;
import com.anna.quizboot.domain.Quiz;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizBootApplication.class, args);


    }
}

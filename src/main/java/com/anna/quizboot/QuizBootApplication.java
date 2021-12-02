package com.anna.quizboot;

import com.anna.quizboot.conf.LocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringBootApplication
public class QuizBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizBootApplication.class, args);

    }
}

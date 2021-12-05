package com.anna.quizboot.quizanswerservice;

import com.anna.quizboot.conf.LocaleManager;
import com.anna.quizboot.conf.LocalesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class AnswerServiceImplTest {
    @Mock
    private Scanner scanner;
    @Mock
    private LocaleManager localeManager;

    @Mock
    private LocalesRepository repository;

    private final AnswerServiceImpl service = new AnswerServiceImpl();

    @Test
    void getNames() {
        Mockito
                .when(service.getNames("request-name"))
                .thenReturn("Введите Ваше имя");
        verify(scanner, atLeastOnce()).nextLine();
    }

    @Test
    void getQuizAnswers() {
        Map<String, List<String>> questionBase = new HashMap<>();
        Map<String, String> answerBase = new HashMap<>();


    }
}
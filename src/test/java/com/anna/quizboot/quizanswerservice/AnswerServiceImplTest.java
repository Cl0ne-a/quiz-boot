package com.anna.quizboot.quizanswerservice;

import com.anna.quizboot.conf.LocaleManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AnswerServiceImplTest {
    @Mock
    private Scanner scanner;
    @Mock
    private LocaleManager localeManager;
    private AnswerServiceImpl service = new AnswerServiceImpl(localeManager);

    @Test
    void getNames() {
        var name = service.getNames();
        when(name).thenReturn("");
    }

    @Test
    void getQuizAnswers() {
    }
}
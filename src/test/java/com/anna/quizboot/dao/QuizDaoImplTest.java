package com.anna.quizboot.dao;

import com.anna.quizboot.domain.Quiz;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
@SpringBootTest
class QuizDaoImplTest {

    @Mock
    private Quiz quiz;
    @Mock
    private DaoHelper daoHelper;

    @Autowired
    private QuizDaoImpl quizDao = new QuizDaoImpl(quiz, daoHelper);

    @Test
    void quiz() {
        Mockito.when(quizDao.quiz()).thenReturn(new HashMap<>());
    }

    @Test
    void answerMap() {

    }
}
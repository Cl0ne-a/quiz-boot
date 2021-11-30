package com.anna.quizboot.dao;

import com.anna.quizboot.domain.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuizDaoImpl implements QuizDao {

    private final Quiz quiz;

    public QuizDaoImpl(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public Map<String, List<String>> quiz() {
        return null;
    }

    @Override
    public Map<String, String> answerMap() {
        return null;
    }
}

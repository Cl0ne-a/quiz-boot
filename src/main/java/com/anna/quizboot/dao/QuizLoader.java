package com.anna.quizboot.dao;

import com.anna.quizboot.dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuizLoader {
    @Autowired
    private QuizDao questionDao;

    public Map<String, List<String>> uploadQuiz() {
        return questionDao.quiz();
    }

    public Map<String, String> uploadAnswers() {
        return questionDao.answerMap();
    }
}

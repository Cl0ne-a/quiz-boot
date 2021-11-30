package com.anna.quizboot.dao;

import java.util.List;
import java.util.Map;

public interface QuizDao {
    Map<String, List<String>> quiz();

    Map<String, String> answerMap();
}

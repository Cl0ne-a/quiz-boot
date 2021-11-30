package com.anna.quizboot.quizanswerservice;

import java.util.List;
import java.util.Map;

public interface AnswerService {
    String getNames();
    List<String> getQuizAnswers(Map<String, List<String>> questionBase, Map<String, String> answerBase);
}

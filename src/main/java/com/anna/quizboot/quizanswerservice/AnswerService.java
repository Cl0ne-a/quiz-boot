package com.anna.quizboot.quizanswerservice;

import java.util.List;
import java.util.Map;

public interface AnswerService {
    String getNames(String nameRequest);
    List<String> getQuizAnswers(Map<String, List<String>> questionBase, Map<String, String> answerBase, String choice);

}

package com.anna.quiz.teacher;

import java.util.List;
import java.util.Map;

public interface Teacher {
    void requestName();
    String firstInstruction();
    Map<String, String> test(Map<String, List<String>> quiz);
    List<String> check(Map<String, String> stdIn, Map<String, String> correctAnswers);
}

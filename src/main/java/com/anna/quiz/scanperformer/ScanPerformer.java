package com.anna.quiz.scanperformer;

import java.util.List;
import java.util.Map;

public interface ScanPerformer {
    List<String> testStudent(Map<String, List<String>> quiz, Map<String, String> correctAnswers);
    Map<String, String> test(Map<String, List<String>> quiz);
    List<String> check(Map<String, String> stdIn, Map<String, String> correctAnswers);
}

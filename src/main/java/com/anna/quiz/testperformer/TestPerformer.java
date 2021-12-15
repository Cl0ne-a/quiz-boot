package com.anna.quiz.testperformer;

import java.util.List;
import java.util.Map;

public interface TestPerformer {
    List<String> testStudent(Map<String, List<String>> quiz, Map<String, String> correctAnswers);
}

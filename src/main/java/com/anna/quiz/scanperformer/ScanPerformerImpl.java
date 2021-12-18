package com.anna.quiz.scanperformer;

import com.anna.quiz.conf.LocalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class ScanPerformerImpl implements ScanPerformer {

    private final Scanner scanner;
    public static String name;

    @Autowired
    private LocalesRepository localesRepository;

    public ScanPerformerImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public List<String> testStudent(Map<String, List<String>> quiz, Map<String, String> correctAnswers) {
        Map<String, String> answersFromStudent = test(quiz);
        return check(answersFromStudent, correctAnswers);
    }

    @Override
    public Map<String, String> test(Map<String, List<String>> quiz) {
        Map<String, String> res = new HashMap<>();
        for (String question: quiz.keySet()) {
            System.out.printf(
                    question + localesRepository.localManagerGetChoice(),
                    quiz.get(question).get(0),
                    quiz.get(question).get(1));
            String response = scanner.nextLine();
            res.put(question, response);
        }
        return res;
    }

    @Override
    public List<String> check(Map<String, String> stdIn, Map<String, String> correctAnswers) {
        List<String> twrongTestAnswers = new ArrayList<>();
        stdIn.forEach((key, val) -> {
            if (!correctAnswers.get(key).equalsIgnoreCase(val)) {
                twrongTestAnswers.add(key.concat(" = ").concat(correctAnswers.get(key)));
            }
        });
        if (twrongTestAnswers.size() > 3) {
            System.out.printf(localesRepository.getAdvice(), name);
        } else {
            System.out.printf(localesRepository.getResult(),
                    name,
                    twrongTestAnswers.size(),
                    correctAnswers.size());
        }
        return twrongTestAnswers;
    }
}

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
public class ScanPerformerImpl implements ScanPerformer{

    private final Scanner scanner;
    public String name;

    @Autowired
    private LocalesRepository localesRepository;

    public ScanPerformerImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String requestName() {
        System.out.println(localesRepository.requestName());
        String name;
        while ((name = scanner.nextLine()).isEmpty()) {
            System.out.println(localesRepository.requestName());
        }
        this.name = name;
        return name;
    }

    @Override
    public void testStudent(Map<String, List<String>> quiz, Map<String, String> correctAnswers) {
        firstInstruction();
        Map<String, String> answersFromStudent = test(quiz);
        check(answersFromStudent, correctAnswers);
    }

    private void firstInstruction() {
        String name = requestName();
        System.out.printf(localesRepository.requestOptions(), name);
    }

    private Map<String, String> test(Map<String, List<String>> quiz) {
        Map<String, String> res = new HashMap<>();
        for (String question: quiz.keySet()) {
            System.out.printf(question + localesRepository.localManagerGetChoice(), quiz.get(question).get(0), quiz.get(question).get(1));
            String response = scanner.nextLine();
            res.put(question, response);
        }
        return res;
    }

    private void check(Map<String, String> stdIn, Map<String, String> correctAnswers) {
        List<String> testResult = new ArrayList<>();
        stdIn.forEach((key, val) -> {
            if (!correctAnswers.get(key).equalsIgnoreCase(val)) {
                testResult.add(key.concat(" = ").concat(correctAnswers.get(key)));
            }
        });
        if (testResult.size() > 3) {
            System.out.printf(localesRepository.getAdvice(), name);
        } else {
            System.out.printf(localesRepository.getResult(), name);
        }
        testResult.forEach(System.out::println);
    }
}

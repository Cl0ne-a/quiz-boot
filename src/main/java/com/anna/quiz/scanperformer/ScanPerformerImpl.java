package com.anna.quiz.scanperformer;

import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.scannerwrapper.ScannerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScanPerformerImpl implements ScanPerformer{

    public String name;

    @Autowired
    private ScannerWrapper scannerWrapper;
    @Autowired
    private LocalesRepository localesRepository;

    @Override
    public String requestName() {
        System.out.println(localesRepository.requestName());

        this.name = scannerWrapper.receiveName(localesRepository);
        return name;
    }

    public String receiveName() {
        String name;
        while ((name = scannerWrapper.getLine()).isEmpty()) {
            System.out.println(localesRepository.requestName());
        }
        return name;
    }

    @Override
    public List<String> testStudent(Map<String, List<String>> quiz, Map<String, String> correctAnswers) {
        firstInstruction();
        Map<String, String> answersFromStudent = test(quiz);
        return check(answersFromStudent, correctAnswers);
    }

    @Override
    public void firstInstruction() {
        String name = requestName();
        System.out.printf(localesRepository.requestOptions(), name);
    }

    @Override
    public Map<String, String> test(Map<String, List<String>> quiz) {
        Map<String, String> res = new HashMap<>();
        for (String question: quiz.keySet()) {
            System.out.printf(question + localesRepository.localManagerGetChoice(), quiz.get(question).get(0), quiz.get(question).get(1));
            String response = scannerWrapper.getLine();
            res.put(question, response);
        }
        return res;
    }

    @Override
    public List<String> check(Map<String, String> stdIn, Map<String, String> correctAnswers) {
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
        return testResult;
    }
}

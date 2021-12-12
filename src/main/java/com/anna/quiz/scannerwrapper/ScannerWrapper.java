package com.anna.quiz.scannerwrapper;

import com.anna.quiz.conf.LocalesRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ScannerWrapper {
    private final Scanner scanner;

    public ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public String getLine() {
        return scanner.nextLine();
    }

    public String receiveName(LocalesRepository localesRepository) {
        String name;
        while ((name = this.getLine()).isEmpty()) {
            System.out.println(localesRepository.requestName());
        }
        return name;
    }

    public Map<String, String> receiveStudentsAnswers(Map<String, List<String>> quiz, LocalesRepository localesRepository) {
        Map<String, String> res = new HashMap<>();
        for (String question: quiz.keySet()) {
            System.out.printf(question + localesRepository.localManagerGetChoice(), quiz.get(question).get(0), quiz.get(question).get(1));
            String response = getLine();
            res.put(question, response);
        }
        return res;
    }
}

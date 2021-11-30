package com.anna.quizboot.quizanswerservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class AnswerServiceImpl implements AnswerService{
    private final Scanner scanner;

    public AnswerServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getNames() {
        String name;
        while ((name = scanner.nextLine()).isEmpty()) {
            System.out.println("Please, enter your name");
        }
        return name;
    }

    @Override
    public List<String> getQuizAnswers(Map<String, List<String>> questionBase, Map<String, String> answerBase) {
        List<String> answers = new ArrayList<>();
        questionBase.forEach((key, value) -> {
            System.out.printf("\n%s?", key);
            System.out.printf("\nChooze: %s or %s\n", value.get(0), value.get(1));
            String response = scanner.next();
            if (response.equalsIgnoreCase(answerBase.get(key))) {
                answers.add(key + ": " + response);
            }
        });
        return answers;
    }
}

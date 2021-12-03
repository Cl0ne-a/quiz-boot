package com.anna.quizboot.quizanswerservice;

import com.anna.quizboot.conf.LocaleManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class AnswerServiceImpl implements AnswerService{
    private final Scanner scanner;
    private final LocaleManager localeManager;

    public AnswerServiceImpl(LocaleManager localeManager) {
        this.localeManager = localeManager;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getNames() {
        String name;
        while ((name = scanner.nextLine()).isEmpty()) {
            System.out.println(localeManager.getBundle().getString("name-request"));
        }
        return name;
    }

    @Override
    public List<String> getQuizAnswers(Map<String, List<String>> questionBase, Map<String, String> answerBase) {
        List<String> answers = new ArrayList<>();
        var choice = localeManager.getBundle().getString("choice");
        questionBase.forEach((key, valueList) -> {
            System.out.printf("\n%s?", key);
            System.out.printf(choice, valueList.get(0), valueList.get(1));
            String response = scanner.next();
            if (response.equalsIgnoreCase(answerBase.get(key))) {
                answers.add(key + ": " + response);
            }
        });
        return answers;
    }
}

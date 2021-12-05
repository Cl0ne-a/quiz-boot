package com.anna.quizboot.quizanswerservice;

import com.anna.quizboot.conf.LocaleManager;
import com.anna.quizboot.conf.LocalesRepository;
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
    public String getNames(String nameRequest) {

        String name;
        while ((name = scanner.nextLine()).isEmpty()) {
            System.out.println(nameRequest);
        }
        return name;
    }

    @Override
    public List<String> getQuizAnswers(Map<String, List<String>> questionBase,
                                       Map<String, String> answerBase, String choice) {
        List<String> answers = new ArrayList<>();

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

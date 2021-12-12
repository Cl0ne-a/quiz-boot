package com.anna.quiz.teacher;

import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.scannerwrapper.ScannerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CheckUpRoutine implements Teacher{
    private String name;

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

    @Override
    public String firstInstruction() {
        String name = requestName();
        return String.format(localesRepository.requestOptions(), name);
    }

    @Override
    public Map<String, String> test(Map<String, List<String>> quiz) {
        return scannerWrapper.receiveStudentsAnswers(quiz, localesRepository);
    }

    @Override
    public List<String> check(Map<String, String> studentsAnswers, Map<String, String> correctAnswers) {
        List<String> testResult = new ArrayList<>();
        studentsAnswers.forEach((key, val) -> {
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

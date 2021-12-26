package com.anna.quiz.teacher;

import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.domain.Quiz;
import com.anna.quiz.scannerwrapper.ScannerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value = "checkUpRoutine")
public class CheckUpRoutine implements Teacher{
//    public static String name;

    private final ScannerWrapper scannerWrapper;
    private final LocalesRepository localesRepository;

    @Autowired
    public CheckUpRoutine(ScannerWrapper scannerWrapper, LocalesRepository localesRepository) {
        this.scannerWrapper = scannerWrapper;
        this.localesRepository = localesRepository;
    }

    @Override
    public void requestName() {
        Quiz.studentsName = scannerWrapper.receiveName(localesRepository);
//        return ;
    }

    @Override
    public String firstInstruction() {
        return String.format(localesRepository.requestOptions(), Quiz.studentsName);
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
            System.out.printf(localesRepository.getAdvice(), Quiz.studentsName);
        } else {
            System.out.printf(localesRepository.getResult(), Quiz.studentsName);
        }
        return testResult;
    }
}

package com.anna.quiz.quizinitializer;

import com.anna.quiz.loaders.DataLoader;
import com.anna.quiz.scanperformer.ScanPerformerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class Initializer {
    @Qualifier("questionLoaderImpl")
    private final DataLoader<String, List<String>> questionLoader;

    @Qualifier("answersLoaderImpl")
    private final DataLoader<String, String> answersLoader;

    @Autowired
    private ScanPerformerImpl tester;

    public Initializer(@Qualifier(value = "questionLoaderImpl") DataLoader<String, List<String>> questionLoader,
                       @Qualifier(value = "answersLoaderImpl")DataLoader<String, String> answersLoader,
                       ScanPerformerImpl tester) {
        this.questionLoader = questionLoader;
        this.answersLoader = answersLoader;
        this.tester = tester;
    }

    public void init() {
        Map<String, List<String>> test = questionLoader.loadData();
        Map<String, String> answersFromDB = answersLoader.loadData();

        tester.testStudent(test, answersFromDB).forEach(System.out::println);
    }
}


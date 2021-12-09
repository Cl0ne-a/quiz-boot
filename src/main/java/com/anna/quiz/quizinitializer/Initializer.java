package com.anna.quiz.quizinitializer;

import com.anna.quiz.loaders.DataLoader;
import com.anna.quiz.scanperformer.ScanPerformerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class Initializer {
    @Autowired
    @Qualifier("questionLoaderImpl")
    private DataLoader<String, java.util.List<String>> questionLoader;
    @Autowired
    @Qualifier("answersLoaderImpl")
    private DataLoader<String, String> answersLoader;
    @Autowired
    private ScanPerformerImpl tester;

    @PostConstruct
    public void init() {
        Map<String, List<String>> test = questionLoader.loadData();
        Map<String, String> answersFromDB = answersLoader.loadData();

        tester.testStudent(test, answersFromDB).forEach(System.out::println);
    }
}

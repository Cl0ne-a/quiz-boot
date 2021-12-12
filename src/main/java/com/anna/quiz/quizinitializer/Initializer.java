package com.anna.quiz.quizinitializer;

import com.anna.quiz.loaders.DataLoader;
import com.anna.quiz.scanperformer.TestPerformerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Initializer {
    @Autowired
    @Qualifier("questionLoaderImpl")
    private DataLoader<String, List<String>> questionLoader;
    @Autowired
    @Qualifier("answersLoaderImpl")
    private DataLoader<String, String> answersLoader;
    @Autowired
    private TestPerformerImpl tester;

    public Initializer(@Qualifier(value = "questionLoaderImpl") DataLoader<String, List<String>> questionLoader,
                       @Qualifier(value = "answersLoaderImpl")DataLoader<String, String> answersLoader,
                       TestPerformerImpl tester
    ) {
        this.questionLoader = questionLoader;
        this.answersLoader = answersLoader;
        this.tester = tester;
    }

    public void init() {
        var test = questionLoader.loadData();
        var answersFromDB = answersLoader.loadData();

        tester.testStudent(test, answersFromDB).forEach(System.out::println);
    }
}


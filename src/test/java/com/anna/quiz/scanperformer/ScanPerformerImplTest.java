package com.anna.quiz.scanperformer;

import com.anna.quiz.conf.LocalesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.mockito.Mockito.when;

@SpringBootTest
class ScanPerformerImplTest {
    @Autowired
    private final ScanPerformer scanPerformer;

    @MockBean
    private Scanner scanner;

    @MockBean
    private LocalesRepository localesRepository;

    public ScanPerformerImplTest(ScanPerformer scanPerformer) {
        this.scanPerformer = scanPerformer;
    }

    @Test
    public void testStudent() {
        Map<String, List<String>> quiz = Map.of("A", List.of("A1", "A2"));
        Map<String, String> answerKeys = Map.of("A", "A1");

        when(scanPerformer.test(Map.of("A", List.of("A1", "A2")))).thenReturn(Map.of("A", "A2"));

        var actual = scanPerformer.testStudent(
                quiz,
                answerKeys);

        Assertions.assertEquals(List.of("A = A1"), actual);
    }
}
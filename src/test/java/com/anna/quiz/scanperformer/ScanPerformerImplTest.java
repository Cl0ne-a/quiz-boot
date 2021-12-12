package com.anna.quiz.scanperformer;

import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.scannerwrapper.ScannerWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
class ScanPerformerImplTest {

    @Mock
    private ScannerWrapper scannerWrapper;

    @Mock
    private LocalesRepository localesRepository;

    @Qualifier("scanPerformerImpl")
    @Autowired
    private ScanPerformer scanPerformer;

    public ScanPerformerImplTest(@Qualifier("scanPerformerImpl") ScanPerformer scanPerformer) {
        this.scanPerformer = scanPerformer;
    }

    @DisplayName("Возвращает введенное имя")
    @Test
    public void requestName(){
        String name = "Anna S";
        when(scannerWrapper.receiveName(localesRepository)).thenReturn(name);

        when(scanPerformer.requestName()).thenReturn(scannerWrapper.receiveName(localesRepository));

    }

    @DisplayName("Правильно тестирует введенные ответы")
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
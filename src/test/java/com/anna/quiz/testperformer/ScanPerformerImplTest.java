package com.anna.quiz.testperformer;

import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.scannerwrapper.ScannerWrapper;
import com.anna.quiz.teacher.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ScanPerformerImplTest {

    @Mock
    private ScannerWrapper scannerWrapper;

    @Mock
    private LocalesRepository localesRepository;

    @Mock
    @Qualifier("checkUpRoutine") private Teacher teacher;

    @Qualifier("testPerformerImpl")
    @Autowired
    private TestPerformer scanPerformer;

    public ScanPerformerImplTest(@Qualifier("testPerformerImpl") TestPerformer scanPerformer) {
        this.scanPerformer = scanPerformer;
    }

    @DisplayName("Правильно тестирует введенные ответы")
    @Test
    public void testStudent() {
        Map<String, List<String>> quiz = Map.of("A", List.of("A1", "A2"));
        Map<String, String> answerKeys = Map.of("A", "A2");
        Map<String, String> studentsAnswers = Map.of("A", "A1");

        String name = "Anna S";

        when(teacher.firstInstruction()).thenReturn("some info");
        when(teacher.requestName()).thenReturn(name);
        when(teacher.test(quiz)).thenReturn(studentsAnswers);
        when(teacher.check(studentsAnswers, answerKeys)).thenReturn(List.of("A2"));

        var actual = scanPerformer.testStudent(quiz, answerKeys);

        verify(teacher.firstInstruction(), atLeastOnce());
        verify(teacher.requestName(), atLeastOnce());
        verify(teacher.test(quiz), times(1));
        verify(teacher.check(studentsAnswers, answerKeys), times(1));

        Assertions.assertEquals(List.of("A = A2"), actual);
    }
}
package com.anna.quiz.testperformer;

import com.anna.quiz.teacher.CheckUpRoutine;
import com.anna.quiz.teacher.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@SpringBootTest
class TestPerformerImplTest {
    @Autowired
    private Teacher teacher;

    @Autowired
    private TestPerformer testPerformerImpl;

    @Configuration
    static class ContextConfiguration {

        @Bean(value = "checkUpRoutine")
        public Teacher teacher() {
            return mock(CheckUpRoutine.class);
        }

        @Bean(value = "testPerformerImpl")
        public TestPerformer testPerformer() {
            return new TestPerformerImpl(teacher());
        }
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

        var actual = testPerformerImpl.testStudent(quiz, answerKeys);

        verify(teacher, atLeastOnce()).firstInstruction();
        verify(teacher, times(1)).test(quiz);
        verify(teacher, times(1)).check(studentsAnswers, answerKeys);

        Assertions.assertEquals(List.of("A2"), actual);
    }
}
package com.anna.quiz.loaders;

import com.anna.quiz.domain.Quiz;
import com.anna.quiz.quizinitializer.Initializer;
import com.anna.quiz.scanperformer.ScanPerformerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@SpringBootTest
class AnswersLoaderImplTest {
    @MockBean Quiz quiz;

    @Autowired
    private AnswersLoaderImpl answerLoader;

    @MockBean
    @Qualifier("questionLoaderImpl")
    private static DataLoader<String, List<String>> questionLoader;
    @MockBean
    @Qualifier("answersLoaderImpl")
    private static DataLoader<String, String> answersLoader;
    @MockBean
    private static ScanPerformerImpl tester;

    @Configuration
    static class Configs {
        @Bean(value = "testInitializer")
        Initializer initializer() {
            return new Initializer(questionLoader, answersLoader, tester);
        }
    }

    @DisplayName("возвращает ди соответствующие данные в соответствующей локали: ")
    @Test
    void loadData() {
        Map<String, String> expected = Map.of(
                "Расстояние до Луны в км","384,400",
                "Население Аргентины в млн", "45,38",
                "Население США в млн", "335",
                "Порода собак из Китая", "мопс",
                "Население Шотландии в млн","Эдинбург");
        Map<String, String> actual = answerLoader.loadData();

        Assertions.assertEquals(expected, actual);
    }
}
package com.anna.quiz.loaders;

import com.anna.quiz.domain.Quiz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
class AnswersLoaderImplTest {
    @Mock
    private Quiz quiz;
    @Autowired
    @Qualifier("answersLoaderImpl")
    private DataLoader<String, String> answersLoader;

    public AnswersLoaderImplTest(@Qualifier(value = "answersLoaderImpl")DataLoader<String, String> answersLoader) {
        this.answersLoader = answersLoader;
    }

    @DisplayName("возвращает ли соответствующие данные для квиза: ")
    @Test
    void loadData() {
        Map<String, String> expected = Map.of(
                "Расстояние до Луны в км","384,400",
                "Население Аргентины в млн", "45,38",
                "Население США в млн", "335",
                "Порода собак из Китая", "мопс",
                "Население Шотландии в млн","Эдинбург");

        when(quiz.getAnswers()).thenReturn("src/test/resources/META-INF/answers.csv");
        Map<String, String> actual = answersLoader.loadData();

        Assertions.assertEquals(expected, actual);
    }
}
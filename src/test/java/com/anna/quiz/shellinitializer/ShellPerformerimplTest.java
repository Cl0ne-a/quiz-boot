package com.anna.quiz.shellinitializer;

import com.anna.quiz.conf.LocaleManager;
import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.conf.LocalesRepositoryImpl;
import com.anna.quiz.domain.Quiz;
import com.anna.quiz.loaders.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.event.EventListener;
import org.springframework.shell.Shell;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShellPerformerimplTest {

    @Autowired
    private Shell shell;

    private static final String STEP_A = "a";
    private static final String STEP_A_RESPONSE = "Введите Ваше имя в формате 'n <name>'";
    private static final String STEP_B = "b";
    private static final String STEP_B_RESPONSE = "Итак, %s, продолжить - ответь мне 'run +'";

    @Test
    void nameRequestTest() {
        String actual = (String) shell.evaluate(() -> STEP_A);
        assertThat(actual).isEqualTo(STEP_A_RESPONSE);
    }

    @Test
    void test() {
        String nameRequest = (String) shell.evaluate(() -> STEP_B);
        assertThat(nameRequest).isEqualTo(STEP_B_RESPONSE);
    }
}
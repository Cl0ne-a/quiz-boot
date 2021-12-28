package com.anna.quiz.shellinitializer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;

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
        String actual = (String) shell.evaluate(() -> STEP_B);
        assertThat(actual).isEqualTo(String.format(STEP_B_RESPONSE, "Mr. Incognito"));
    }
}
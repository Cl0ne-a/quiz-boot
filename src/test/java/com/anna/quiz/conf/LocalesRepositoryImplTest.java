package com.anna.quiz.conf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocalesRepositoryImplTest {

    @Autowired
    LocaleManager localeManager;

    @Test
    public void testRequestName() {
        String actual = localeManager.getBundle().getString("name-request");
        String expected = "Введите Ваше имя";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRequestOptions() {
        String actual = localeManager.getBundle().getString("options");
        String expected = "Отлично,%s, выберите ответ или поставьте \"-\":\n";
        Assertions.assertEquals(expected, actual);
    }
}
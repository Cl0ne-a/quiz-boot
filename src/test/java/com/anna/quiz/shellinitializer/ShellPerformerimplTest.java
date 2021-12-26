package com.anna.quiz.shellinitializer;

import com.anna.quiz.conf.LocaleManager;
import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.domain.Quiz;
import com.anna.quiz.loaders.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShellPerformerimplTest {

    @Value("${LocaleManager.lang}")
    String language;

    @Value("${LocaleManager.location}")
    String country;

    @Value("${LocaleManager.name}")
    String baseName;

    @MockBean
    LocaleManager manager;

    @MockBean
    Quiz quiz;

    @MockBean
    @Qualifier("questionLoaderImpl")
    DataLoader question;

    @MockBean
    @Qualifier("answersLoaderImpl")
    DataLoader answers;


    private static final String STEP_ZERO = "a";
    private static final String STEP_ZERO_RESPONSE = "Введите Ваше имя в формате \"n <ваше имя>\" без кавычек";
    private static final String STEP_ONE = "n";
    private static final String STEP_ONE_RESPONSE = "Отлично, guest, проходя тест, Вам  необходимо выбрать и напечатать правильный ответ, если готовы продолжать - поставьте \"run +\"";

    @SpyBean
    private final LocalesRepository localesRepository;

    @Autowired
    private Shell shell;

    ShellPerformerimplTest(LocalesRepository localesRepository) {
        this.localesRepository = localesRepository;
    }

    @BeforeEach
    void before() {
        when(localesRepository.requestName()).thenCallRealMethod();
    }

    @Test
    void test() {
        String startMessage = (String) shell.evaluate(() -> STEP_ZERO);
        assertThat(startMessage).isEqualTo(STEP_ZERO_RESPONSE);

        String nameRequest = (String) shell.evaluate(() -> STEP_ONE);
        assertThat(nameRequest).isEqualTo(STEP_ONE_RESPONSE);
    }
}
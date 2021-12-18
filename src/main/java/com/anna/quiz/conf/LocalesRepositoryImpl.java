package com.anna.quiz.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalesRepositoryImpl implements LocalesRepository {
    @Autowired
    private LocaleManager localeManager;

    @Override
    public String localManagerGetChoice() {
        return localeManager.getBundle().getString("choice");
    }
    @Override
    public String requestName() {
        return localeManager.getBundle().getString("name-request");
    }

    @Override
    public String requestOptions() {
        return localeManager.getBundle().getString("options");
    }

    @Override
    public String getResult() {
        return localeManager.getBundle().getString("result");
    }

    @Override
    public String getAdvice() {
        return localeManager.getBundle().getString("advice");
    }

    @Override
    public String readyToContinue() {
        return localeManager.getBundle().getString("ready");
    }

    @Override
    public String exit() {
        return localeManager.getBundle().getString("exit");
    }
}

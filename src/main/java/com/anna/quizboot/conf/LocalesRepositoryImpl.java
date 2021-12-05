package com.anna.quizboot.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalesRepositoryImpl implements LocalesRepository{
    @Autowired
    private LocaleManager localeManager;

    public String localManagerGetChoice() {
        return localeManager.getBundle().getString("choice");
    }

    public String requestName() {
        return localeManager.getBundle().getString("name-request");
    }

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
}

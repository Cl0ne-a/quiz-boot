package com.anna.quizboot.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class LocaleManager {

    @Value("${LocaleManager.language}")
    String language;
    @Value("${LocaleManager.country}")
    String country;
    @Value("${LocaleManager.bName}")
    String baseName;

    @Bean
    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle(this.baseName, new Locale(this.language, this.country));
    }
}
package com.anna.quizboot.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class LocaleResolver {

    @Value("${LocaleResolver.language}")
    String language;
    @Value("${LocaleResolver.country}")
    String country;
    @Value("${LocaleResolver.bName}")
    String baseName;

    @Bean
    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle(this.baseName, new Locale(this.language, this.country));
    }
}
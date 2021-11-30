package com.anna.quizboot.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class Quiz {
    @Value("${quiz.questions}")
    private String questions;

    @Value("${quiz.answers}")
    private String answers;
}
package com.anna.quizboot.exceptionshandler;

public class QuizException extends RuntimeException{
    public QuizException() {
    }

    public QuizException(String message) {
        super(message);
    }
}

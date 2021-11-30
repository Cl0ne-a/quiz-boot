package com.anna.quizboot.csvreader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public interface Reader {
    static java.io.Reader getReader(String source) {
        java.io.Reader questions = null;
        try {
            questions = new FileReader(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return questions;
    }
}

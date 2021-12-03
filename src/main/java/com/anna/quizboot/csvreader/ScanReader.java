package com.anna.quizboot.csvreader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public interface ScanReader {
    static Reader getReader(String source) {
        Reader questions = null;
        try {
            questions = new FileReader(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return questions;
    }
}

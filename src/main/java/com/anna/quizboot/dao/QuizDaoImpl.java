package com.anna.quizboot.dao;

import com.anna.quizboot.conf.LocaleResolver;
import com.anna.quizboot.domain.Quiz;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.anna.quizboot.csvreader.Reader.getReader;

@Service
public class QuizDaoImpl implements QuizDao {
    private final Quiz quiz;
    private final LocaleResolver localeResolver;

    public QuizDaoImpl(Quiz quiz, LocaleResolver localeResolver) {
        this.quiz = quiz;
        this.localeResolver = localeResolver;
    }

    @Override
    public Map<String, List<String>> quiz() {
        String source = this.quiz.questions;
        Reader questions = getReader(source);
        Iterable<CSVRecord> records = null;
        try {
            assert questions != null;
            records = CSVFormat.RFC4180.withHeader(
                            "question",
                            "opt1", "opt2")
                    .parse(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert records != null;
        return getQuizMap(records);
    }

    @Override
    public Map<String, String> answerMap() {
        String source = this.quiz.answers;
        Reader questions = getReader(source);
        Iterable<CSVRecord> records = null;
        try {
            assert questions != null;
            records = CSVFormat.RFC4180.withHeader(
                            "question",
                            "answer")
                    .parse(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert records != null;
        return getAnswerMap(records);
    }

    private Map<String, List<String>> getQuizMap(Iterable<CSVRecord> records) {
        Map<String, List<String>> qaMapper = new HashMap<>();
        assert records != null;
        var resourceBundle = localeResolver.getBundle();

        for (CSVRecord record : records) {
            String country = resourceBundle.getString(record.get("question"));

            String opt1 = resourceBundle.getString(record.get("opt1"));
            String opt2 = resourceBundle.getString(record.get("opt2"));
            qaMapper.put(country, List.of(opt1, opt2));
        }
        return qaMapper;
    }

    private Map<String, String> getAnswerMap(Iterable<CSVRecord> records) {
        Map<String, String> answerBase = new HashMap<>();
        assert records != null;
        var resourceBundle = localeResolver.getBundle();
        for (CSVRecord record : records) {
            String question = resourceBundle.getString(record.get("question"));
            String answer = resourceBundle.getString(record.get("answer"));
            answerBase.put(question, answer);
        }
        return answerBase;
    }
}

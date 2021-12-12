package com.anna.quiz.loaders;

import com.anna.quiz.conf.LocaleManager;
import com.anna.quiz.csvreader.ScanReader;
import com.anna.quiz.domain.Quiz;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionLoaderImpl implements DataLoader {
    @Autowired
    private Quiz quiz;
    @Autowired
    ScanReader reader;
    @Autowired
    private LocaleManager localeManager;
    @Value("${DataLoader.question}")
    private String question;
    @Value("${DataLoader.option-1}")
    private String optionA;
    @Value("${DataLoader.option-2}")
    private String optionB;

    @Override
    public String receiveDataSource() {
        return quiz.getQuestions();
    }

    @Override
    public Map<String, List<String>> loadData() {
        var questions = reader.readCsv(receiveDataSource());
        var iterableFromRecords = records(questions);

        return composeMap(iterableFromRecords);
    }

    private Iterable<CSVRecord> records(Reader questions) {
        Iterable<CSVRecord> records = null;
        try {
            assert questions != null;
            records = CSVFormat.RFC4180.withHeader(
                            "question",
                            "opt1",
                            "opt2")
                    .parse(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private Map<String, List<String>> composeMap(Iterable<CSVRecord> records) {
        Map<String, List<String>> quizMap = new HashMap<>();
        for (CSVRecord record : records) {
            String country = reader.getStringFromBundle(localeManager, record, question);

            String opt1 = reader.getStringFromBundle(localeManager, record, optionA);
            String opt2 = reader.getStringFromBundle(localeManager, record, optionB);
            quizMap.put(country, List.of(opt1, opt2));
        }
        return quizMap;
    }
}
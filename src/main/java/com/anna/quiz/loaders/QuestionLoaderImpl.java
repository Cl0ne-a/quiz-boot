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
    private LocaleManager localeManager;
    @Value("${DaoHelper.question}")
    private String question;
    @Value("${DaoHelper.option-1}")
    private String optionA;
    @Value("${DaoHelper.option-2}")
    private String optionB;

    @Override
    public Map<String, List<String>> loadData() {
        // get quiz source from quiz
        String source = quiz.questions;
        // read data
        var questions = ScanReader.readCsv(source);
        // transform to Map
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
            String country = ScanReader.getStringFromBundle(localeManager, record, question);

            String opt1 = ScanReader.getStringFromBundle(localeManager, record, optionA);
            String opt2 = ScanReader.getStringFromBundle(localeManager, record, optionB);
            quizMap.put(country, List.of(opt1, opt2));
        }
        return quizMap;
    }
}
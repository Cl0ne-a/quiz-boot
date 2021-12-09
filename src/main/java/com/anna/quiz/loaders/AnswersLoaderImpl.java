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
import java.util.Map;

@Service
public class AnswersLoaderImpl implements DataLoader{
    @Autowired
    private Quiz quiz;
    @Autowired
    private LocaleManager localeManager;
    @Value("${DataLoader.question}")
    private String question;
    @Value("${DataLoader.option-1}")
    private String answer;

    @Override
    public Map<String, String> loadData() {
        String source = quiz.getAnswers();

        var iterableFromRecords = records(source);
        return composeMap(iterableFromRecords);
    }

    private Iterable<CSVRecord> records(String source) {
        var answers = ScanReader.readCsv(source);
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.RFC4180.withHeader(
                            "question",
                            "opt1")
                    .parse(answers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private Map<String, String> composeMap(Iterable<CSVRecord> records) {
        Map<String, String> quizMap = new HashMap<>();
        for (CSVRecord record : records) {
            String request = ScanReader.getStringFromBundle(localeManager, record, question);

            String response = ScanReader.getStringFromBundle(localeManager, record, answer);
            quizMap.put(request, response);
        }
        return quizMap;
    }
}

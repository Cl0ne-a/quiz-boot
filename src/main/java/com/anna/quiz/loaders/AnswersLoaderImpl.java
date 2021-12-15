package com.anna.quiz.loaders;

import com.anna.quiz.conf.LocaleManager;
import com.anna.quiz.csvreader.ScanReader;
import com.anna.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnswersLoaderImpl implements DataLoader {
    private Quiz quiz;
    private ScanReader reader;
    private LocaleManager localeManager;
    private String question;
    private String answer;

    @Autowired
    public AnswersLoaderImpl(Quiz quiz,
                             ScanReader reader,
                             LocaleManager localeManager,
                             @Value("${DataLoader.question}") String question,
                             @Value("${DataLoader.option-1}") String answer) {
        this.quiz = quiz;
        this.reader = reader;
        this.localeManager = localeManager;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String receiveDataSource() {
        return quiz.getAnswers();
    }

    @Override
    public Map<String, String> loadData() {
        var iterableFromRecords = records(receiveDataSource());
        return composeMap(iterableFromRecords);
    }

    private Iterable<CSVRecord> records(String source) {
        var answers = reader.readCsv(source);
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
            String request = reader.getStringFromBundle(localeManager, record, question);

            String response = reader.getStringFromBundle(localeManager, record, answer);
            quizMap.put(request, response);
        }
        return quizMap;
    }
}

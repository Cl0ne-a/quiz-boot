package com.anna.quizboot.dao;

import com.anna.quizboot.conf.LocaleManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.anna.quizboot.csvreader.ScanReader.getReader;

@Service
public class DaoHelper {
    private final LocaleManager localeManager;
    @Value("${DaoHelper.question}")
    private String question;
    @Value("${DaoHelper.answer}")
    private String answer;
    @Value("${DaoHelper.option-1}")
    private String optionA;
    @Value("${DaoHelper.option-2}")
    private String optionB;


    public DaoHelper(LocaleManager localeManager) {
        this.localeManager = localeManager;
    }


    protected Iterable<CSVRecord> getCsvRecords(String source) {

        Reader questions = getReader(source);
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

    protected Map<String, List<String>> getQuizMap(Iterable<CSVRecord> records) {
        Map<String, List<String>> quizMap = new HashMap<>();
        assert records != null;

        for (CSVRecord record : records) {
            String country = getStringFromBundle(record, question);

            String opt1 = getStringFromBundle(record, optionA);
            String opt2 = getStringFromBundle(record, optionB);
            quizMap.put(country, List.of(opt1, opt2));
        }
        return quizMap;
    }

    protected Map<String, String> getAnswerMap(Iterable<CSVRecord> records) {
        Map<String, String> answerBase = new HashMap<>();
        assert records != null;
        for (CSVRecord record : records) {

            String question = getStringFromBundle(record, this.question);
            String answer = getStringFromBundle(record, this.answer);
            answerBase.put(question, answer);
        }
        return answerBase;
    }

    private String getStringFromBundle(CSVRecord record, String key) {
        var resourceBundle = localeManager.getBundle();
        return resourceBundle.containsKey(record.get(key))
                ? resourceBundle.getString(record.get(key))
                : "no information available";
    }
}

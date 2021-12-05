package com.anna.quizboot.dao;

import com.anna.quizboot.domain.Quiz;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import static com.anna.quizboot.csvreader.ScanReader.getReader;

@Service
public class QuizDaoImpl implements QuizDao {
    private final Quiz quiz;
    private final DaoHelper daoHelper;

    public QuizDaoImpl(Quiz quiz, DaoHelper daoHelper) {
        this.quiz = quiz;
        this.daoHelper = daoHelper;
    }

    @Override
    public Map<String, List<String>> quiz() {
        Iterable<CSVRecord> records = daoHelper.getCsvRecords(this.quiz.questions);
        assert records != null;
        return this.daoHelper.getQuizMap(records);
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

        return this.daoHelper.getAnswerMap(records);
    }
}

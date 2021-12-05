package com.anna.quizboot.dao;

import com.anna.quizboot.csvreader.ScanReader;
import com.anna.quizboot.domain.Quiz;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class QuizDaoImplTest {

    @Mock
    private Quiz quiz;
    @Mock
    private DaoHelper daoHelper;

    @Autowired
    private final QuizDaoImpl quizDao = new QuizDaoImpl(quiz, daoHelper);

    @Test
    void quiz() {
        String random = "random/String/path";
        Iterable<CSVRecord> records = () -> null;
        quizDao.quiz();
        verify(daoHelper.getCsvRecords(random), atLeastOnce());
        verify(daoHelper.getQuizMap(records), times(1));
    }

    @Test
    void answerMap() throws IOException {
        ScanReader.getReader("resource");
        CSVFormat.RFC4180.withHeader(
                        "question",
                        "answer")
                .parse(ScanReader.getReader("resource"));
        daoHelper.getAnswerMap(CSVFormat.RFC4180.withHeader(
                        "question",
                        "answer")
                .parse(ScanReader.getReader("resource")));
        quizDao.answerMap();
        verify(quiz.answers, times(1));
        verify(ScanReader.getReader("resource"), times(1));
    }
}
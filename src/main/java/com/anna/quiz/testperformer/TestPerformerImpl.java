package com.anna.quiz.testperformer;

import com.anna.quiz.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestPerformerImpl implements TestPerformer {

    private Teacher teacher;

    @Autowired
    public TestPerformerImpl( @Qualifier("checkUpRoutine")Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public List<String> testStudent(Map<String, List<String>> quiz, Map<String, String> correctAnswers) {
        System.out.println(teacher.firstInstruction());
        Map<String, String> answersFromStudent = teacher.test(quiz);
        return teacher.check(answersFromStudent, correctAnswers);
    }
}

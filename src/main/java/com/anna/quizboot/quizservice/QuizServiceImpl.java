package com.anna.quizboot.quizservice;

import com.anna.quizboot.dao.QuizDao;
import com.anna.quizboot.quizanswerservice.AnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class QuizServiceImpl implements QuizService{
    private final QuizDao questionDao;
    private final AnswerService answerService;

    public QuizServiceImpl(QuizDao questionDao, AnswerService answerService) {
        this.questionDao = questionDao;
        this.answerService = answerService;
    }

    @PostConstruct
    @Override
    public void questionInit() {
        Map<String, List<String>> questionBase = uploadQuiz();
        Map<String, String> answerBase = uploadAnswers();

        System.out.println("Enter your name and surname: \n");

        String name = answerService.getNames();
        System.out.printf("Ok, %s, now please print the correct answers or \"-\":\n", name);

        List<String> answers = answerService.getQuizAnswers(questionBase, answerBase);

        if (!answers.isEmpty() && answers.size() > questionBase.size() / 2) {
            System.out.printf("\n%s, test is passed with score %d of %d\n", name, answers.size(), questionBase.size());
            answers.forEach(System.out::println);
        } else {
            System.out.printf("%s, time to search some google :)", name);
        }
    }

    private Map<String, List<String>> uploadQuiz() {
        return questionDao.quiz();
    }

    private Map<String, String> uploadAnswers() {
        return questionDao.answerMap();
    }
}

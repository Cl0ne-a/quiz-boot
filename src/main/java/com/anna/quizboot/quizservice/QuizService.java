package com.anna.quizboot.quizservice;

import com.anna.quizboot.conf.LocaleManager;
import com.anna.quizboot.dao.QuizDao;
import com.anna.quizboot.quizanswerservice.AnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final QuizDao questionDao;
    private final AnswerService answerService;
    private final LocaleManager localeManager;


    public QuizService(QuizDao questionDao, AnswerService answerService, LocaleManager localeManager) {
        this.questionDao = questionDao;
        this.answerService = answerService;
        this.localeManager = localeManager;
    }

    @PostConstruct
    public void questionInit() {
        Map<String, List<String>> questionBase = uploadQuiz();
        Map<String, String> answerBase = uploadAnswers();

        var localBundle = localeManager.getBundle();

        System.out.printf("%s: \n", localBundle.getString("name-request"));

        String name = answerService.getNames();
        System.out.printf(localBundle.getString("options"), name);

        List<String> answers = answerService.getQuizAnswers(questionBase, answerBase);

        if (!answers.isEmpty() && answers.size() > questionBase.size() / 2) {
            System.out.printf((localBundle.getString("result") + "\n"), name, answers.size(), questionBase.size());
            answers.forEach(System.out::println);
        } else {
            System.out.printf(localBundle.getString("advice")+ "\n", name);
        }
    }

    private Map<String, List<String>> uploadQuiz() {
        return questionDao.quiz();
    }

    private Map<String, String> uploadAnswers() {
        return questionDao.answerMap();
    }
}

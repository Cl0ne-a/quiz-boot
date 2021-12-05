package com.anna.quizboot.quizservice;

import com.anna.quizboot.conf.LocalesRepository;
import com.anna.quizboot.dao.QuizLoader;
import com.anna.quizboot.quizanswerservice.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final QuizLoader quizLoader;
    private final AnswerService answerService;
    private final LocalesRepository localesRepository;

    public QuizService(QuizLoader quizLoader,
                       AnswerService answerService,
                       LocalesRepository localesRepository) {
        this.quizLoader = quizLoader;
        this.answerService = answerService;
        this.localesRepository = localesRepository;

        this.questionInit();
    }

    public void questionInit() {
        Map<String, List<String>> questionBase =
                quizLoader.uploadQuiz();
        Map<String, String> answerBase =
                quizLoader.uploadAnswers();

        System.out.printf("%s: \n", localesRepository.requestName());

        String name = answerService.getNames(localesRepository.requestName());
        System.out.printf(localesRepository.requestOptions(), name);

        List<String> answers = answerService
                .getQuizAnswers(
                        questionBase,
                        answerBase,
                        localesRepository.localManagerGetChoice());

        if (!answers.isEmpty() && answers.size() > questionBase.size() / 2) {
            System.out.printf((localesRepository.getResult() + "\n"), name, answers.size(), questionBase.size());
            answers.forEach(System.out::println);
        } else {
            System.out.printf(localesRepository.getAdvice() + "\n", name);
        }
    }
}

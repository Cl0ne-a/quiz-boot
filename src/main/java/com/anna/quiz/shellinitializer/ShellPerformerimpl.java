package com.anna.quiz.shellinitializer;

import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.domain.Quiz;
import com.anna.quiz.teacher.CheckUpRoutine;
import com.anna.quiz.testinitializer.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellPerformerimpl implements ShellPerformer{
    private final LocalesRepository localesRepository;
    private String READY = "+";
    private final Initializer initializer;

    @Autowired
    public ShellPerformerimpl(LocalesRepository localesRepository, Initializer initializer) {
        this.localesRepository = localesRepository;
        this.initializer = initializer;
    }

    @ShellMethod(key = "a", value = "step 0")
    public void preTest() {
        System.out.println(localesRepository.requestName());
    }

    @ShellMethod(key = "n", value = "step 1")
    @ShellMethodAvailability(value = "nameValue")
    public String firstInstruction(
            @ShellOption(defaultValue = "Mr. Incognito")
                    String name) {
        Quiz.studentsName = name;
        return String.format(localesRepository.readyToContinue(), name);
    }

    @ShellMethod(key = "run", value = "step 2")
    public void getResponse(@ShellOption(defaultValue = "-") String response) {
        READY = response;
        initializer.init();
    }

    private Availability nameValue() {
        return READY.equals("guest")
                ? Availability.unavailable(localesRepository.requestName())
                :Availability.available();
    }

    @ShellMethodAvailability("getResponse")
    private Availability ready() {
        return READY.equals("+")
                ? Availability.available()
                : Availability.unavailable("run 'exit' command");
    }
}

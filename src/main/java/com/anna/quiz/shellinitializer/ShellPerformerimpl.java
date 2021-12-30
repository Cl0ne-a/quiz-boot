package com.anna.quiz.shellinitializer;

import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.domain.Quiz;
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

    @ShellMethod(key = "a", value = "step 1")
    public String preTest() {
        return localesRepository.requestName();
    }

    @ShellMethod(key = "b", value = "step 2")
    public String firstInstruction(
            @ShellOption(defaultValue = "Mr. Incognito")
                    String name) {
        Quiz.studentsName = name;
        return String.format(localesRepository.readyToContinue(), name);
    }

    @ShellMethod(key = "run", value = "step 3")
    public void getResponse(@ShellOption(defaultValue = "-") String response) {
        READY = response;
        initializer.init();
    }

    @ShellMethodAvailability("getResponse")
    private Availability ready() {
        return READY.equals("+")
                ? Availability.available()
                : Availability.unavailable("run 'exit' command");
    }
}

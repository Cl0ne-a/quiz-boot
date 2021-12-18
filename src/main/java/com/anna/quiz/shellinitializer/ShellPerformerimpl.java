package com.anna.quiz.shellinitializer;

import com.anna.quiz.conf.LocalesRepository;
import com.anna.quiz.quizinitializer.Initializer;
import com.anna.quiz.scanperformer.ScanPerformerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellPerformerimpl implements ShellPerformer{
    private final LocalesRepository localesRepository;
    private String readyToContinue = "+";
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
    public String firstInstruction(@ShellOption(defaultValue = "guest") String name) {
        ScanPerformerImpl.name = name;
        System.out.printf(localesRepository.requestOptions(), name);
        return localesRepository.readyToContinue();
    }

    @ShellMethod(key = "run", value = "step 2")
    @ShellMethodAvailability(value = "ready")
    public void getResponse(@ShellOption(defaultValue = "-") String response) {
        readyToContinue = response;
        initializer.init();
    }

    private Availability nameValue() {
        return readyToContinue.equals("guest")
                ? Availability.unavailable(localesRepository.requestName())
                :Availability.available();
    }

    private Availability ready() {
        return readyToContinue.equals("-")
                ? Availability.unavailable(localesRepository.exit())
                :Availability.available();
    }
}

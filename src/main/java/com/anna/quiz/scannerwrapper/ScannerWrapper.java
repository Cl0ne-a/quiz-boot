package com.anna.quiz.scannerwrapper;

import com.anna.quiz.conf.LocalesRepository;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerWrapper {
    private final Scanner scanner;


    public ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public String getLine() {
        return scanner.nextLine();
    }

    public String receiveName(LocalesRepository localesRepository) {
        String name;
        while ((name = this.getLine()).isEmpty()) {
            System.out.println(localesRepository.requestName());
        }
        return name;
    }
}

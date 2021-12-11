package com.anna.quiz.scannerwrapper;

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
}

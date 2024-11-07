package org.example;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Hangman h = new Hangman(scanner);
        h.play(scanner);
        scanner.close();
        System.exit(0);
    }
}

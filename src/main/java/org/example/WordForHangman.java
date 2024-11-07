package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordForHangman {
    private String fileName = "E:\\lerning java\\sergeyJukov\\task_one\\src\\main\\java\\org\\example\\russian_nouns.txt";
    private List<String> wordsList = new ArrayList();
    private String wordForHangman;

    public WordForHangman(Scanner scanner) throws FileNotFoundException {
        scanner = new Scanner(new File(this.fileName));

        while(scanner.hasNextLine()) {
            this.wordForHangman = scanner.nextLine();
            this.wordsList.add(this.wordForHangman);
        }

        scanner.close();
        this.setWordForHangman();
    }

    public String getWordForHangman() {
        return this.wordForHangman;
    }

    public void setWordForHangman() {
        do {
            this.wordForHangman = (String)this.wordsList.get((int)(Math.random() * (double)this.wordsList.size()));
        } while(this.wordForHangman.length() < 4 || this.wordForHangman.length() > 8);

    }
}


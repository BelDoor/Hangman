package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HunterWord {
    private File file = new File("src/main/resources/russian_nouns.txt");
   
    private List<String> words = new ArrayList();
    private String wordForHangman;

    public HunterWord(Scanner scanner) throws FileNotFoundException {
        scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            this.words.add(scanner.nextLine());
        }

        scanner.close();
        setWordForHangman();
    }

    private int takeIndex() {
        return (int) (Math.random() * this.words.size());
    }

    private boolean verifyWord(String word){
        int sizeWord  = word.length();
        return ((sizeWord < 4) || (sizeWord > 8));
    }

    public void setWordForHangman() {
        do {
            wordForHangman = words.get(takeIndex());
        } while (verifyWord(wordForHangman));
    }

    public String getWordForHangman() {
        return this.wordForHangman;
    }

}


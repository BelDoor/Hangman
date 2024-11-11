package org.example;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Hangman {
    private HunterWord hunterWord;
    private AuditorInput auditor;
    private String riddleWord;
    private List<Character> gameBoard;
    private Set<Character> selectedLiterals;
    private Scanner scanner;

    private final String START_MESSAGE = "Для начала отправь (н) для выхода отпарвь (в)?";
    private final String START = "Введите одну букву от а до я!\n";
    private final String LAST_MESSAGE = "До встречи!";
    private int life;

    public Hangman(Scanner scanner){
        this.hunterWord = new HunterWord();
        auditor = new AuditorInput();
        this.selectedLiterals = new LinkedHashSet();
        this.scanner = scanner;
    }

    private void createBoard() {
        this.gameBoard = new ArrayList();

        for (int i = 0; i < this.riddleWord.length(); ++i) {
            this.gameBoard.add('_');
        }
    }

    private void prepareGame() {
        this.life = 6;
        this.hunterWord.setWordForHangman();
        this.riddleWord = this.hunterWord.getWordForHangman();
        this.createBoard();
        this.selectedLiterals.clear();
    }


    private void insertInBoard(char letter) {
        for (int i = 0; i < riddleWord.length(); ++i) {
            if (riddleWord.charAt(i) == letter) {
                gameBoard.set(i, letter);
            }
        }
    }

    private boolean proceedGame() {
        if (life > 0 && gameBoard.contains('_')) {
            return true;
        } else {
            return false;
        }
    }

    private void guessingTheWord() {
        while (proceedGame()) {
            System.out.print(START);
            String enter = scanner.nextLine();
            int letterNext = auditor.isLetterInRiddleWord(enter, selectedLiterals, riddleWord);
            if (letterNext == 1) {
                insertInBoard(auditor.getCorrectLetter());
            } else if (letterNext == -1) {
                --life;
            }
            picture();
        }
    }


    private void endForRound() {
        if (life > 0) {
            System.out.printf("Поздравляю Вы отгадали слово -> %s\n", riddleWord);
        } else {
            System.out.printf("Слово было -> %s\n", riddleWord);
        }
    }

    public void start() {
        while (true) {
            System.out.println(START_MESSAGE);
            String start = scanner.nextLine().toLowerCase();

            int st = auditor.checkStartOrExit(start);
            if (st == 1) {
                prepareGame();
                picture();
                guessingTheWord();
                endForRound();
            } else if (st == 0) {
                System.out.println(LAST_MESSAGE);
                scanner.close();
                System.exit(0);
                return;
            }
        }
    }

    private String stat() {
        return "Количество жизний: " + this.life +
                "\nЗагадонное слово: " + this.gameBoard +
                "\nПопытки: " + this.selectedLiterals;
    }

    private String gollow(){
        switch (life){
            case 6: return Gallow.getGallowSixLife();
            case 5: return Gallow.getGallowFiveLife();
            case 4: return Gallow.getGallowFoureLife();
            case 3: return Gallow.getGallowThreeLife();
            case 2: return Gallow.getGallowTwoLife();
            case 1: return Gallow.getGallowOneLife();
            case 0: return Gallow.getGallowZeroLife();
        }
        return "";
    }

    private void picture(){
        System.out.printf("Статистика:\n%s\n---------\n %s\n",stat(),gollow());
    }
}

package org.example;

import java.io.FileNotFoundException;
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
    private final String START = "Ввелите одну букву от а до я!\n";
    private final String LAST_MESSAGE = "До встречи!";
    private int life;

    public Hangman(Scanner scanner) throws FileNotFoundException {
        this.hunterWord = new HunterWord(scanner);
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
            System.out.println(this.gallows());
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
            String start = scanner.nextLine();

            int st = auditor.checkStartOrExit(start);
            if (st == 1) {
                prepareGame();
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

    public String gallows() {
        return "Количество жизний: " + this.life +
                "\nЗагадонное слово: " + this.gameBoard +
                "\nПопытки: " + this.selectedLiterals +
                "\n  +---+\n" +
                "  |   |\n  " +
                (this.life < 6 ? "O" : " ") + "   |\n " +
                (this.life < 5 ? "/" : " ") + (this.life < 4 ? "|" : " ") + (this.life < 3 ? "\\" : " ") + "  |\n " +
                (this.life < 2 ? "/" : " ") + " " + (this.life < 1 ? "\\" : " ") +
                "  |\n=======\n";
    }
}

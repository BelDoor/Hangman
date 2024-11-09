package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Hangman {
    private WordForHangman wordForHangman;
    private String riddleWord;
    private List<Character> gameBoard;
    private Set<Character> selectedLiterals;
    private int life = 6;

    public Hangman(Scanner scanner) throws FileNotFoundException {
        this.wordForHangman = new WordForHangman(scanner);
        this.riddleWord = this.wordForHangman.getWordForHangman();
        this.createBoard();
        this.selectedLiterals = new LinkedHashSet();
    }

    private void createBoard() {
        this.gameBoard = new ArrayList();

        for(int i = 0; i < this.riddleWord.length(); ++i) {
            this.gameBoard.add('_');
        }
    }

    private void prepareGame() {
        this.life = 6;
        this.wordForHangman.setWordForHangman();
        this.riddleWord = this.wordForHangman.getWordForHangman();
        this.createBoard();
        this.selectedLiterals.clear();
    }

    public void play(Scanner input) {
        while(true) {
            System.out.println("Для начала отправь (н) для выхода отпарвь (в)?");
            InputCheck in = new InputCheck();
            int start = in.checkStartOrExit(input.next().toLowerCase());
            if (start == 1) {
                System.out.println("Количество жизний: " + this.life + "\nЗагадонное слово: " + this.gameBoard.toString() + "\n");

                while(this.life > 0 && this.gameBoard.contains('_')) {
                    this.life += in.isCorrectLitter(input, this.selectedLiterals, this.riddleWord, this.gameBoard);
                    System.out.println(this.toString());
                }

                System.out.println(this.life > 0 ? "Поздравляю вы отгадали " + this.riddleWord : "Слово было " + this.riddleWord);
            } else if (start == 0) {
                System.out.println("До встречи!");
                input.close();
                System.exit(0);
                return;
            }

            this.prepareGame();
        }
    }

    public String toString() {
        return "Количество жизний: " + this.life + "\nЗагадонное слово: " + this.gameBoard + "\nПопытки: " + this.selectedLiterals + "\n  +---+\n  |   |\n  " + (this.life < 6 ? "O" : " ") + "   |\n " + (this.life < 5 ? "/" : " ") + (this.life < 4 ? "|" : " ") + (this.life < 3 ? "\\" : " ") + "  |\n " + (this.life < 2 ? "/" : " ") + " " + (this.life < 1 ? "\\" : " ") + "  |\n=======\n";
    }
}

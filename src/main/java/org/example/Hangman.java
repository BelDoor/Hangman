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
    private List<Character> riddleBoard;
    private Set<Character> selectedLiteral;
    private int life = 6;

    public Hangman(Scanner scanner) throws FileNotFoundException {
        this.wordForHangman = new WordForHangman(scanner);
        this.riddleWord = this.wordForHangman.getWordForHangman();
        this.riddleBoardNext();
        this.selectedLiteral = new LinkedHashSet();
    }

    private void riddleBoardNext() {
        this.riddleBoard = new ArrayList();

        for(int i = 0; i < this.riddleWord.length(); ++i) {
            this.riddleBoard.add('_');
        }

    }

    private void newGame() {
        this.life = 6;
        this.wordForHangman.setWordForHangman();
        this.riddleWord = this.wordForHangman.getWordForHangman();
        this.riddleBoardNext();
        this.selectedLiteral.clear();
    }

    public void play(Scanner input) {
        while(true) {
            System.out.println("Для начала отправь (н) для выхода отпарвь (в)?");
            InputCheck in = new InputCheck();
            int i = in.inOrOutСheck(input.next().toLowerCase());
            if (i == 1) {
                int var10001 = this.life;
                System.out.println("Количество жизний: " + var10001 + "\nЗагадонное слово: " + this.riddleBoard.toString() + "\n");

                while(this.life > 0 && this.riddleBoard.contains('_')) {
                    this.life += in.isCorrectLitter(input, this.selectedLiteral, this.riddleWord, this.riddleBoard);
                    System.out.println(this.toString());
                }

                System.out.println(this.life > 0 ? "Поздравляю вы отгадали " + this.riddleWord : "Слово было " + this.riddleWord);
            } else if (i == 0) {
                System.out.println("До встречи!");
                return;
            }

            this.newGame();
        }
    }

    public String toString() {
        return "Количество жизний: " + this.life + "\nЗагадонное слово: " + this.riddleBoard + "\nПопытки: " + this.selectedLiteral + "\n  +---+\n  |   |\n  " + (this.life < 6 ? "O" : " ") + "   |\n " + (this.life < 5 ? "/" : " ") + (this.life < 4 ? "|" : " ") + (this.life < 3 ? "\\" : " ") + "  |\n " + (this.life < 2 ? "/" : " ") + " " + (this.life < 1 ? "\\" : " ") + "  |\n=======\n";
    }
}

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
    private int life;
    
    
    private static final String START_MESSAGE = "Для начала отправь (н) для выхода отпарвь (в)?";
    private static final String START = "Введите одну букву от а до я!\n";
    private static final String LAST_MESSAGE = "До встречи!";
    

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


    private void insertInBoard(char correctLetter) {
        for (int i = 0; i < riddleWord.length(); ++i) {
            if (riddleWord.charAt(i) == correctLetter) {
                gameBoard.set(i, correctLetter);
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
            String startMessage = scanner.nextLine().toLowerCase();

            int startNumber = auditor.checkInputForStart(startMessage);
            if (startNumber == 1) {
                prepareGame();
                picture();
                guessingTheWord();
                endForRound();
            } else if (startNumber == 0) {
                System.out.println(LAST_MESSAGE);
                scanner.close();
                System.exit(0);
                return;
            }
        }
    }

    private String stat() {
        return String.format("Количество жизний: %d\nЗагадонное слово: %s\nПопытки: %s", this.life,
                this.gameBoard, this.selectedLiterals);
    }

    private String gollow() {
        return GallowState.getGallowPicture(life);
    }

    private void picture(){
        System.out.printf("Статистика:\n%s\n---------\n %s\n",stat(),gollow());
    }
}

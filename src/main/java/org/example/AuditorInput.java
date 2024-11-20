package org.example;

import java.util.Set;

public class AuditorInput {

    private static final String REPEAT_INPUT = "Повторите попытку!";

    private char correctLetter;

    private boolean checkForOneLetter(String enterLine) {
        if (enterLine.length() == 1) {
            return true;
        } else {
            System.out.printf("Вы ввели больше одной буквы!\n%s\n", REPEAT_INPUT);
            return false;
        }
    }

    private boolean checkForCyrillic(char letter) {
        if ((letter < 'а' || letter > 'я') && letter != 'ё') {
            System.out.printf("Вы ввели не кирилицу!\n%s\n", REPEAT_INPUT);
            return false;
        } else {
            return true;
        }
    }

    private void setCorrectLetter(char correctLetter) {
        this.correctLetter = correctLetter;
    }

    private boolean isCorrectInput(String enter) {
        if (this.checkForOneLetter(enter) && this.checkForCyrillic(enter.toLowerCase().charAt(0))) {
            setCorrectLetter(enter.toLowerCase().charAt(0));
            return true;
        } else {
            return false;
        }
    }

    private boolean isCorrectLitter(String enter, Set<Character> letterEntered) {
        if (isCorrectInput(enter) && letterEntered.add(correctLetter)) {
            return true;
        } else {
            return false;
        }
    }

    public char getCorrectLetter() {
        return correctLetter;
    }

    private boolean checkLetterInWord(String riddleWord) {
        if (riddleWord.contains("" + this.correctLetter)) {
            System.out.printf("Откройте букву! -> %s\n", correctLetter);
            return true;
        } else {
            System.out.printf("Мимо!\nМинус жизнь!!!\n%s\n", REPEAT_INPUT);
            return false;
        }
    }


    //accepts letter input
    //checks the letter for correct input
    //checks whether the entered letter is in the riddle word
    //if the letter is in riddleWord returns 1
    //if the letter is not in the word -1
    //if the input is incorrect or repeated 0
    public int isLetterInRiddleWord(String enter, Set<Character> selectedLetters, String riddleWord) {
        if (isCorrectLitter(enter, selectedLetters)) {
            if (checkLetterInWord(riddleWord)) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    /**
     * @param startMessageer
     * @return if input corresponds to start returns 1
     * @return if input is incorrect -1
     * @return if input corresponds to output 0
     */
    public int checkInputForStart(String startMessageer) {
        if (startMessageer.equals("н")) {
            return 1;
        } else if (startMessageer.equals("в")) {
            return 0;
        }
        return -1;
    }
}

package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AuditorInput {

    private final String REPEAT_INPUT = "Повторите попытку!";

    private char correctLetter;

    public AuditorInput() {
    }

    private boolean checkForOneLetter(String s) {
        if (s.length() == 1) {
            return true;
        } else {
            System.out.printf("Вы ввели больше одной буквы!\n%s\n", REPEAT_INPUT);
            return false;
        }
    }

    private boolean checkForCyrillic(char ch) {
        if ((ch < 'а' || ch > 'я') && ch != 'ё') {
            System.out.printf("Вы ввели не кирилицу!\n%s\n", REPEAT_INPUT);
            return false;
        } else {
            return true;
        }
    }

    private void setCorrectLetter(char correctLetter) {
        this.correctLetter = correctLetter;
    }

    private boolean isCorrectInput(String enter){
        if (this.checkForOneLetter(enter) && this.checkForCyrillic(enter.toLowerCase().charAt(0))) {
            setCorrectLetter(enter.toLowerCase().charAt(0));
            return true;
        } else {
            return false;
        }
    }

    private boolean isCorrectLitter(String enter, Set<Character> letterEntered){
        if(isCorrectInput(enter) && letterEntered.add(correctLetter)){
            return true;
        } else {
            return false;
        }
    }

    public char getCorrectLetter() {
        return correctLetter;
    }

    //метод на проверку наличия введенной буквы в загадоном слове
    private boolean checkLetterInWord(String riddleWord){
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
    //вызвать изиметода который передает вход
    public int isLetterInRiddleWord(String enter, Set<Character> selectedLetters, String riddleWord){
        if(isCorrectLitter(enter, selectedLetters)){
            if(checkLetterInWord(riddleWord)){
                return 1;
            }else {
                return -1;
            }
        } else {
            return 0;
        }
    }


    private int checkInputForStart(String enter){
        if (enter.charAt(0) == 'н') {
            return 1;
        } else if (enter.charAt(0) == 'в') {
            return 0;
        }
        return -1;
    }

    //accepts letter input
    //checks letter for correct input
    //checks letter for correspondence to start
    //if input corresponds to start returns 1
    //if input is incorrect -1
    //if input corresponds to output 0
    public int checkStartOrExit(String enter) {
        if (isCorrectInput(enter) ) {
            return checkInputForStart(enter);
        }
        return -1;
    }
}

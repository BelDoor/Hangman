package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputCheck {
    private char correctLetter;

    public InputCheck() {
    }

    private boolean oneLetterCheck(String s) {
        if (s.length() == 1) {
            return true;
        } else {
            System.out.println("Вы ввели больше одной буквы!");
            return false;
        }
    }

    private boolean сyrillicСheck(char ch) {
        if ((ch < 1072 || ch > 1103) && ch != 1105) {
            System.out.println("Вы ввели не кирилицу!");
            return false;
        } else {
            return true;
        }
    }

    public int isCorrectLitter(Scanner input, Set<Character> letterEntered, String riddleWord, List<Character> riddleBoard) {
        if (this.isCorrectInput(input) && letterEntered.add(this.correctLetter)) {
            if (riddleWord.contains("" + this.correctLetter)) {
                for(int i = 0; i < riddleWord.length(); ++i) {
                    if (riddleWord.charAt(i) == this.correctLetter) {
                        riddleBoard.set(i, this.correctLetter);
                    }
                }

                return 0;
            } else {
                System.out.println("НЕТ ТАКОЙ БУКВЫ!!! МИНУС ЖИЗНЬ!!!");
                return -1;
            }
        } else {
            System.out.println("Вы уже вводили эту буквы! Повторите попытку!");
            return 0;
        }
    }

    private boolean isCorrectInput(Scanner input) {
        System.out.print("Введите одну букву -> ");
        String enter = input.next();
        if (this.oneLetterCheck(enter) && this.сyrillicСheck(enter.toLowerCase().charAt(0))) {
            this.correctLetter = enter.charAt(0);
            return true;
        } else {
            System.out.println("Повторите попытку!");
            return false;
        }
    }

    public int inOrOutСheck(String s) {
        if (this.oneLetterCheck(s)) {
            if (s.charAt(0) == 1085) {
                System.out.println("Начали!!!");
                return 1;
            }

            if (s.charAt(0) == 1074) {
                return 0;
            }
        }

        return -1;
    }
}

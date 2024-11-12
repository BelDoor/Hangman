package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HunterWord {
    private String pathFile = "russian_nouns.txt";
    private List<String> words = new ArrayList(51301);
    private String wordForHangman;

    public HunterWord(){
        readNouns();
        setWordForHangman();
    }

    private InputStream inputNouns() throws IllegalAccessException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(pathFile);
        if(is==null){
            throw new IllegalAccessException("file not found!" + pathFile);
        }else {
        return is;
        }
    }

    private void readNouns() {

            try(InputStreamReader streamReader = new InputStreamReader(inputNouns(),StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)){

                String line = reader.readLine();
                while (line != null){
                    words.add(line);
                    line = reader.readLine();
                }
            }catch (IOException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

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


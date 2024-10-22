package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;


public class HangmanGameCheatingComputer extends HangmanGame {
    private List<String> myRemainingWords;

    public HangmanGameCheatingComputer(HangmanDictionary dictionary, int wordLength, int numGuesses) {
        super(dictionary, wordLength, numGuesses);
    }

    @Override
    protected void makeSecretWord(HangmanDictionary dictionary, int wordLength) {
        myRemainingWords = dictionary.getWords(wordLength);
        mySecretWord = dictionary.getRandomWord(wordLength).toLowerCase();
        myDisplayWord = new DisplayWord(mySecretWord);
    }

    @Override
    protected char makeGuess() {
        String guess = ConsoleReader.promptString("Make a guess: ");
        return guess.toLowerCase().charAt(0);
    }

    @Override
    protected boolean checkGuessInSecret(char guess) {
        cheat(guess);
        boolean isCorrectGuess = mySecretWord.indexOf(guess) >= 0;
        if (isCorrectGuess) {
            myDisplayWord.update(guess, mySecretWord);
        }
        return isCorrectGuess;
    }

    private void cheat(char guess) {
        HashMap<DisplayWord, List<String>> templatedWords = new HashMap<>();
        for (String w : myRemainingWords) {
            DisplayWord template = new DisplayWord(myDisplayWord);
            template.update(guess, w);
            if (!templatedWords.containsKey(template)) {
                templatedWords.put(template, new ArrayList<>());
            }
            templatedWords.get(template).add(w);
        }

        int max = 0;
        DisplayWord maxKey = new DisplayWord("");
        for (Map.Entry<DisplayWord, List<String>> entry : templatedWords.entrySet()) {
            if (entry.getValue().size() > max) {
                max = entry.getValue().size();
                maxKey = entry.getKey();
            }
        }

        myRemainingWords = templatedWords.get(maxKey);
        Collections.shuffle(myRemainingWords);
        mySecretWord = myRemainingWords.get(0);
        myDisplayWord = maxKey;
    }
}
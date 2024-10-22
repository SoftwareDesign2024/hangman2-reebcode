package game;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;

public class HangmanGameInteractiveGuesser extends HangmanGame {
    public HangmanGameInteractiveGuesser(HangmanDictionary dictionary, int wordLength, int numGuesses) {
        super(dictionary, wordLength, numGuesses);
    }

    @Override
    protected void makeSecretWord(HangmanDictionary dictionary, int wordLength) {
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
        boolean isCorrectGuess = mySecretWord.indexOf(guess) >= 0;
        if (isCorrectGuess) {
            myDisplayWord.update(guess, mySecretWord);
        }
        return isCorrectGuess;
    }
}
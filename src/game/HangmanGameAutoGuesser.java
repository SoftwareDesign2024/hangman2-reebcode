package game;

import util.DisplayWord;
import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * where the computer guesses letters based on a predictable pattern.
 *
 * @author Robert C. Duvall
 */
public class HangmanGameAutoGuesser extends HangmanGame {
    private static final String LETTERS_ORDERED_BY_FREQUENCY = "etaoinshrldcumfpgwybvkxjqz";
    private int myIndex;

    public HangmanGameAutoGuesser(HangmanDictionary dictionary, int wordLength, int numGuesses) {
        super(dictionary, wordLength, numGuesses);
        myIndex = 0;
    }

    @Override
    protected void makeSecretWord(HangmanDictionary dictionary, int wordLength) {
        mySecretWord = dictionary.getRandomWord(wordLength).toLowerCase();
        myDisplayWord = new DisplayWord(mySecretWord);
    }

    @Override
    protected char makeGuess() {
        return LETTERS_ORDERED_BY_FREQUENCY.charAt(myIndex++);
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

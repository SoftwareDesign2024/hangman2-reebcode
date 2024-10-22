/*
 * Reed Gatfield
 * 
 */
package game;

import util.HangmanDictionary;
import util.DisplayWord;

public abstract class HangmanGame {
    protected static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    protected String mySecretWord;
    protected DisplayWord myDisplayWord;
    protected StringBuilder myLettersLeftToGuess;
    protected int myNumGuessesLeft;

    public HangmanGame(HangmanDictionary dictionary, int wordLength, int numGuesses) {
        myLettersLeftToGuess = new StringBuilder(ALPHABET);
        makeSecretWord(dictionary, wordLength);
        myNumGuessesLeft = numGuesses;
    }

    protected abstract void makeSecretWord(HangmanDictionary dictionary, int wordLength);
    protected abstract char makeGuess();
    protected abstract boolean checkGuessInSecret(char guess);

    public void play() {
        boolean gameOver = false;
        while (!gameOver) {
            printStatus();
            
            char guess = makeGuess();
            if (isNewGuess(guess)) {
                recordGuess(guess);
                if (!checkGuessInSecret(guess)) {
                    myNumGuessesLeft -= 1;
                }
                
                if (isGameLost()) {
                    System.out.println("YOU ARE HUNG!!!");
                    gameOver = true;
                }
                else if (isGameWon()) {
                    System.out.println("YOU WIN!!!");
                    gameOver = true;
                }
            }
        }
        System.out.println("The secret word was " + mySecretWord);
    }

    protected boolean isNewGuess(char guess) {
        return myLettersLeftToGuess.indexOf("" + guess) >= 0;
    }

    protected void recordGuess(char guess) {
        int index = myLettersLeftToGuess.indexOf("" + guess);
        if (index >= 0) {
            myLettersLeftToGuess.deleteCharAt(index);
        }
    }

    protected boolean isGameWon() {
        return myDisplayWord.equals(mySecretWord);
    }

    protected boolean isGameLost() {
        return myNumGuessesLeft == 0;
    }

    protected void printStatus() {
        System.out.println(myDisplayWord);
        System.out.println("# misses left = " + myNumGuessesLeft);
        System.out.println("letters not yet guessed = " + myLettersLeftToGuess);
        System.out.println();
    }
}

    
/*
 * Reed Gatfield
 * 
 */
package game;

public abstract class Guesser {
    protected static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    protected StringBuilder myLettersLeftToGuess;

    public Guesser() {
        myLettersLeftToGuess = new StringBuilder(ALPHABET);
    }

    public boolean isNewGuess(char guess) {
        return myLettersLeftToGuess.indexOf(String.valueOf(guess)) >= 0;
    }
    
    public abstract char makeGuess();

    public void recordGuess(char guess) {
        int index = myLettersLeftToGuess.indexOf(String.valueOf(guess));
        if (index >= 0) {
            myLettersLeftToGuess.deleteCharAt(index);
        }
    }

    public String getLettersLeftToGuess() {
        return myLettersLeftToGuess.toString();
    }
}
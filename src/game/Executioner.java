/*
 * Reed Gatfield
 * 
 */
package game;

import util.DisplayWord;
import util.HangmanDictionary;

public abstract class Executioner {
	protected String mySecretWord;
	protected DisplayWord myDisplayWord;

	public abstract void makeSecretWord(HangmanDictionary dictionary, int wordLength);
	public abstract boolean checkGuessInSecret(char guess);

	public boolean isWordGuessed() {
		return myDisplayWord.equals(mySecretWord);
	}

	public String getDisplayWord() {
		return myDisplayWord.toString();
	}

	public String getSecretWord() {
		return mySecretWord;
	}
}
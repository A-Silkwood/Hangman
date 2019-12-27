package com.silkwood.hangman;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.silkwood.hangman.userinterface.GameMenu;
import com.silkwood.hangman.userinterface.MainMenu;

public class Hangman {
	//name of program
	public static final String PROGRAM_NAME = "Hangman v2.0";
	
	//constants for calculation
	private static final int LETTERS = 26;
	private static final int LETTER_A = 'A';
	private static final int LETTER_Z = 'Z';
	private static final int MAX_MISTAKES = 6;
	private static final int MAX_LENGTH = 12;
	
	//processing
	private static Scanner input;
	private static String word;
	private static int mistakes;
	private static boolean[] usedLetters;
	private static boolean[] correctLetters;
	private static File file;
	private static ArrayList<String> wordBank;
	
	public static boolean gameOver;
	
	//window
	private static JFrame mainMenu;
	private static JFrame gameMenu;
	
	
	public static void main(String[] args) {
		System.out.println("Hangman v2.0 is starting");
		mistakes = 0;
		gameOver = false;
		usedLetters = new boolean[LETTERS];
		correctLetters = new boolean[LETTERS];
		word = "";
		file = new File("assets/default.txt");
		System.out.println("Loading default word bank");
		loadBank();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainMenu = new MainMenu();
				gameMenu = new GameMenu();
				mainMenu.setVisible(true);
			}
		});
	}
	
	/**
	 * Switches to the game window.
	 */
	public static void switchWindow() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainMenu.setVisible(false);
				chooseWord();
				gameMenu.setVisible(true);
			}
		});
	}
	
	/**
	 * Resets to default values for the game.
	 */
	public static void reset() {
		mistakes = 0;
		gameOver = false;
		GameMenu.setImage(mistakes);
		usedLetters = new boolean[LETTERS];
		setWord();
		setUsed();
		GameMenu.setError("");
	}
	
	/**
	 * Checks if the given file is a txt file. If it is it is then loaded into 
	 * the word bank.
	 * 
	 * @param newFile	File to be loaded
	 */
	public static void setFile(File newFile) {
		String name = newFile.getName();
		String extension = "";
		int i = name.lastIndexOf('.');
		if(i > 0) {
			extension = name.substring(i + 1);
		}
		if(extension.equals("txt")) {
			file = newFile;
			System.out.println("Successfully retrieved " + file.getName());
			loadBank();
		} else {
			System.out.println("Invalid file type\nFile was not loaded");
		}
	}
	
	/**
	 * Loads the words from the file into the word bank.
	 */
	public static void loadBank() {
		wordBank = new ArrayList<String>();
		
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String curr;
		int valid = 0;
		int invalid = 0;
		while(input.hasNextLine()) {
			curr = input.nextLine().toUpperCase();
			if(isOnlyLetters(curr) && curr.length() <= MAX_LENGTH &&
					curr.length() > 0) {
				wordBank.add(curr);
				valid++;
			} else {
				invalid++;
			}
		}
		
		if(wordBank.size() == 0) {
			System.out.println("File had no valid entries\nLoading default word"
					+ " bank");
			file = new File("assets/default.txt");
			loadBank();
		} else {
			System.out.println("There was " + valid + " valid entries");
			System.out.println("There was " + invalid + " invalid entries");
		}
		
		System.out.println("File " + file.getName() + " finished loading");
		System.out.println("There were " + wordBank.size() + " entries");
	}
	
	/**
	 * Returns if the string given was only letters.
	 * 
	 * <p>
	 * Assumes the string is all uppercase
	 * </p>
	 * 
	 * @return	If the string was only letters
	 */
	public static boolean isOnlyLetters(String word) {
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) < LETTER_A || word.charAt(i) > LETTER_Z) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Resets the game and chooses a new word to solve.
	 */
	public static void chooseWord() {
		reset();
		
		String currWord = word;
		Random r = new Random();
		word = wordBank.get(r.nextInt(wordBank.size()));
		if(currWord.equals(word)) {
			//retries if the same word was selected
			chooseWord();
		} else {
			System.out.println("The word chosen was " + word);
			updateCorrectLetters();
			setWord();
		}
	}
	
	/**
	 * Sets the appropriate letters to true if the letter is in the word.
	 */
	public static void updateCorrectLetters() {
		for(int i = 0; i < word.length(); i++) {
			correctLetters[word.charAt(i) - LETTER_A] = true;
		}
	}
	
	/**
	 * Updates the word displayed to the user with correctly guessed letters.
	 */
	public static void setWord() {
		String currWord = "";
		for(int i = 0; i < word.length(); i++) {
			if(isUsed(word.charAt(i))) {
				currWord += word.charAt(i) + " ";
			} else {
				currWord += "_ ";
			}
		}
		
		GameMenu.setWord(currWord);
	}
	
	/**
	 * Displays the full word to the user despite guessing the letters.
	 */
	public static void showWord() {
		String currWord = "";
		for(int i = 0; i < word.length(); i++) {
			currWord += word.charAt(i) + " ";
		}
		
		GameMenu.setWord(currWord);
	}
	
	/**
	 * Updates the letters displayed to the user that were guessed incorrectly.
	 */
	public static void setUsed() {
		String usedString = "";
		
		for(int i = 0; i < usedLetters.length; i++) {
			if(usedLetters[i] && !correctLetters[i]) {
				usedString += (char)(LETTER_A + i) + " ";
			}
		}
		
		GameMenu.setUsedLetters(usedString);
	}

	/**
	 * Checks whatever character that was entered into the text box.
	 */
	public static void checkGuess(char letter) {
		if(isLetter(letter)) {
			if(!isUsed(letter)) {
				usedLetters[letter - LETTER_A] = true;
				GameMenu.setError("");
				if(isCorrect(letter)) {
					setWord();
				} else {
					GameMenu.setImage(++mistakes);
					setUsed();
				}
				if(hasWon()) {
					GameMenu.setError("You Win");
					showWord();
				} else if(hasLost()	) {
					GameMenu.setError("You Lose");
					showWord();
				}
			} else {
				GameMenu.setError("Letter was used");
			}
		} else {
			GameMenu.setError("Must input letter");
		}
	}
	
	/**
	 * Checks if the given character is a letter.
	 * 
	 * <p>
	 * Assumes only uppercase letters are given.
	 * </p>
	 * 
	 * @param letter	Character to check
	 * @return	If the character was a letter
	 */
	public static boolean isLetter(char letter) {
		return (letter >= LETTER_A && letter <= LETTER_Z);
	}
	
	public static boolean isUsed(char letter) {
		return usedLetters[letter - LETTER_A];
	}
	
	public static boolean isCorrect(char letter) {
		return correctLetters[letter - LETTER_A];
	}
	
	/**
	 * Checks if the user has won the game.
	 * 
	 * <p>
	 * Sets the game into game over state if they did win.
	 * </p>
	 * 
	 * @return	If the user won or not
	 */
	public static boolean hasWon() {
		for(int i = 0; i < correctLetters.length; i++) {
			if(correctLetters[i]) {
				if(!usedLetters[i]) {
					return false;
				}
			}
		}
		
		gameOver = true;
		return true;
	}
	
	/**
	 * Checks if the user has lost the game.
	 * 
	 * <p>
	 * Sets the game into game over state if they did lose.
	 * </p>
	 * 
	 * @return	If the user lost or not
	 */
	public static boolean hasLost() {
		if(mistakes >= MAX_MISTAKES) {
			gameOver = true;
			return true;
		}
		
		return false;
	}
}
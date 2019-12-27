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
	private static char guess;
	private static int mistakes;
	private static boolean[] usedLetters;
	private static boolean[] correctLetters;
	private static File file;
	private static ArrayList<String> wordBank;
	
	//window
	private static JFrame mainMenu;
	private static JFrame gameMenu;
	
	
	public static void main(String[] args) {
		System.out.println("Hangman v2.0 is starting");
		mistakes = 0;
		usedLetters = new boolean[LETTERS];
		correctLetters = new boolean[LETTERS];
		wordBank = new ArrayList<String>();
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
	
	/*
	 * Switches to the game window.
	 */
	public static void switchWindow() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainMenu.setVisible(false);
				startGame();
				gameMenu.setVisible(true);
			}
		});
	}
	
	/*
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
	
	/*
	 * Loads the words from the file into the word bank.
	 */
	public static void loadBank() {
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String curr;
		while(input.hasNextLine()) {
			curr = input.nextLine().toUpperCase();
			if(isOnlyLetters(curr)) {
				wordBank.add(curr);
			}
		}
		
		if(wordBank.size() == 0) {
			System.out.println("File had no valid entries\nLoading default word"
					+ " bank");
			file = new File("assets/default.txt");
			loadBank();
		}
		
		System.out.println("File " + file.getName() + " finished loading");
		System.out.println("There were " + wordBank.size() + " entries");
	}
	
	public static void startGame() {
		chooseWord();
		
		/*
		do {
			displayHangman();
			displayUsed();
			
			do {
				guess = recieveGuess();
			} while(isUsed(guess));
			usedLetters[guess - LETTER_A] = true;
			if(!isCorrect(guess)) {
				mistakes++;
			}
		} while(mistakes < MAX_MISTAKES && !hasWon());
		
		displayHangman();
		
		if(hasWon()) {
			System.out.println("Congrats");
		} else {
			System.out.println("Game Over");
		}
		*/
	}
	
	public static boolean isOnlyLetters(String word) {
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) < LETTER_A || word.charAt(i) > LETTER_Z) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void chooseWord() {
		String currWord = word;
		Random r = new Random();
		word = wordBank.get(r.nextInt(wordBank.size()));
		if(currWord.equals(word)) {
			chooseWord();
		} else {
			System.out.println("The word chosen was " + word);
			updateCorrectLetters();
			setWord();
		}
	}
	
	public static void updateCorrectLetters() {
		for(int i = 0; i < word.length(); i++) {
			correctLetters[word.charAt(i) - LETTER_A] = true;
		}
	}
	
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
	
	public static void displayHangman() {
		System.out.println(" _____");
		System.out.println(" |   |");
		
		//head
		System.out.print(" |");
		if(mistakes >= 1) {
			System.out.println("   O");
		} else {
			System.out.println();
		}
		
		//arms and body
		System.out.print(" |");
		if(mistakes >= 2) {
			if(mistakes >= 3) {
				if(mistakes >= 4) {
					System.out.println("  /|\\");
				} else {
					System.out.println("  /|");
				}
			} else {
				System.out.println("   |");
			}
		} else {
			System.out.println();
		}
		
		//legs
		System.out.print(" |");
		if(mistakes >= 5) {
			if(mistakes >= 6) {
				System.out.println("  / \\");
			} else {
				System.out.println("  /");
			}
		} else {
			System.out.println();
		}
		
		System.out.println("========");
 	}
	
	public static void displayUsed() {
		System.out.print("Used: ");
		
		for(int i = 0; i < usedLetters.length; i++) {
			if(usedLetters[i]) {
				System.out.print((char)(LETTER_A + i) + " ");
			}
		}
		
		System.out.println();
	}

	public static char recieveGuess() {
		String guess;
		do {
			System.out.println("Guess a letter: ");
			guess = input.nextLine();
			guess = guess.toUpperCase();
		} while(guess.length() != 1 || !isLetter(guess.charAt(0)));
		
		return guess.charAt(0);
	}
	
	public static boolean isLetter(char letter) {
		return (letter >= LETTER_A && letter <= LETTER_Z);
	}
	
	public static boolean isUsed(char letter) {
		return usedLetters[letter - LETTER_A];
	}
	
	public static boolean isCorrect(char letter) {
		return correctLetters[letter - LETTER_A];
	}
	
	public static boolean hasWon() {
		for(int i = 0; i < correctLetters.length; i++) {
			if(correctLetters[i]) {
				if(!usedLetters[i]) {
					return false;
				}
			}
		}
		
		return true;
	}
}
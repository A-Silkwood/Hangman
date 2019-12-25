package com.silkwood.hangman;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.silkwood.hangman.userinterface.MainMenu;

public class Hangman {
	private static final int LETTERS = 26;
	private static final int LETTER_A = 'A';
	private static final int LETTER_Z = 'Z';
	private static final int MAX_MISTAKES = 6;
	
	//input
	private static Scanner input;
	
	//processing
	private static String word;
	private static char guess;
	private static int mistakes;
	private static boolean[] usedLetters;
	private static boolean[] correctLetters;
	
	//window
	private static JFrame window;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				window = new MainMenu("Hangman v2.0");
				window.setVisible(true);
			}
		});
	}
	
	public Hangman() {
		mistakes = 0;
		usedLetters = new boolean[LETTERS];
		correctLetters = new boolean[LETTERS];
		input = new Scanner(System.in);
		
		playGame();
	}
	
	public static void playGame() {
		word = recieveWord();
		updateCorrectLetters(word);
		
		do {
			displayHangman();
			displayWord();
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
		displayWord();
		
		if(hasWon()) {
			System.out.println("Congrats");
		} else {
			System.out.println("Game Over");
		}
		
	}
	
	public static String recieveWord() {
		String word;
		
		do {
			System.out.println("Enter a word for the other person to guess: ");
			word = input.nextLine();
			word = word.toUpperCase();
		} while(!isOnlyLetters(word));
		
		return word;
	}
	
	public static boolean isOnlyLetters(String word) {
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) < LETTER_A || word.charAt(i) > LETTER_Z) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void updateCorrectLetters(String word) {
		for(int i = 0; i < word.length(); i++) {
			correctLetters[word.charAt(i) - LETTER_A] = true;
		}
	}
	
	public static void displayWord() {
		for(int i = 0; i < word.length(); i++) {
			if(isUsed(word.charAt(i))) {
				System.out.print(word.charAt(i) + " ");
			} else {
				System.out.print("_ ");
			}
		}
		
		System.out.println();
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
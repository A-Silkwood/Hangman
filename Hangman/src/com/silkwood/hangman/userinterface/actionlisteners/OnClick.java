package com.silkwood.hangman.userinterface.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.silkwood.hangman.Hangman;
import com.silkwood.hangman.userinterface.GameMenu;

public class OnClick implements ActionListener {
	//possible functions
	public static final String LOAD = "load";
	public static final String START = "start";
	public static final String NEW_WORD = "new";
	public static final String RESET = "reset";
	public static final String EXIT = "exit";
	public static final String ENTER = "enter";
	
	/*
	 * Figures out which function is to be performed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		switch(action) {
		case LOAD:
			load();
			break;
		case START:
			start();
			break;
		case NEW_WORD:
			newWord();
			break;
		case RESET:
			reset();
			break;
		case EXIT:
			exit();
			break;
		case ENTER:
			enter();
			break;
		}
	}
	
	/*
	 * Attempts to load a new word bank from the users files.
	 */
	private void load() {
		System.out.println("Opening loading menu");                            
		GameMenu.loadFile();
	}
	
	/*
	 * Starts the game by changing a GameMenu.
	 */
	private void start() {
		System.out.println("Starting game");
		Hangman.switchWindow();
	}
	
	/*
	 * Starts game over with new word.
	 */
	private void newWord() {
		System.out.println("Loading new word");
	}
	
	/*
	 * Resets the current word and mistakes.
	 */
	private void reset() {
		System.out.println("Reseting current word");
	}
	
	/**
	 * Closes the program.
	 */
	private void exit() {
		System.out.println("Exiting program");
		System.exit(0);
	}
	
	/**
	 * Checks current guess.
	 */
	private void enter() {
		System.out.println("Entering current guess");
	}
}

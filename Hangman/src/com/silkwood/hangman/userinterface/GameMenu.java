package com.silkwood.hangman.userinterface;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class GameMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final int WINDOW_WIDTH = 400;
	public static final int WINDOW_HEIGHT = 300;
	
	public GameMenu(String title) {
		super(title);
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		
		//create components
		JMenuBar bar = new JMenuBar();
		JMenu file =new JMenu("File");
		JMenuItem loadItem = new JMenuItem("Load Word Bank");
		JMenuItem newItem = new JMenuItem("New Word");
		JMenuItem resetItem = new JMenuItem("Reset");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		JEditorPane hangmanPane = new JEditorPane();
		JLabel wordLabel = new JLabel();
		JLabel usedLabel = new JLabel();
		
		JTextField guessText = new JTextField("Enter your guess:");
		JLabel errorLabel =new JLabel();
		JButton enterButton = new JButton("->");
		
		//add components
		Container pane = getContentPane();
		
		pane.add(bar);
		bar.add(file);
		file.add(loadItem);
		file.addSeparator();
		file.add(newItem);
		file.add(resetItem);
		file.addSeparator();
		file.add(exitItem);
	}
}

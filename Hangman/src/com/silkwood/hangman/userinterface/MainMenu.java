package com.silkwood.hangman.userinterface;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final int WINDOW_WIDTH = 400;
	public static final int WINDOW_HEIGHT = 150;
	
	private static final String LOAD = "load";
	private static final String START = "start";
	private static final String EXIT = "exit";
	
	public MainMenu(String title) {
		super(title);
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set layout
		setLayout(new GridBagLayout());
		
		//create components
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem loadItem = new JMenuItem("Load Word Bank");
		JMenuItem startItem = new JMenuItem("Start Game");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		JButton loadButton = new JButton("Load Word Bank");
		JButton startButton = new JButton("Start Game");
		
		startButton.setPreferredSize(loadButton.getPreferredSize());
		
		loadItem.setActionCommand(LOAD);
		loadButton.setActionCommand(LOAD);
		startItem.setActionCommand(START);
		startButton.setActionCommand(START);
		exitItem.setActionCommand(EXIT);
		
		//create constraints and add components
		Container pane = getContentPane();
		
		GridBagConstraints c = new GridBagConstraints();
		
		//menu bar
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		pane.add(bar, c);
		//file tab
		bar.add(file);
		file.add(loadItem);
		file.add(startItem);
		file.add(exitItem);
		
		//buttons
		c.weighty = 1.0;
		c.weightx = 1.0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 5);
		pane.add(loadButton, c);
		c.gridx = 1;
		c.insets = new Insets(10, 5, 10, 10);
		pane.add(startButton, c);
	}
}
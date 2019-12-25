package com.silkwood.hangman.userinterface;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public MainMenu(String title) {
		super(title);
		
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
		
		//create constraints
		GridBagConstraints barConstraints = new GridBagConstraints();
		barConstraints.
		//add components to content pane
		Container c = getContentPane();
		
		c.add(bar);
		bar.add(file);
		file.add(loadItem);
		file.add(startItem);
		file.add(exitItem);
	}
}

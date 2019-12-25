package com.silkwood.hangman.userinterface;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class UserInterface extends JFrame {
	private static final long serialVersionUID = 1L;

	public UserInterface(String title) {
		super(title);
		
		//Set layout manager
		setLayout(new GridBagLayout());
		
		//Create swing components
		JButton load = new JButton("Load Word Bank");
		JButton start = new JButton("Start Game");
		JButton reset = new JButton("Reset");
		
		//Add swing components to its content pane
		Container c = getContentPane();
		
		//c.add(textArea, BorderLayout.CENTER);
		//c.add(button, BorderLayout.SOUTH);
		
		//Add behavior
		/*
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Hello\n");
			}
		});*/
	}
}
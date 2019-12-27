package com.silkwood.hangman.userinterface;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.silkwood.hangman.Hangman;
import com.silkwood.hangman.userinterface.actionlisteners.OnClick;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	//window size
	public static final int WINDOW_WIDTH = 350;
	public static final int WINDOW_HEIGHT = 150;
	
	private static Container pane;
	private static GridBagConstraints c;
	private static OnClick al;
	//components
	private static JMenuBar bar;
	private static JMenu file;
	private static JMenuItem loadItem;
	private static JMenuItem startItem;
	private static JMenuItem exitItem;
	private static JButton loadButton;
	private static JButton startButton;
	
	/*
	 * Creates the main menu.
	 */
	public MainMenu() {
		super(Hangman.PROGRAM_NAME);
		
		//window settings
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		//center frame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width / 2) - (this.getSize().width / 2),
				(dim.height / 2) - (this.getSize().height / 2));
		
		//components
		
		//menu bar
		bar = new JMenuBar();
		//file menu
		file = new JMenu("File");
		loadItem = new JMenuItem("Load Word Bank");
		startItem = new JMenuItem("Start Game");
		exitItem = new JMenuItem("Exit");
		//buttons
		loadButton = new JButton("Load Word Bank");
		startButton = new JButton("Start Game");
		
		//make buttons the same size
		startButton.setPreferredSize(loadButton.getPreferredSize());
		
		//component functionality 
		al = new OnClick();
		
		//commands
		loadItem.setActionCommand(OnClick.LOAD);
		loadButton.setActionCommand(OnClick.LOAD);
		startItem.setActionCommand(OnClick.START);
		startButton.setActionCommand(OnClick.START);
		exitItem.setActionCommand(OnClick.EXIT);
		
		//listeners
		loadItem.addActionListener(al);
		loadButton.addActionListener(al);
		startItem.addActionListener(al);
		startButton.addActionListener(al);
		exitItem.addActionListener(al);
		
		//add components to window
		pane = getContentPane();
		c = new GridBagConstraints();
		
		//menu bar
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		pane.add(bar, c);
		//file tab
		bar.add(file);
		file.add(loadItem);
		file.add(startItem);
		file.addSeparator();
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
package com.silkwood.hangman.userinterface;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import com.silkwood.hangman.Hangman;
import com.silkwood.hangman.userinterface.actionlisteners.OnClick;

public class GameMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	//window size
	public static final int WINDOW_WIDTH = 400;
	public static final int WINDOW_HEIGHT = 300;
	
	private static JFileChooser fc;
	private Container pane;
	private GridBagConstraints c;
	private OnClick al;
	//components
	private JMenuBar bar;
	private JMenu file;
	private JMenuItem loadItem;
	private JMenuItem newItem;
	private JMenuItem resetItem;
	private JMenuItem exitItem;
	//temp
	private JEditorPane hangmanPane;
	private JLabel wordLabel;
	private JLabel usedLabel;
	private JTextField guessText;
	private JLabel errorLabel;
	private JButton enterButton;
	
	/*
	 * Creates the game menu.
	 */
	public GameMenu() {
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
		
		//file chooser
		fc = new JFileChooser();
		
		//menu bar
		bar = new JMenuBar();
		//file menu
		file =new JMenu("File");
		loadItem = new JMenuItem("Load Word Bank");
		newItem = new JMenuItem("New Word");
		resetItem = new JMenuItem("Reset");
		exitItem = new JMenuItem("Exit");
		//graphics
		hangmanPane = new JEditorPane();
		wordLabel = new JLabel();
		usedLabel = new JLabel();
		//user interaction
		guessText = new JTextField("Enter your guess:");
		errorLabel =new JLabel();
		enterButton = new JButton("->");
		
		//component functionality
		al = new OnClick();
		
		//commands
		loadItem.setActionCommand(OnClick.LOAD);
		newItem.setActionCommand(OnClick.NEW_WORD);
		resetItem.setActionCommand(OnClick.RESET);
		exitItem.setActionCommand(OnClick.EXIT);
		
		//listeners
		loadItem.addActionListener(al);
		newItem.addActionListener(al);
		resetItem.addActionListener(al);
		exitItem.addActionListener(al);
		
		//add components to window
		pane = getContentPane();
		c = new GridBagConstraints();
		
		//menu bar
		pane.add(bar);
		//file tab
		bar.add(file);
		file.add(loadItem);
		file.addSeparator();
		file.add(newItem);
		file.add(resetItem);
		file.addSeparator();
		file.add(exitItem);
	}
	
	/*
	 * Opens the file chooser and then loads the file if a file was chosen.
	 */
	public static void loadFile() {
		int returnVal = fc.showOpenDialog(fc);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			System.out.println("Loading " + file.getName());
			
			Hangman.setFile(file);
		} else {
			System.out.println("No file was loaded");
		}
		
		
	}
}

package com.silkwood.hangman.userinterface;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.silkwood.hangman.Hangman;
import com.silkwood.hangman.userinterface.actionlisteners.OnClick;

public class GameMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	//window size
	public static final int WINDOW_WIDTH = 350;
	public static final int WINDOW_HEIGHT = 300;
	
	private static JFileChooser fileChooser;
	private static Container pane;
	private static GridBagConstraints c;
	private static OnClick al;
	//components
	private static JMenuBar bar;
	private static JMenu file;
	private static JMenuItem loadItem;
	private static JMenuItem newItem;
	private static JMenuItem resetItem;
	private static JMenuItem exitItem;
	private static JLabel hangmanLabel;
	private static JLabel wordLabel;
	private static JLabel usedLabel;
	private static JLabel usedLettersLabel;
	private static JTextField guessText;
	private static JLabel errorLabel;
	private static JButton enterButton;
	//images
	private static String[] hangmanImgs;
	private static ImageIcon hangmanImg;
	
	/**
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
		//images
		hangmanImgs = new String[7];
		hangmanImgs[0] = "assets/hangman0.png";
		hangmanImgs[1] = "assets/hangman1.png";
		hangmanImgs[2] = "assets/hangman2.png";
		hangmanImgs[3] = "assets/hangman3.png";
		hangmanImgs[4] = "assets/hangman4.png";
		hangmanImgs[5] = "assets/hangman5.png";
		hangmanImgs[6] = "assets/hangman6.png";
		hangmanImg = new ImageIcon(hangmanImgs[0]);
		
		//components
		
		//file chooser
		fileChooser = new JFileChooser();
		
		//menu bar
		bar = new JMenuBar();
		//file menu
		file =new JMenu("File");
		loadItem = new JMenuItem("Load Word Bank");
		newItem = new JMenuItem("New Word");
		resetItem = new JMenuItem("Reset");
		exitItem = new JMenuItem("Exit");
		//graphics
		hangmanLabel = new JLabel(hangmanImg);
		wordLabel = new JLabel("T _ S T");
		//wordLabel = new JLabel(Hangman.getNewWord());
		usedLabel = new JLabel("Wrong Guesses");
		usedLettersLabel = new JLabel("");
		errorLabel =new JLabel("");
		//user interaction
		guessText = new JTextField();
		enterButton = new JButton("ENTER");
		
		//component functionality
		al = new OnClick();
		
		//commands
		loadItem.setActionCommand(OnClick.LOAD);
		newItem.setActionCommand(OnClick.NEW_WORD);
		resetItem.setActionCommand(OnClick.RESET);
		exitItem.setActionCommand(OnClick.EXIT);
		enterButton.setActionCommand(OnClick.ENTER);
		
		//listeners
		loadItem.addActionListener(al);
		newItem.addActionListener(al);
		resetItem.addActionListener(al);
		exitItem.addActionListener(al);
		enterButton.addActionListener(al);
		//when enter is typed in text box
		guessText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Hangman.gameOver) {
					System.out.println("Entering current guess");
					getGuess();
				}
			}
		});
		
		//component settings
		
		//hangman
		hangmanLabel.setHorizontalAlignment(JLabel.CENTER);
		hangmanLabel.setVerticalAlignment(JLabel.CENTER);
		//word
		wordLabel.setHorizontalAlignment(JLabel.CENTER);
		wordLabel.setVerticalAlignment(JLabel.TOP);
		wordLabel.setFont(wordLabel.getFont().deriveFont(25f));
		//used letters
		usedLabel.setHorizontalAlignment(JLabel.CENTER);
		usedLabel.setVerticalAlignment(JLabel.BOTTOM);
		usedLabel.setFont(usedLabel.getFont().deriveFont(22f));
		usedLettersLabel.setOpaque(true);
		usedLettersLabel.setHorizontalAlignment(JLabel.CENTER);
		usedLettersLabel.setVerticalAlignment(JLabel.TOP);
		usedLettersLabel.setFont(usedLettersLabel.getFont().deriveFont(20f));
		usedLettersLabel.setMinimumSize(new Dimension(175, 40));
		usedLettersLabel.setMaximumSize(new Dimension(175, 40));
		usedLettersLabel.setPreferredSize(new Dimension(175, 40));
		//error
		errorLabel.setHorizontalAlignment(JLabel.LEADING);
		errorLabel.setVerticalAlignment(JLabel.TOP);
		errorLabel.setFont(errorLabel.getFont().deriveFont(10f));
		//textbox
		guessText.setDocument(new JTextFieldLimit(1));
		guessText.setFont(guessText.getFont().deriveFont(20f));
		guessText.setHorizontalAlignment(JTextField.CENTER);
		
		//add components to window
		pane = getContentPane();
		c = new GridBagConstraints();
		
		//menu bar
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		pane.add(bar, c);
		//file tab
		bar.add(file);
		file.add(loadItem);
		file.addSeparator();
		file.add(newItem);
		file.add(resetItem);
		file.addSeparator();
		file.add(exitItem);
		
		//hangman graphics
		c.gridy = 1;
		c.insets = new Insets(10, 0, 0, 0);
		pane.add(hangmanLabel, c);
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 0);
		pane.add(wordLabel, c);
		
		//users section
		c.gridwidth = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.0;
		pane.add(usedLabel, c);
		c.gridx = 1;
		c.insets = new Insets(10, 0, 0, 5);
		pane.add(guessText, c);
		c.gridx = 2;
		c.insets = new Insets(10, 5, 0, 10);
		pane.add(enterButton, c);
		c.gridy = 4;
		c.gridx = 0;
		c.insets = new Insets(0, 0, 0, 0);
		pane.add(usedLettersLabel, c);
		c.gridwidth = 2;
		c.gridx = 1;
		c.insets = new Insets(0, 10, 0, 0);
		pane.add(errorLabel, c);
	}
	
	/**
	 * Opens the file chooser and then loads the file if a file was chosen.
	 */
	public static void loadFile() {
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		
		//if file was loaded or not
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			System.out.println("Loading " + file.getName());
			
			Hangman.setFile(file);
			Hangman.chooseWord();
		} else {
			System.out.println("No file was loaded");
		}
	}
	
	/**
	 * Sets the appropriate image based on mistakes.
	 */
	public static void setImage(int mistakes) {
		if(mistakes >= 0 && mistakes <= 6) {
			hangmanImg = new ImageIcon(hangmanImgs[mistakes]);
		} else {
			//out of bounds value
			hangmanImg = new ImageIcon(hangmanImgs[0]);
		}
		
		hangmanLabel.setIcon(hangmanImg);
	}
	
	/**
	 * Sets the string used to display the word.
	 * 
	 * @param word	String to set to
	 */
	public static void setWord(String word) {
		wordLabel.setText(word);
	}
	
	/**
	 * Sets the string used to display all of the used letters.
	 *
	 * @param usedLetters	String to set to
	 */
	public static void setUsedLetters(String usedLetters) {
		usedLettersLabel.setText(usedLetters);
	}
	
	/**
	 * Sets the string used to display errors to the user.
	 * 
	 * @param error	String to set to
	 */
	public static void setError(String error) {
		errorLabel.setText(error);
	}
	
	/**
	 * Receives the guess entry in the text box
	 */
	public static void getGuess() {
		String test = guessText.getText();
		guessText.setText("");
		if(!test.equals("")) {
			System.out.println("Recieved " + test.toUpperCase());
			//hangman code
			Hangman.checkGuess(test.toUpperCase().charAt(0));
		} else {
			GameMenu.setError("Enter a letter");
			System.out.println("Recieved nothing");
		}
	}
}

//class that help limits the text field object to a single character
class JTextFieldLimit extends PlainDocument {
	private static final long serialVersionUID = 1L;
	private int limit;
	
	//constructors
	JTextFieldLimit(int limit) {
		super();
		
		this.limit = limit;
	}
	
	JTextFieldLimit(int limit, boolean upper) {
		super();
		
		this.limit = limit;
	}
	
	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null)
			return;
		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}

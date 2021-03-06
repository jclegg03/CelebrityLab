package celebrity.view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import celebrity.controller.CelebrityGame;

/**
 * The start screen for the CelebrityGame app.
 * 
 * @author cody.henrichsen
 * @version 2.1 18/09/2018 Refactored away validation to controller.
 */
public class StartPanel extends JPanel
{
  /**
   * Reference to the Game to call methods.
   */
  private CelebrityGame app;
  
  /**
   * The layout manager for the screen.
   */
  private SpringLayout panelLayout;
  
  /**
   * Logical container for the RadioButtons to guarantee only one is selected
   * at a time.
   */
  private ButtonGroup typeGroup;
  
  /**
   * RadioButton for the default type.
   */
  private JRadioButton celebrityRadio;
  
  /**
   * RadioButton for the Celebrity type.
   */
  private JRadioButton literatureRadio;
  
  /**
   * Customize the JRadioButton for the class created sub class
   */
  private JRadioButton cinemaRadio;
  
  /**
   * Peer subclass
   */
  private JRadioButton historyRadio;
  
  /**
   * Label to guide the user to what should be inputted.
   */
  private JLabel clueLabel;
  
  /**
   * Label for displaying the current number of celebrities added to the game
   */
  private JLabel celebrityCountLabel;
  
  /**
   * Textfield to type in the answer for the celebrity.
   */
  private JTextField answerField;
  
  /**
   * Textfield to type in the clue for the celebrity.
   */
  private JTextField clueField;
  
  /**
   * Button used to verify and add a Celebrity to the ArrayList of Celebrity for the game
   */
  private JButton addCelebrityButton;
  
  /**
   * Button used to start the game.
   */
  private JButton startButton;
  
  /**
   * String to populate the clueLabel if Celebrity is picked.
   */
  private String celebrityClue;
  
  /**
   * String to populate the clueLabel if Literature Celebrity is picked.
   */
  private String literatureClue;
  
  /**
   * String to populate the clueLabel if Class Generated Celebrity is picked.
   */
  private String cinemaClue;
  
  /**
   * String to populate the clueLabel if peer Celebrity is picked.
   */
  private String historyClue;
  
  /**
   * String used for static text in label.
   */
  private String countLabelText;
  
  /**
   * The current number of celebrities added to the game
   */
  private int celebrityCount;
  
  
  /**
   * Constructs a StartPanel with a reference to the game passed as a
   * parameter to be used as a data member.
   * 
   * @param controller
   *            The reference to the game
   */
  public StartPanel(CelebrityGame controller)
  {
    super();
    this.app = controller;
    this.panelLayout = new SpringLayout();
    this.typeGroup = new ButtonGroup();
    this.celebrityRadio = new JRadioButton("Celebrity");
    this.literatureRadio = new JRadioButton("Literature Celebrity");
    this.cinemaRadio = new JRadioButton("Cinema Celebrity");
    this.historyRadio = new JRadioButton("Historical Celebrity");
    this.celebrityClue = "Enter the clue for the celebrity";
    this.literatureClue = "Enter the clues for the literature celeb separated by commas";
    this.cinemaClue = "Enter the characters they play then a : then the movie its from. Seperate values with commas.";
    this.historyClue = "Enter the birth year (number) of the celebrity";
    this.clueLabel = new JLabel(celebrityClue);
    
    this.answerField = new JTextField("Type celebrity here (4 letters minimum thx Cher)");
    this.clueField = new JTextField("Enter celebrity clue here (10 letters minimum)");
    this.addCelebrityButton = new JButton("Add current celebrity");
    this.startButton = new JButton("Start Celebrity game");
    this.celebrityCount = 0;
    this.countLabelText = "Current Celebrity Count: " + celebrityCount;
    this.celebrityCountLabel = new JLabel(countLabelText);
    
    setupPanel();
    setupLayout();
    setupListeners();
  }
  
  /**
   * Validation method for the text to create a Celebrity instance.
   * 
   * @param answerText
   *            The name of the Celebrity. Validation requires at least 4
   *            characters.
   * @param clueText
   *            The text for the clue. Validation depends on the selected
   *            Celebrity type, but at least 10 characters are required.
   * @return Whether the appropriate text amounts are filled and the correct
   *         type of clue is given.
   */
  private boolean validate(String answerText, String clueText)
  {
    boolean validClue = false;
    boolean validAnswer = false;
    
    if (literatureRadio.isSelected())
    {
      validClue = app.validateClue(clueText, "Literature");
    }
    else if(cinemaRadio.isSelected())
    {
    	validClue = app.validateClue(clueText, "Cinema");
    }
    else if(historyRadio.isSelected())
    {
    	validClue = app.validateClue(clueText, "History");
    }
    else
    {
      validClue = app.validateClue(clueText, "");
    }
    
    if (answerText.length() > 4)
    {
      validAnswer = app.validateCelebrity(answerText);
    }
    
    return (validClue && validAnswer);
  }
  
  /**
   * Adds all components to the StartPanel and uses the SpringLayout variable,
   * panelLayout, as the layout manager.
   */
  private void setupPanel()
  {
	  this.setLayout(panelLayout);
	  
	  this.add(literatureRadio);
	  this.add(celebrityRadio);
	  this.add(cinemaRadio);
	  this.add(historyRadio);
	  this.add(clueLabel);
	  this.add(answerField);
	  this.add(clueField);
	  this.add(addCelebrityButton);
	  this.add(startButton);
	  this.add(celebrityCountLabel);
    // Adds the RadioButtons to the group so only one can be selected.
	  
	  typeGroup.add(celebrityRadio);
	  typeGroup.add(literatureRadio);
	  typeGroup.add(cinemaRadio);
	  typeGroup.add(historyRadio);
  }
  
  /**
   * Uses the Springlayout constraint system to place all GUI components on
   * screen. All constraints grouped together to keep code clean and
   * maintainable.
   */
  private void setupLayout()
  {
    panelLayout.putConstraint(SpringLayout.WEST, clueLabel, 0, SpringLayout.WEST, historyRadio);
    panelLayout.putConstraint(SpringLayout.NORTH, celebrityRadio, 15, SpringLayout.NORTH, this);
    panelLayout.putConstraint(SpringLayout.WEST, celebrityRadio, 15, SpringLayout.WEST, this);
    panelLayout.putConstraint(SpringLayout.EAST, addCelebrityButton, 0, SpringLayout.EAST, startButton);
    panelLayout.putConstraint(SpringLayout.NORTH, addCelebrityButton, 20, SpringLayout.SOUTH, clueField);
    panelLayout.putConstraint(SpringLayout.WEST, addCelebrityButton, 0, SpringLayout.WEST, historyRadio);
    
    panelLayout.putConstraint(SpringLayout.NORTH, startButton, 20, SpringLayout.SOUTH, addCelebrityButton);
    panelLayout.putConstraint(SpringLayout.NORTH, celebrityCountLabel, 0, SpringLayout.NORTH, historyRadio);
    panelLayout.putConstraint(SpringLayout.EAST, celebrityCountLabel, -45, SpringLayout.EAST, this);
    
    //peer button
    panelLayout.putConstraint(SpringLayout.NORTH, historyRadio, 10, SpringLayout.SOUTH, cinemaRadio);
    panelLayout.putConstraint(SpringLayout.WEST, historyRadio, 0, SpringLayout.WEST, cinemaRadio);
    
    //Put your custom radio button info here
    panelLayout.putConstraint(SpringLayout.NORTH, cinemaRadio, 10, SpringLayout.SOUTH, literatureRadio);
    panelLayout.putConstraint(SpringLayout.WEST, cinemaRadio, 0, SpringLayout.WEST, literatureRadio);
    
    panelLayout.putConstraint(SpringLayout.NORTH, literatureRadio, 10, SpringLayout.SOUTH, celebrityRadio);
    panelLayout.putConstraint(SpringLayout.WEST, literatureRadio, 0, SpringLayout.WEST, celebrityRadio);
    
    panelLayout.putConstraint(SpringLayout.NORTH, clueLabel, 10, SpringLayout.SOUTH, answerField);
    panelLayout.putConstraint(SpringLayout.NORTH, answerField, 40, SpringLayout.SOUTH, historyRadio);
    panelLayout.putConstraint(SpringLayout.WEST, answerField, 0, SpringLayout.WEST, historyRadio);
    panelLayout.putConstraint(SpringLayout.EAST, answerField, -15, SpringLayout.EAST, this);
    
    panelLayout.putConstraint(SpringLayout.WEST, clueField, 0, SpringLayout.WEST, historyRadio);
    panelLayout.putConstraint(SpringLayout.SOUTH, clueField, 55, SpringLayout.SOUTH, answerField);
    panelLayout.putConstraint(SpringLayout.EAST, clueField, 0, SpringLayout.EAST, answerField);
    panelLayout.putConstraint(SpringLayout.WEST, startButton, 0, SpringLayout.WEST, historyRadio);
    panelLayout.putConstraint(SpringLayout.EAST, startButton, 0, SpringLayout.EAST, answerField);
    
  }
  
  /**
   * Used to link all Listeners to the associated GUI components.
   */
  private void setupListeners()
  {
    /**
     * Links the submitButton to the validation and submit code. Provides
     * user input if information is not valid.
     */
    startButton.addActionListener(new ActionListener()
                                    {
      public void actionPerformed(ActionEvent mouseClick)
      {
        app.play();
      }
    });
    
    addCelebrityButton.addActionListener(new ActionListener()
                                           {
      public void actionPerformed(ActionEvent mouseClick)
      {
        answerField.setBackground(Color.WHITE);
        clueField.setBackground(Color.WHITE);
        if (validate(answerField.getText(), clueField.getText()))
        {
          addToGame();
        }
        else
        {
          invalidInput();
        }
        celebrityCount = app.getCelebrityGameSize();
        celebrityCountLabel.setText(countLabelText + celebrityCount);
      }
    });
    
    /**
     * Adds listeners to the radio buttons using the Java 8+ Lambda structure
     * for short code.
     * 
     */
    literatureRadio.addActionListener(select -> clueLabel.setText(literatureClue));
    celebrityRadio.addActionListener(select -> clueLabel.setText(celebrityClue));
    cinemaRadio.addActionListener(select -> clueLabel.setText(cinemaClue));
    historyRadio.addActionListener(select -> clueLabel.setText(historyClue));
  }
  
  private void invalidInput()
  {
    answerField.setText("Type in the celebrity!!");
    answerField.setBackground(Color.RED);
    clueField.setText("Type in the clue");
    clueField.setBackground(Color.RED);
  }
  
  private void addToGame()
  {
    String type = "Celebrity";
    if (literatureRadio.isSelected())
    {
      type = "Literature";
    }
    else if(cinemaRadio.isSelected())
    {
    	type = "Cinema";
    }
    else if(historyRadio.isSelected())
    {
    	type = "History";
    }
    String answer = answerField.getText().trim();
    String clue = clueField.getText().trim();
    answerField.setText("");
    clueField.setText("");
    app.addCelebrity(answer, clue, type);
    startButton.setEnabled(true);
  }
  
}

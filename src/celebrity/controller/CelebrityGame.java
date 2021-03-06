package celebrity.controller;
import java.util.ArrayList;

import celebrity.model.Celebrity;
import celebrity.model.LiteratureCelebrity;
import celebrity.model.CinemaCelebrity;
import celebrity.model.HistoricalCelebrity;
import celebrity.view.CelebrityFrame;

/**
 * The framework for the Celebrity Game project
 * 
 * @author cody.henrichsen
 * @version 2.3 25/09/2018 refactored the prepareGame and play methods
 */
public class CelebrityGame
{
	/**
	 * A reference to a Celebrity or subclass instance.
	 */
	private Celebrity currentCeleb;

	/**
	 * The GUI frame for the Celebrity game.
	 */
	private CelebrityFrame frame;

	/**
	 * The ArrayList of Celebrity values that make up the game
	 */
	private ArrayList<Celebrity> celebs;

	/**
	 * Builds the game and starts the GUI
	 */
	public CelebrityGame()
	{
		this.frame = new CelebrityFrame(this);
		prepareGame();
	}

	/**
	 * Prepares the game to start by re-initializing the celebGameList and having the gameFrame open the start screen.
	 */
	public void prepareGame()
	{
		celebs = new ArrayList<Celebrity>();
		frame.replaceScreen(CelebrityFrame.START);
	}

	/**
	 * Determines if the supplied guess is correct.
	 * 
	 * @param guess
	 *            The supplied String
	 * @return Whether it matches regardless of case or extraneous external
	 *         spaces.
	 */
	public boolean processGuess(String guess)
	{
		boolean correct = false;
		
		if(guess.trim().equalsIgnoreCase(currentCeleb.getAnswer()))
		{
			correct = true;
			celebs.remove(currentCeleb);
			if(celebs.size() > 0)
			{
				currentCeleb = celebs.get(0);
			}
		}
		
		return correct;
	}

	/**
	 * Asserts that the list is initialized and contains at least one Celebrity.
	 * Sets the current celebrity as the first item in the list. Opens the game
	 * play screen.
	 */
	public void play()
	{
		if(celebs != null && celebs.size() > 0)
		{
			currentCeleb = celebs.get(0);
			frame.replaceScreen(CelebrityFrame.GAME);
		}
		else
		{
			prepareGame();
		}
	}

	/**
	 * Adds a Celebrity of specified type to the game list
	 * 
	 * @param name
	 *            The name of the celebrity
	 * @param guess
	 *            The clue(s) for the celebrity
	 * @param type
	 *            What type of celebrity
	 */
	public void addCelebrity(String name, String guess, String type)
	{
		if(validateCelebrity(name) && validateClue(guess, type))
		{
			if(type.equals("Celebrity"))
			{
				celebs.add(new Celebrity(name, guess));
			}
			else if(type.equals("Literature"))
			{
				celebs.add(new LiteratureCelebrity(name, guess));
			}
			else if(type.equals("Cinema"))
			{
				celebs.add(new CinemaCelebrity(name, guess));
			}
			else if(type.contentEquals("History"));
			{
				celebs.add(new HistoricalCelebrity(name, guess));
			}
		}
	}

	/**
	 * Validates the name of the celebrity. It must have at least 4 characters.
	 * @param name The name of the Celebrity
	 * @return If the supplied Celebrity is valid
	 */
	public boolean validateCelebrity(String name)
	{
		return name.trim().length() >= 4;
	}

	/**
	 * Checks that the supplied clue has at least 10 characters or is a series of clues
	 * This method would be expanded based on your subclass of Celebrity.
	 * @param clue The text of the clue(s)
	 * @param type Supports a subclass of Celebrity 
	 * @return If the clue is valid.
	 */
	public boolean validateClue(String clue, String type)
	{
		boolean valid = false;
		
		if(type.equals("Celebrity") && clue.trim().length() >= 10)
		{
			valid = true;
		}
		else if(type.equals("Literature") && clue.contains(","))
		{
			String first = clue.substring(0, clue.indexOf(","));
			String second = clue.substring(clue.indexOf(",") + 2);
			
			if(first.length() >= 4 && second.length() >= 4)
			{
				valid = true;
			}
		}
		else if(type.contentEquals("Cinema") && clue.contains(":"))
		{
			String character = clue.substring(0, clue.indexOf(":"));
			String movie = clue.substring(clue.indexOf(":") + 2);
			
			valid = movie.length() >= 4 && character.length() >= 4;
		}
		else if(type.equals("History"))
		{
			try
			{
				Integer.parseInt(clue);
				valid = true;
			}
			catch(NumberFormatException notANumber)
			{
				valid = false;
			}
		}
		
		return valid;
	}

	/**
	 * Accessor method for the current size of the list of celebrities
	 * 
	 * @return Remaining number of celebrities
	 */
	public int getCelebrityGameSize()
	{
		return celebs.size();
	}

	/**
	 * Accessor method for the games clue to maintain low coupling between
	 * classes
	 * 
	 * @return The String clue from the current celebrity.
	 */
	public String sendClue()
	{
		return currentCeleb.getClue();
	}

	/**
	 * Accessor method for the games answer to maintain low coupling between
	 * classes
	 * 
	 * @return The String answer from the current celebrity.
	 */
	public String sendAnswer()
	{
		return currentCeleb.getAnswer();
	}
}

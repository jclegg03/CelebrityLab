package celebrity.view;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import celebrity.controller.CelebrityGame;

/**
 * GUI Frame class for the Celebrity Game
 * @author cody.henrichsen
 * @version 2.1 17/09/2018
 */
public class CelebrityFrame extends JFrame
{
	//Data members for the JFrame subclass instance 
	public static final String START = "START";
	public static final String GAME = "GAME";
	
	/**
	 * The instance of the panel used for playing the game.
	 */
	private CelebrityPanel gamePanel;
	
	/**
	 * Blank panel used for the CardLayout switching screens feature.
	 */
	private JPanel panelCards;
	
	/**
	 * The instance variable used to add a celebrity to the game.
	 */
	private StartPanel startPanel;
	
	/**
	 * A reference to the CelebrityGame instance to allow for minimized coupling in the object structure.
	 */
	private CelebrityGame app;
	
	
	/**
	 * Builds an instance of the CelebFrame with a reference to the CelebrityGame instance.
	 * @param controller A reference to a CelebrityGame to share with the CelebPanel instance.
	 */
	public CelebrityFrame(CelebrityGame controllerRef)
	{
		//The first line of any subclass should ALWAYS be a correct call to the super constructor.
		super("Name that Celebrity!!!");
		this.app = controllerRef;
		this.panelCards = new JPanel(new CardLayout());
		this.startPanel = new StartPanel(app);
		this.gamePanel = new CelebrityPanel(app);
		setupFrame();
	}
	
	/**
	 * Configures the JFrame window subclass to add the panel and set size based information.
	 */
	private void setupFrame()
	{
		this.panelCards.add(startPanel);
		this.panelCards.add(gamePanel);
		this.setSize(800, 800);
		this.add(panelCards);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.replaceScreen(START);
		this.setVisible(true);
	}
	
	/**
	 * Swaps the screen between the existing panels based on the String parameter.
	 * @param screen The name of the screen to open.
	 */
	public void replaceScreen(String screen)
	{
		if(screen.equals(GAME))
		{
			gamePanel.addClue(app.sendClue());
			((CardLayout) panelCards.getLayout()).show(panelCards, screen);
		}
		else
		{
			((CardLayout) panelCards.getLayout()).show(panelCards, screen);
		}
	}
	
}

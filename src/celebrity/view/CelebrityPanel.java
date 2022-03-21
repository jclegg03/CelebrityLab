package celebrity.view;

import javax.swing.JPanel;

import celebrity.controller.Controller;

public class CelebrityPanel extends JPanel
{
	private Controller app;
	
	public CelebrityPanel(Controller app)
	{
		super();
		this.app = app;
	}
}

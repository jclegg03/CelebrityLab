package celebrity.view;

import javax.swing.JFrame;

import celebrity.controller.Controller;

public class CelebrityView extends JFrame
{
	private Controller app;
	private CelebrityPanel content;
	
	public CelebrityView(Controller app)
	{
		super("Name That Person!");
		this.app = app;
		this.content = new CelebrityPanel(app);
		this.setContentPane(content);
		this.setSize(750, 750);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

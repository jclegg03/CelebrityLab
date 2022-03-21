package celebrity.controller;

import celebrity.view.CelebrityView;

public class Controller
{
	private CelebrityView frame;
	
	public Controller()
	{
		this.frame = new CelebrityView(this);
	}
}

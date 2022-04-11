package celebrity.model;

import java.util.ArrayList;

public class CinemaCelebrity extends Celebrity
{
	private ArrayList<String> clues;
	private ArrayList<String> movies;
	
	public CinemaCelebrity(String answer, String clues)
	{
		super(answer, clues);
		seperateClues();
	}
	
	public void seperateClues()
	{
		this.clues = new ArrayList<String>();
		this.movies = new ArrayList<String>();
		String[] clueList = super.getClue().split(",");
		for(String clue : clueList)
		{
			clues.add(clue);
			movies.add(clue.split(":")[1]);
		}
	}
}

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
	
	@Override
	public String getClue()
	{
		if(clues.size() == 0)
		{
			seperateClues();
		}
		String clue = clues.remove((int) (Math.random() * clues.size()));
		
		return clue;
	}
	
	public String getMovie()
	{
		if(movies.size() == 0)
		{
			seperateClues();
		}
		String movie = movies.remove((int) (Math.random() * clues.size()));
		
		return movie;
	}
	
	@Override
	public String toString()
	{
		String description = "This is a movie celebrity named: " + getAnswer() + "\nHere are some characters and movies they are associated with:";
		seperateClues();
		for(String clue : clues)
		{
			description += "\n" + clue;
		}
		
		return description;
	}
}

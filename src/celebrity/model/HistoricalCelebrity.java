package celebrity.model;

public class HistoricalCelebrity extends Celebrity
{
	
	private String answer;
	
	public HistoricalCelebrity(String answer, String clue)
	{
		super(answer, clue);
	}
	
	@Override
	public String getClue()
	{
		String clue = "Their birth year is " + super.getClue();
		return clue;
	}
	
	@Override
	public String toString()
	{
		String desc = "This is the historical celebrity: " + getAnswer() + "The clue is: " + this.getClue();
		
		return desc;
	}
}

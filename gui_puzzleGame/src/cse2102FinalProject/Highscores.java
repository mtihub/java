package cse2102FinalProject;

import java.io.*;
import java.util.*;

public class Highscores 
{
	private PuzzleTimer puzzleTimer;
	protected ArrayList<Integer> easyScore, mediumScore, hardScore;
	
	public Highscores()//PuzzleTimer timer)
	{
		//this.puzzleTimer = timer;
		this.easyScore = new ArrayList<Integer>(Collections.nCopies(10, -1));
		this.mediumScore = new ArrayList<Integer>(Collections.nCopies(10, -1));
		this.hardScore = new ArrayList<Integer>(Collections.nCopies(10, -1));
		getScore();
	}
	
	public Highscores(PuzzleTimer timer)
	{
		this.puzzleTimer = timer;
		this.easyScore = new ArrayList<Integer>(Collections.nCopies(10, -1));
		this.mediumScore = new ArrayList<Integer>(Collections.nCopies(10, -1));
		this.hardScore = new ArrayList<Integer>(Collections.nCopies(10, -1));
		getScore();
	}
	
	private void getScore()
	{
		try
		{
			FileInputStream file = new FileInputStream("Data\\Highscores\\Scores.txt");
			InputStreamReader reader = new InputStreamReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			
			String line = null;
			for (int i = 0; ((line = buffer.readLine()) != null) && (i < 3); i++) 
			{
				String[] scores = line.split(" ");
				if (i == 0)
				{
					for (int j = 0; j < 10; j++) 
						this.easyScore.set(j, Integer.parseInt(scores[j]));
				}
				else if (i == 1)
				{
					for (int j = 0; j < 10; j++) 
						this.mediumScore.set(j, Integer.parseInt(scores[j]));
				}
				else if (i == 2)
				{for (int j = 0; j < 10; j++) 
					this.hardScore.set(j, Integer.parseInt(scores[j]));}
			}
			
			buffer.close();
			
		}
		catch (Exception e)
		{
			System.out.println("\nError Fetching Scores\n");
			e.printStackTrace();
		}
		
		//System.out.println("\n" + easyScore + "  " + mediumScore + "  " + hardScore);
	}
	
	
	public boolean updateScore(String difficulty, int currentLevel)
	{
		getScore();
		int seconds = puzzleTimer.stopTimerAndGetSeconds();
		boolean wasHighScore = false;
		
		try
		{
			Formatter write = new Formatter("Data\\Highscores\\Scores.txt");
			
			switch (difficulty)
			{
			case "Easy":
				if (this.easyScore.get(currentLevel) < 0 || this.easyScore.get(currentLevel) > seconds)
				{
					this.easyScore.set(currentLevel, seconds);
					wasHighScore = true;
				}
				break;
								
			case "Medium":
				if (this.mediumScore.get(currentLevel) < 0 || this.mediumScore.get(currentLevel) > seconds)
				{
					this.mediumScore.set(currentLevel, seconds);
					wasHighScore = true;
				}
				break;
				
			case "Hard":
				if (this.hardScore.get(currentLevel) < 0 || this.hardScore.get(currentLevel) > seconds)
				{
					this.hardScore.set(currentLevel, seconds);
					wasHighScore = true;
				}
				break;
			}
			
			
			String updatedScores = "";
			for (int i = 0; i < 3; i++)
			{
				if (i == 0)
				{
					for (int j = 0; j < this.easyScore.size(); j++)
					{
						if (j != this.easyScore.size() - 1)
							updatedScores += Integer.toString(this.easyScore.get(j)) + " ";
						else
							updatedScores += Integer.toString(this.easyScore.get(j)) + System.lineSeparator();
					}
				}
				else if (i == 1)
				{
					for (int j = 0; j < this.mediumScore.size(); j++)
					{
						if (j != this.mediumScore.size() - 1)
							updatedScores += Integer.toString(this.mediumScore.get(j)) + " ";
						else
							updatedScores += Integer.toString(this.mediumScore.get(j)) + System.lineSeparator();
					}
				}
				else if (i == 2)
				{
					for (int j = 0; j < this.hardScore.size(); j++)
					{
						if (j != this.hardScore.size() - 1)
							updatedScores += Integer.toString(this.hardScore.get(j)) + " ";
						else
							updatedScores += Integer.toString(this.hardScore.get(j));
					}
				}
			}
			
			write.format("%s", updatedScores);
			write.close();
		}
		catch (Exception e)
		{
			System.out.println("\n\tError Updating Scores...\n");
			e.printStackTrace();
		}
		
		return wasHighScore;
	}
	
	
	public void updateScores()
	{
		getScore();
	}
	
	
	/*
	
	public ArrayList<Integer> getEasyScores()
	{
		return this.easyScore;
	}
	
	public ArrayList<Integer> getMediumScores()
	{
		return this.mediumScore;
	}
	
	public ArrayList<Integer> getHardScores()
	{
		return this.hardScore;
	}
	*/
	
	
	/*
	public void printScores()
	{
		getScore();
		
		System.out.print("\nEasy Scores:\n");
		
		for (int num : this.easyScore)
			System.out.print(num + " ");
		
		System.out.print("\n\nMedium Scores:\n");
		
		for (int num : this.mediumScore)
			System.out.print(num + " ");
		
		System.out.print("\n\nHard Scores:\n");
		
		for (int num : this.hardScore)
			System.out.print(num + " ");
		
		System.out.println();
	}
	*/

}

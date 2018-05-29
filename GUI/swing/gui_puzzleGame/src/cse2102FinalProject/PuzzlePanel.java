package cse2102FinalProject;

import java.util.*;

import javax.swing.*;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class PuzzlePanel extends JPanel implements ActionListener
{
	private JPanel contentPanel;
	
	private int currentDifficulty = 0;
	private int currentLevel = 1;
	private int numberOfImages = 0;// = 9;
	private String difficulty;
	private int numberOfSolved = 0;
	
	private CustomButton nextLevel, hasBeenSolved, jumpToNextLevel, mainMenu, exit, hint;
	private PuzzleButton lastClickedButton = null;
	
	private ArrayList<Icon> images;
	private ArrayList<Icon> solvedPuzzle;
	private ArrayList<PuzzleButton> puzzleButtons;
	
	private JLabel numberOfSolvedLabel = new JLabel();
	
	private PuzzleTimer puzzleTimer;
	//private Score score;
	private Highscores scores;
	private HighscorePanel highscorePanel;
	
	public PuzzlePanel(JPanel mainPanel, String difficulty, HighscorePanel highscorePanel)
	{
		// Setup Main Frame
		this.contentPanel = mainPanel;
		setLayout(null);
		this.setSize(1920, 1080);
		
		this.puzzleTimer = new PuzzleTimer();
		
		this.difficulty = difficulty;
		
		//score = new Score(this.puzzleTimer);
		this.scores = new Highscores(this.puzzleTimer);
		this.highscorePanel = highscorePanel;
		
		switch (difficulty)
		{
		case "Easy":
			this.currentDifficulty = 1;
			this.numberOfImages = 9;
			break;
		case "Medium":
			this.currentDifficulty = 2;
			this.numberOfImages = 25;
			break;
		case "Hard":
			this.currentDifficulty = 3;
			this.numberOfImages = 49;
			break;
		}
		
		
		// Fields
		getImagesAndSolvedPuzzle();
		getPuzzleButtons();
		
		numberOfSolvedLabel.setText("Number of Pieces Solved: " + Integer.toString(this.numberOfSolved));
		numberOfSolvedLabel.setFont(new Font("Bell MT", Font.BOLD, 35)); // Showcard Gothic
		numberOfSolvedLabel.setBounds(1450,400,500,60);
		numberOfSolvedLabel.setForeground(Color.WHITE);
		add(this.numberOfSolvedLabel);
		
		
		// Setup and Add the Next Level Button
		this.nextLevel = new CustomButton("Move onto the Next Level !!",
										  1500,650,320,85,
					   					  (new Color(59,89,182)), (Color.WHITE),
					   					  (new Font("Tahoma", Font.BOLD, 17)), 
					   					  this, "Next");
		this.nextLevel.setButtonVisibility(false);
		
		this.hasBeenSolved = new CustomButton("Solved! :)",
							 				  90,500,320,85,
							 				  (new Color(50,200,120)), (Color.WHITE),
							 				  (new Font("Tahoma", Font.BOLD, 19)), 
							 				  this, "Next");
		this.hasBeenSolved.setButtonVisibility(false);
		
		this.jumpToNextLevel = new CustomButton("Skip to the Next Level",
										  	//1480,650,300,70,
											830,990,290,60,
										  	(new Color(59,89,182)), (Color.WHITE),
										  	(new Font("Tahoma", Font.BOLD, 14)), 
										  	this, "Jump to Next Level");
		
		this.mainMenu = new CustomButton("Main Menu",
									  50,15,250,65,
									  (new Color(132,12,12)), (Color.WHITE),
									  (new Font("Tahoma", Font.BOLD, 14)), 
									  this, "Main Menu");
		this.exit = new CustomButton("Exit",
									  1620,15,250,65,
									  (new Color(132,12,12)), (Color.WHITE),
									  (new Font("Tahoma", Font.BOLD, 14)), 
									  this, "Exit");
		
		this.hint = new CustomButton("Use a Hint!",
									  90,190,300,70,
									  (new Color(50,200,120)), (Color.WHITE),
									  (new Font("Tahoma", Font.BOLD, 19)), 
									  this, "Hint");
		

		add(this.nextLevel.getButton());
		add(this.hasBeenSolved.getButton());
		add(this.jumpToNextLevel.getButton());
		add(this.mainMenu.getButton());
		add(this.exit.getButton());
		add(this.puzzleTimer.getTimerLabel());
		//add(this.hint.getButton());
		
		
		JLabel background;
		
		if (this.difficulty.equals("Easy"))
		{
			setupAndAddButtons(510, 100, 284, 284, (int)Math.sqrt(this.numberOfImages), 284);
			background = new JLabel(new ImageIcon("Data\\Pictures\\Backgrounds\\4.jpg"));
		}
		else if (this.difficulty.equals("Medium"))
		{
			setupAndAddButtons(540, 140, 160, 160, (int)Math.sqrt(this.numberOfImages), 160);
			background = new JLabel(new ImageIcon("Data\\Pictures\\Backgrounds\\12.jpg"));
		}
		else //if (this.difficulty.equals("Hard"))
		{
			setupAndAddButtons(490, 65, 130, 130, (int)Math.sqrt(this.numberOfImages), 130);
			background = new JLabel(new ImageIcon("Data\\Pictures\\Backgrounds\\10.jpg"));
		}
		
		//JLabel background = new JLabel(new ImageIcon("Data\\Pictures\\Backgrounds\\4.jpg"));
		background.setBounds(0, 0, 1920, 1080);
		add(background);
}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		if (command.charAt(0) == 'P')
		{
			if (!this.isSolved())
			{
				playSound("Click");
				
				if (this.lastClickedButton == null)
				{
					if (!this.puzzleTimer.isRunning())
						this.puzzleTimer.start();
					
					//int index = Character.getNumericValue(command.charAt(command.length() - 1));
					int index = Integer.parseInt(((command.split(" "))[1]));
					this.lastClickedButton = this.puzzleButtons.get(index);
					
					// Shrink Size
					Dimension tempDim = this.lastClickedButton.getButton().getSize();
					tempDim.setSize(tempDim.getWidth() - 24, tempDim.getHeight() - 24);
					this.lastClickedButton.getButton().setSize(tempDim);
					
					// Adjust Position
					Point tempPoint = this.lastClickedButton.getButton().getLocationOnScreen();
					tempPoint.setLocation(tempPoint.getX()+12, tempPoint.getY()+12);
					this.lastClickedButton.getButton().setLocation(tempPoint);
					
				}
				else
				{
					//int index = Character.getNumericValue(command.charAt(command.length() - 1));
					int index = Integer.parseInt(((command.split(" "))[1]));
					PuzzleButton currentClickedButton = this.puzzleButtons.get(index);
					
					if (! command.equals( this.lastClickedButton.getButtonActionCommand() ))
					{
						Icon i0 = this.lastClickedButton.getButtonIcon();
						Icon i1 = (currentClickedButton).getButtonIcon();
						
						
						this.lastClickedButton.setButtonIcon(i1);;
						(currentClickedButton).setButtonIcon(i0);
						
						checkIfInCorrectPosition(this.lastClickedButton);
						checkIfInCorrectPosition(currentClickedButton);
					
						if (isSolved())
						{
							this.jumpToNextLevel.setButtonVisibility(false);
							this.nextLevel.setButtonVisibility(true);
							this.hasBeenSolved.setButtonVisibility(true);
							
							scores.updateScore(this.difficulty, this.currentLevel-1);
							
							playSound("Level Finish");
						}
					
						
						// Re-adjust Size
						Dimension tempDim = this.lastClickedButton.getButton().getSize();
						tempDim.setSize(tempDim.getWidth() + 24, tempDim.getHeight() + 24);
						this.lastClickedButton.getButton().setSize(tempDim);
						
						// Re-adjust Position
						Point tempPoint = this.lastClickedButton.getButton().getLocationOnScreen();
						tempPoint.setLocation(tempPoint.getX()-12, tempPoint.getY()-12);
						this.lastClickedButton.getButton().setLocation(tempPoint);
						
						this.lastClickedButton = null;
					}
					else
					{
						// Re-adjust Size
						Dimension tempDim = this.lastClickedButton.getButton().getSize();
						tempDim.setSize(tempDim.getWidth() + 24, tempDim.getHeight() + 24);
						this.lastClickedButton.getButton().setSize(tempDim);
						
						// Re-adjust Position
						Point tempPoint = this.lastClickedButton.getButton().getLocationOnScreen();
						tempPoint.setLocation(tempPoint.getX()-12, tempPoint.getY()-12);
						this.lastClickedButton.getButton().setLocation(tempPoint);
						
						this.lastClickedButton = null;
					}
				}
			}
		}
		else if (command.equals("Jump to Next Level"))
		{	
			this.puzzleTimer.reset();
			
			if (this.currentLevel == 5)
			{
				goToLevel("First");
				CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
				cardLayout.show(contentPanel, "Level End Panel");
			}
			else
				goToLevel("Next");
			
			playSound("Jump to Next Level");
		}
		else if (command.equals("Next"))
		{	
			this.puzzleTimer.reset();
			
			if (this.currentLevel == 5)
			{	
				goToLevel("First");
				CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
				cardLayout.show(contentPanel, "Level End Panel");
			}
			else
				goToLevel("Next");
			
			playSound("Next");
		}
		
		else if (command.equals("Main Menu"))
		{
			goToLevel("First");
			this.puzzleTimer.reset();
			
			CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
			cardLayout.first(contentPanel);
			
			playSound("Main Menu");
		}
		else if (command.equals("Exit"))
		{
			playSound("Exit");
			this.puzzleTimer.reset();	
			System.exit(0);
		}
		
		//Update panel
		this.highscorePanel.updateScore();
	}
	
	
	private void getImagesAndSolvedPuzzle()
	{
		this.images = new ArrayList<Icon>(this.numberOfImages);
		for (int i = 0; i < this.numberOfImages; i++)
		{
			String path = "Data\\Pictures\\" + Integer.toString(this.currentDifficulty) + "\\" + Integer.toString(this.currentLevel) + "\\" + Integer.toString(i+1) + ".jpg";
			this.images.add(new ImageIcon(path));
		}
		this.solvedPuzzle = new ArrayList<Icon>(this.images);
	}
	
	
	private void getPuzzleButtons()
	{
		Collections.shuffle(this.images);
		this.puzzleButtons = new ArrayList<PuzzleButton>(this.numberOfImages);
		for (int i = 0; i < this.numberOfImages; i++)
		{
			if (this.images.get(i).equals( this.solvedPuzzle.get(i) ))
			{
				puzzleButtons.add(new PuzzleButton(this.images.get(i), i, true));
				this.numberOfSolved++;
				this.numberOfSolvedLabel.setText("Number of Pieces Solved: " + Integer.toString(this.numberOfSolved));
			}
			else
				puzzleButtons.add(new PuzzleButton(this.images.get(i), i, false));
		}
	}
	
	
	private void setupAndAddButtons(int starting_x, int starting_y, int width, int height,
		    					    int squareSize, int distance)
	{
		int current_x = starting_x;
		int current_y = starting_y;
		
		for (int i = 0; i < this.numberOfImages; i++)
		{
			current_x = starting_x + (i%squareSize)*distance;
			if ((i % squareSize == 0) && (i != 0)) current_y += distance;
			
			this.puzzleButtons.get(i).setButtonBounds(current_x, current_y, width, height);
			this.puzzleButtons.get(i).addButtonActionListener(this);
			this.puzzleButtons.get(i).setButtonActionCommand("Puzzle " + Integer.toString(i));
			
			add(this.puzzleButtons.get(i).getButton());
		}
	}
	
	
	private void goToLevel(String level)
	{
		if (level.equals("Next")) this.currentLevel++;
		else if (level.equals("First")) this.currentLevel = 1;
		else if (level.equals("This")) {} //this.currentLevel = this.currentLevel;
		
		this.jumpToNextLevel.setButtonVisibility(true);
		this.hasBeenSolved.setButtonVisibility(false);
		this.nextLevel.setButtonVisibility(false);
		
		getImagesAndSolvedPuzzle();
		
		this.numberOfSolved = 0;
		this.numberOfSolvedLabel.setText("Number of Pieces Solved: " + Integer.toString(this.numberOfSolved));
	
		Collections.shuffle(this.images);
		
		for (int i = 0; i < this.numberOfImages; i++)
		{
			this.puzzleButtons.get(i).setButtonIcon( this.images.get(i) );
			if (this.images.get(i).equals( this.solvedPuzzle.get(i) ))
			{
				this.numberOfSolved++;
				this.numberOfSolvedLabel.setText("Number of Pieces Solved: " + Integer.toString(this.numberOfSolved));
				this.puzzleButtons.get(i).setIfPositionIsCorrect(true);
			}
			else
			    this.puzzleButtons.get(i).setIfPositionIsCorrect(false);
		}
	}
	
	
	private boolean checkIfInCorrectPosition(PuzzleButton aButton)
	{
		if (aButton.getButtonIcon().equals( this.solvedPuzzle.get( aButton.getButtonIndex() ) ))
		{
			aButton.setIfPositionIsCorrect(true);;
			this.numberOfSolved++;
			this.numberOfSolvedLabel.setText("Number of Pieces Solved: " + Integer.toString(this.numberOfSolved));
			return true;
		}
		else
		{
			if (aButton.isPositionCorrect())
			{
				aButton.setIfPositionIsCorrect(false);
				numberOfSolved--;
				this.numberOfSolvedLabel.setText("Number of Pieces Solved: " + Integer.toString(this.numberOfSolved));
			}
			return false;
		}
	}
	
	private boolean isSolved()
	{
		if (this.numberOfSolved == this.numberOfImages)
			return true;
		else
			return false;
	}
	
	
	private void playSound(String type)
	{
		AudioStream BGM;
        try
        {
        	if (type.equals("Click"))
        		BGM = new AudioStream(new FileInputStream("Data\\Music\\click2.wav"));
        	else if (type.equals("Level Finish"))
        		BGM = new AudioStream(new FileInputStream("Data\\Music\\levelFinish1.wav"));
        	else if (type.equals("Jump to Next Level"))
        		BGM = new AudioStream(new FileInputStream("Data\\Music\\exit.wav"));
        	else if (type.equals("Next"))
        		BGM = new AudioStream(new FileInputStream("Data\\Music\\buttonClick.wav"));
        	else
        		BGM = new AudioStream(new FileInputStream("Data\\Music\\exit.wav"));
        	
    		AudioPlayer.player.start(BGM);
        }
        catch (Exception e)
        {
        	System.out.println("\n\tError Playing Click Sound\n");
        	e.printStackTrace();
        }
	}
	
	
	
	/*
	private int[] getCenterXY()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width  / 2;
		int centerY = screenSize.height / 2;
		int[] returnValue = {centerX, centerY};
		return returnValue;
	}
	
	private int[] getStartingXY(int puzzleButtonWidth, int puzzleButtonHeight, int distance, int squareLength)
	{
		int[] startingXY = getCenterXY();
		startingXY[0] += (Math.floorDiv(squareLength, 2) * puzzleButtonWidth) +
						 (Math.floorDiv(squareLength, 2) * distance);
		startingXY[0] += puzzleButtonWidth / 2;
		
		startingXY[1] += (Math.floorDiv(squareLength, 2) * puzzleButtonHeight) +
				 		 (Math.floorDiv(squareLength, 2) * distance);
		startingXY[1] += puzzleButtonHeight / 2;
		
		return startingXY;
	}
	*/

}

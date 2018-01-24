package cse2102FinalProject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import sun.audio.*;

import java.awt.event.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;


class MainJFrame extends JFrame implements ActionListener
{
	JPanel	        		mainContentPanel;
	MainMenuPanel			mainMenuPanel;
	ChooseDifficultyPanel	chooseDifficultyPanel;
	HighscorePanel			highscorePanel;
	HowToPlayPanel			howToPlayPanel;
	LevelEndPanel			levelEndPanel;
	PuzzlePanel     		easyPuzzlePanel;
	PuzzlePanel     		mediumPuzzlePanel;
	PuzzlePanel     		hardPuzzlePanel;
	
	public MainJFrame()
	{
		super("PUZZLE APP");
        
		mainContentPanel = new JPanel();
		mainContentPanel.setLayout(new CardLayout());
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("Data//Pictures//Cursor//cursor3.png");
		Cursor c = toolkit.createCustomCursor(image , new Point(mainContentPanel.getX(), 
											  					mainContentPanel.getY()), "img");
		mainContentPanel.setCursor (c);
		
		
		mainMenuPanel   = new MainMenuPanel(this.mainContentPanel);
		chooseDifficultyPanel   = new ChooseDifficultyPanel(this.mainContentPanel);
		highscorePanel = new HighscorePanel(this.mainContentPanel);
		howToPlayPanel = new HowToPlayPanel(this.mainContentPanel);
		levelEndPanel = new LevelEndPanel(this.mainContentPanel);
		
		
		easyPuzzlePanel = new PuzzlePanel(this.mainContentPanel, "Easy", highscorePanel);
		mediumPuzzlePanel = new PuzzlePanel(this.mainContentPanel, "Medium", highscorePanel);
		hardPuzzlePanel = new PuzzlePanel(this.mainContentPanel, "Hard", highscorePanel);
		
		
		mainContentPanel.add(mainMenuPanel, "Main Menu Panel");
		mainContentPanel.add(chooseDifficultyPanel, "Choose Difficulty Panel");
		mainContentPanel.add(highscorePanel, "Highscore Panel");
		mainContentPanel.add(howToPlayPanel, "How To Play Panel");
		mainContentPanel.add(levelEndPanel, "Level End Panel");
		
		mainContentPanel.add(easyPuzzlePanel, "Easy Puzzle Panel");
		mainContentPanel.add(mediumPuzzlePanel, "Medium Puzzle Panel");
		mainContentPanel.add(hardPuzzlePanel, "Hard Puzzle Panel");
		
		
		
		setContentPane(this.mainContentPanel);        
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		setVisible(true);
		music();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		System.out.println("Width: " + width + "\nHeight: " + height);
	}
	
	
	private void music()
	{
		Thread sound;
        sound = new Thread()
        {
           public void run()
           {
        	   //AudioPlayer MGP = AudioPlayer.player;
               AudioStream BGM;
               //AudioData MD;
               //ContinuousAudioDataStream loop = null;

               for(;;)
               {
            	   try
            	   { 
            		   BGM = new AudioStream(new FileInputStream("Data\\Music\\sample_1.wav"));
            		   AudioPlayer.player.start(BGM);
            		   //BGM.close();
            		   //MD = BGM.getData(); //not necessary
            		   //loop=new ContinuousAudioDataStream(MD); //not necessary
		
              		   sleep(17500);// enter the elapse time of your sound to avoid noise
            	   }
            	   catch(Exception e)
            	   {
            		   e.printStackTrace();
            	   }
            	   
            	   //MGP.start(loop); // It does nothing.I was trying to use this but no success.
               }
           }
        };
        
        sound.start();        
		
		/*
		AudioPlayer MGP = AudioPlayer.player;
	    AudioStream BGM;
	    AudioData MD;
	    ContinuousAudioDataStream loop = null;
	    
	    try 
	    {
	    	BGM = new AudioStream(new FileInputStream("Data\\Music\\sample_1.wav"));
	        MD = BGM.getData();
	        loop = new ContinuousAudioDataStream(MD);
	    } 
	    catch (Exception error) 
	    {
	    	System.out.println("\n\tError Playing Music File...\n");
	    	error.printStackTrace();
	    }
	    
	    MGP.start(loop);
	    */
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		if (command.equals("Exit"))
		{
			this.dispose();
		}
	}
}


package cse2102FinalProject;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MainMenuPanel extends JPanel implements ActionListener
{
	private JPanel contentPanel;
	CustomButton playGame;
	CustomButton highScore;
	CustomButton howToPlay;
	CustomButton exit;
	
	public MainMenuPanel(JPanel mainPanel)
	{
		this.contentPanel = mainPanel;
		setLayout(null);
		this.setSize(1920, 1080);
		
		this.playGame = new CustomButton("Play Game",
										  830,300,(int)((1920*15.625)/100),(int)((1080*8.797)/100),
										  (new Color(59,89,182)), (Color.WHITE),
										  (new Font("Tahoma", Font.BOLD, 17)), 
										  this, "Play Game");
		this.highScore = new CustomButton("High Scores",
										  830,450,300,95,
										  (new Color(59,89,182)), (Color.WHITE),
										  (new Font("Tahoma", Font.BOLD, 17)), 
										  this, "High Score");
		this.howToPlay = new CustomButton("How To Play",
										  830,600,300,95,
										  (new Color(59,89,182)), (Color.WHITE),
										  (new Font("Tahoma", Font.BOLD, 17)), 
										  this, "Instructions");
		this.exit = new CustomButton("Exit",
										  830,750,300,95,
										  (new Color(59,89,182)), (Color.WHITE),
										  (new Font("Tahoma", Font.BOLD, 17)), 
										  this, "Exit");
		
		add(playGame.getButton());
		add(highScore.getButton());
		add(howToPlay.getButton());
		add(exit.getButton());
		
		JLabel background = new JLabel(new ImageIcon("Data\\Pictures\\Backgrounds\\1.jpg"));
		background.setBounds(0, 0, 1920, 1080);
		add(background);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
		
		if (command.charAt(0) == 'P')
		{
			cardLayout.show(contentPanel, "Choose Difficulty Panel");
			playSound("Click");
		}
		else if (command.charAt(0) == 'H')
		{
			cardLayout.show(contentPanel, "Highscore Panel");
			playSound("Click");
		}
		else if (command.charAt(0) == 'I')
		{
			cardLayout.show(contentPanel, "How To Play Panel");
			playSound("Click");
		}
		else if (command.charAt(0) == 'E')
		{
			playSound("Exit");
			System.exit(0);
		}
	}
	
	private void playSound(String type)
	{
		AudioStream BGM;
        try
        {
        	if (type.equals("Click"))
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
}

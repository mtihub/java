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
import cse2102FinalProject.CustomButton;

public class ChooseDifficultyPanel extends JPanel implements ActionListener
{
	private JPanel contentPanel;
	private CustomButton easy, medium, hard, back, exit;
	
	public ChooseDifficultyPanel(JPanel mainPanel)
	{
		this.contentPanel = mainPanel;
		setLayout(null);
		this.setSize(1920, 1080);
		
		this.easy = new CustomButton("Easy",
									830,400,300,65,
									(new Color(59,89,182)), (Color.WHITE),
									(new Font("Tahoma", Font.BOLD, 17)), 
									this, "Easy");
		this.medium = new CustomButton("Medium",
										830,500,300,65,
										(new Color(59,89,182)), (Color.WHITE),
										(new Font("Tahoma", Font.BOLD, 17)), 
										this, "Medium");
		this.hard = new CustomButton("Hard",
									830,600,300,65,
									(new Color(59,89,182)), (Color.WHITE),
									(new Font("Tahoma", Font.BOLD, 17)), 
									this, "Hard");
		this.back = new CustomButton("Back",
								  50,15,250,65,
								  (new Color(132,12,12)), (Color.WHITE),
								  (new Font("Tahoma", Font.BOLD, 14)), 
								  this, "Back");
		this.exit = new CustomButton("Exit",
								  1620,15,250,65,
								  (new Color(132,12,12)), (Color.WHITE),
								  (new Font("Tahoma", Font.BOLD, 14)), 
								  this, "Exit");
		
		add(easy.getButton());
		add(medium.getButton());
		add(hard.getButton());
		add(back.getButton());
		add(exit.getButton());
		
		JLabel background = new JLabel(new ImageIcon("Data\\Pictures\\Backgrounds\\2.jpg"));
		background.setBounds(0, 0, 1920, 1080);
		add(background);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
		
		if (command.equals("Easy"))
		{
			cardLayout.show(contentPanel, "Easy Puzzle Panel");
			playSound("Click");
		}
		else if (command.charAt(0) == 'M')
		{
			cardLayout.show(contentPanel, "Medium Puzzle Panel");
			playSound("Click");
		}
		else if (command.charAt(0) == 'H')
		{
			cardLayout.show(contentPanel, "Hard Puzzle Panel");
			playSound("Click");
		}
		else if (command.charAt(0) == 'B')
		{
			cardLayout.show(contentPanel, "Main Menu Panel");
			playSound("Back");
		}
		else if (command.equals("Exit"))
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

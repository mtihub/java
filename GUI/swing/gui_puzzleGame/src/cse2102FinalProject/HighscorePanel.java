package cse2102FinalProject;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class HighscorePanel extends JPanel implements ActionListener
{
	private JPanel contentPanel;
	private CustomButton back, exit;
	private ArrayList<JLabel> scoreLabels;
	private Highscores scores;
	private int currentRow = 0;
	
	
	public HighscorePanel(JPanel mainPanel)
	{
		this.contentPanel = mainPanel;
		setLayout(null);
		this.setSize(1920, 1080);
		
		this.scoreLabels = new ArrayList<JLabel>(8);
		this.scores = new Highscores();
		
		for (int i = 0, yCoord = 100; i < 8; i++, yCoord += 60)
		{
			this.scoreLabels.add(new JLabel());
			this.scoreLabels.get(i).setFont(new Font("Bell MT", Font.BOLD, 35));
			if (i == 0) this.scoreLabels.get(i).setBounds(700,yCoord,1600,50);
			else this.scoreLabels.get(i).setBounds(100,yCoord,1600,50);
			this.scoreLabels.get(i).setForeground(Color.WHITE);
		}
		
		
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
		
		buildDisplay();
		
		add(back.getButton());
		add(exit.getButton());
		for (JLabel label : this.scoreLabels)
			add(label);
		
		
		JLabel background = new JLabel(new ImageIcon("Data\\Pictures\\Backgrounds\\8.jpg"));
		background.setBounds(0, 0, 1920, 1080);
		add(background);
	}
	
	
	public void updateScore()
	{
		this.scores.updateScores();
		buildDisplay();
	}
	
	private void buildDisplay()
	{
		this.scoreLabels.get(currentRow++).setText("High Scores");
		this.scoreLabels.get(currentRow++).setText("---------------------------------------------------------------------------------------------------------------");
		this.scoreLabels.get(currentRow++).setText("                                                            Easy                              Medium                              Hard");
		
		for (int i = 0; i < 5; i++)
		{
			String temp = new String("Level " + Integer.toString(i+1) + "          ");
			if (this.scores.easyScore.get(i) > 0) temp += "                                        " + this.scores.easyScore.get(i) + "                                   ";
			else temp += "                                      ........ " + "                                 ";
			
			if (this.scores.mediumScore.get(i) > 0) temp += this.scores.mediumScore.get(i) + "                                 ";
			else temp += " ........ " + "                               ";
			
			if (this.scores.hardScore.get(i) > 0) temp += this.scores.hardScore.get(i);
			else temp += " ........ ";
			
			
			this.scoreLabels.get(currentRow++).setText(temp);	
		}
		
		this.currentRow = 0;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
		
		if (command.equals("Back"))
		{
			cardLayout.show(this.contentPanel, "Main Menu Panel");
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

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

public class LevelEndPanel extends JPanel implements ActionListener
{
	private JPanel contentPanel;
	private CustomButton mainMenu, exit;
	private JLabel label_1, label_2;
	
	public LevelEndPanel(JPanel mainPanel) //, String finishedLevel)
	{
		this.contentPanel = mainPanel;
		setLayout(null);
		this.setSize(1920, 1080);
		
		this.label_1 = new JLabel();
		label_1.setText("That's All There is in This Level !!");
		label_1.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
		label_1.setBounds(615,60,1600,60);
		label_1.setForeground(Color.WHITE);

		this.label_2 = new JLabel();
		label_2.setText("Do Try Out the Other Difficulties for New Puzzles :)");
		label_2.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
		label_2.setBounds(465,120,1600,60);
		label_2.setForeground(Color.WHITE);
		
		this.mainMenu = new CustomButton("Go to Main Menu",
										250,400,300,180,
										(new Color(132,12,12)), (Color.WHITE),
										(new Font("Tahoma", Font.BOLD, 14)), 
										this, "Main Menu");
		this.exit = new CustomButton("Exit",
									  1350,400,300,180,
									  (new Color(132,12,12)), (Color.WHITE),
									  (new Font("Tahoma", Font.BOLD, 14)), 
									  this, "Exit");
		
		
		add(label_1);
		add(label_2);
		add(mainMenu.getButton());
		add(exit.getButton());
		
		
		JLabel background = new JLabel(new ImageIcon("Data\\Pictures\\Backgrounds\\13.jpg"));
		background.setBounds(0, 0, 1920, 1080);
		add(background);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
		
		if (command.equals("Main Menu"))
		{
			cardLayout.show(this.contentPanel, "Main Menu Panel");
			playSound("Click");
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

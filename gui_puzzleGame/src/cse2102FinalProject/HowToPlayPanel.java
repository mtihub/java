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

public class HowToPlayPanel extends JPanel implements ActionListener
{
	private JPanel contentPanel;
	private CustomButton back, exit;
	
	public HowToPlayPanel(JPanel mainPanel)
	{
		this.contentPanel = mainPanel;
		setLayout(null);
		this.setSize(1920, 1080);
		
		
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
		
		add(back.getButton());
		add(exit.getButton());
		
		
		JLabel background = new JLabel(new ImageIcon("Data\\Pictures\\Backgrounds\\14.jpg"));
		background.setBounds(0, 0, 1920, 1080);
		add(background);

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

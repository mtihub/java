package cse2102FinalProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class PuzzleTimer 
{
	private JLabel timerLabel = new JLabel("Time: ...");
	
	private int sec = 0;
	private int min = 0;
	private Timer timer;
	
	public PuzzleTimer() 
	{
		timerLabel.setFont(new Font("Bell MT", Font.BOLD, 35));
		timerLabel.setBounds(1450,460,500,60);
        timerLabel.setForeground(Color.WHITE);
		
		timer = new Timer(1000, new ActionListener() 
								{
						      		@Override
						      		public void actionPerformed(ActionEvent e)
						      		{
						      			sec++;
						      			min = Math.floorDiv(sec, 60);
						      			timerLabel.setText("Time: " + String.format("%02d%n", min) + 
														   ":" + 
														   String.format("%02d%n", (sec - (min*60))));
						      		}
								});
	}
	
	
	public JLabel getTimerLabel()
	{
		return this.timerLabel;
	}
	
	public void start()
	{
		timer.start();
	}
	
	public void stop()
	{
		timer.stop();
	}
	
	public void reset()
	{
		timer.stop();
		sec = 0;
		min = 0;
		timerLabel.setText("Time: ...");
	}
	
	public int stopTimerAndGetSeconds()
	{
		timer.stop();
		return (min*60) + sec;
	}
	
	public boolean isRunning()
	{
		return timer.isRunning();
	}
	
}

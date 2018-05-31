package cse2102FinalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import com.sun.glass.ui.Cursor;

public class CustomButton 
{
	private JButton button;
	
	public CustomButton()
	{
		this.button = new JButton();
		this.button.setFocusPainted(false);
	}
	
	public CustomButton(String name)
	{
		this.button = new JButton(name);
		this.button.setFocusPainted(false);
	}
	
	public CustomButton(Icon image)
	{
		this.button = new JButton(image);
		this.button.setFocusPainted(false);
	}
	
	public CustomButton(String name,
						int x, int y, int width, int height,
						Color background, Color foreground,
						Font font,
						ActionListener action, String command)
	{
		this.button = new JButton(name);
		this.button.setBounds(x,y,width,height);
		this.button.setBackground(background);
		this.button.setForeground(foreground);
		this.button.setFocusPainted(false);
		this.button.setFont(font);
		this.button.addActionListener(action);
		this.button.setActionCommand(command);
	}
	
	public CustomButton(String name,
						int x, int y, int width, int height,
						Icon image, Color foreground,
						Font font,
						ActionListener action, String command)
	{
		this.button = new JButton(image);
		this.button.setName(name);
		this.button.setBounds(x,y,width,height);
		this.button.setForeground(foreground);
		this.button.setFocusPainted(false);
		this.button.setFont(font);
		this.button.addActionListener(action);
		this.button.setActionCommand(command);
	}
	
	public void setButtonName(String name)
	{
		this.button.setName(name);
	}
	
	public void setButtonBounds(int x, int y, int width, int height)
	{
		this.button.setBounds(x,y,width,height);
	}
	
	public void setButtonIcon(Icon image)
	{
		this.button.setIcon(image);
	}
	
	public void setButtonBackground(Color background)
	{
		this.button.setBackground(background);
	}
	
	public void setButtonForeground(Color foreground)
	{
		this.button.setForeground(foreground);
	}
	
	public void setButtonFont(Font font)
	{
		this.button.setFont(font);
	}
	
	public void addButtonActionListener(ActionListener action)
	{
		this.button.addActionListener(action);
	}
	
	public void setButtonActionCommand(String command)
	{
		this.button.setActionCommand(command);
	}
	
	public void setButtonVisibility(boolean isVisible)
	{
		this.button.setVisible(isVisible);
	}
	
	public JButton getButton()
	{
		return this.button;
	}
	
}

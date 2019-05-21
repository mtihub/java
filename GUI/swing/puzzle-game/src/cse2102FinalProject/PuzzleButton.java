package cse2102FinalProject;


import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;

public class PuzzleButton 
{
	
	private final JButton button; 
	private final int index;
	private boolean isPositionCorrect;
	
	public PuzzleButton(Icon image, int i)
	{
		this.button = new JButton(image);
		this.index = i;
		this.isPositionCorrect = false;
		
	}
	
	public PuzzleButton(Icon image, int i, boolean isCorrect)
	{
		this.button = new JButton(image);
		this.index = i;
		this.isPositionCorrect = isCorrect;
	}
	
	public void setButtonIcon(Icon image)
	{
		this.button.setIcon(image);
	}
	
	public void setButtonBounds(int x, int y, int width, int height)
	{
		this.button.setBounds(x, y, width, height);
	}
	
	public void addButtonActionListener(ActionListener action)
	{
		this.button.addActionListener(action);
	}
	
	public void setButtonActionCommand(String command)
	{
		this.button.setActionCommand(command);
	}
	
	
	public void setIfPositionIsCorrect(boolean isCorrect)
	{
		this.isPositionCorrect = isCorrect;
	}
	
	
	public JButton getButton()
	{
		return this.button;
	}
	
	public Icon getButtonIcon()
	{
		return this.button.getIcon();
	}
	
	public String getButtonActionCommand()
	{
		return this.button.getActionCommand();
	}
	
	public int getButtonIndex()
	{
		return this.index;
	}
	
	public boolean isPositionCorrect()
	{
		return this.isPositionCorrect;
	}
	
	
	
	
}


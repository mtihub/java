package cse2102FinalProject;

import java.util.Random;

//import sun.audio.*;
//import java.io.FileInputStream;


class Main// extends JFrame implements ActionListener
{	
	// Main Class
	public static void main(String args[])
	{	
		new MainJFrame();
		//music();
		
		
	}
	
	/*
	private static void music()
	{
		AudioPlayer MGP = AudioPlayer.player;
	    AudioStream BGM;
	    AudioData MD;
	    ContinuousAudioDataStream loop = null;
	    
	    try 
	    {
	    	BGM = new AudioStream(new FileInputStream("Data\\Music\\IonicBondHiraeth.mp3"));
	        MD = BGM.getData();
	        loop = new ContinuousAudioDataStream(MD);
	    } 
	    catch (Exception error) 
	    {
	    	System.out.println("\n\tError Playing Music File...\n");
	    	error.printStackTrace();
	    }
	    
	    MGP.start(loop);
	}
	*/
}
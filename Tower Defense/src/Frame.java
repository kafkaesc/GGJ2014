import java.awt.*;
import sun.audio.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Frame extends JFrame
{
	public static String title = "Cold War";//title of game/file
	public static Dimension size = new Dimension(1220, 730);//dimensions of frame
	public Frame()        //constructor
	{
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();            //calls init
	}
	
	public void init()//what's in the frame
	{
		setLayout(new GridLayout(1,1,0,0));  //sets the grid layout to of the 1 by 1
		Screen screen = new Screen(this);   //creates a new screen
		add(screen);                         //adds a screen
		setVisible(true);                    //lets you see the frame
	}
	
	public static void main(String [] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		File file = new File("Resources/Audio/ColdWar_V5.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.loop(Clip.LOOP_CONTINUOUSLY);  // repeat forever
        
		Frame frame = new Frame();//runs frame
		frame.setLayout(null);

	}	
}
import java.awt.*;
import javax.swing.*;


public class Frame extends JFrame
{
	
	public static String title = "Save the King";//title of game/file
	public static Dimension size = new Dimension(700,550);//dimensions of frame
	public Frame()//constructor
	{
		setTitle(title);//sets title to title
		setSize(size);// sets size to size
		setResizable(false);//does not allow it to be resized
		setLocationRelativeTo(null);//
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes by closing window
		
		init();//calls in it
	}
	
	public void init()//whats in the frame
	{
		setLayout(new GridLayout(1,1,0,0));//sets the grid layout to of the 1 by 1
		Screen screen = new Screen(this);//creates a new screen
		add(screen);//adds a screen
		setVisible(true);//lets you see the frame
	}
	
	public static void main(String [] args)
	{
		Frame frame = new Frame();//runs frame
	}
	
}

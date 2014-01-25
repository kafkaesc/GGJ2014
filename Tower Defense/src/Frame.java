import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame
{
	public static String title = "Cold War";//title of game/file
	public static Dimension size = new Dimension(750, 550);//dimensions of frame
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
	
	public static void main(String [] args)
	{
		Frame frame = new Frame();//runs frame
	}	
}
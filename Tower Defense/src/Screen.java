import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.*;

public class Screen extends JPanel implements Runnable
{
	public static int myWidth,myHeight;
	
	public static Image[] tileSet_Grass = new Image[100];
	public static Image[] tileSet_Air   = new Image[100]; 
	public static Image[] tileSet_res   = new Image[100];
	
	public static boolean isFirst = true; //for the first run through
	
	public static Point mse = new Point(0,0);
	
	public Thread thread = new Thread(this);//the game loop
	
	public static Room room;//creates the room
	public static Save save;// for save file
	public static Store store;
	
	public Screen(Frame frame)//for the screen
	{
		frame.addMouseListener(new KeyHandel());
		frame.addMouseMotionListener(new KeyHandel());
		thread.start();//starts the thread
	}
	
	public void addEnemy()
	{
		
	}
	
	public void define()//
	{
		room = new Room();//creates a new room
		save = new Save();//creates a new save(levels)
		store = new Store();
		
		for(int i = 0; i<tileSet_Grass.length;i++)//runs for loop from 0 to length of grass tile
		{
			tileSet_Grass[i] = new ImageIcon("Resources/tileSet_Grass.png").getImage();//gets the image
			tileSet_Grass[i] = createImage(new FilteredImageSource(tileSet_Grass[i].getSource(),new CropImageFilter(0,26*i,26,26)));
			//create and image and crops it to the size given in room
		}
		
		for(int i = 0; i<tileSet_Air.length;i++)
		{
			tileSet_Air[i] = new ImageIcon("Resources/tileSet_Air.png").getImage();
			tileSet_Air[i] = createImage(new FilteredImageSource(tileSet_Air[i].getSource(),new CropImageFilter(0,26*i,26,26)));
		}
		
		tileSet_res[0] = new ImageIcon("res/cell.png").getImage();
		
		save.loadDefault();//opens level change number to change level
		
	}
	public void paintComponent(Graphics g)//opens paint
	{
		if(isFirst)//if it is the first run
		{
			myWidth = getWidth();//get the width
			myHeight = getHeight();//get the height
			define();//run define
			isFirst = false;//set to not first anymore
		}
		g.setColor(new Color(100,100,100));
		g.fillRect(0, 0, getWidth(), getHeight());//clears the rectangle
		g.setColor(new Color (0,0,0));
		g.drawLine(room.block[0][0].x -1 ,0, room.block[0][0].x-1, room.block[room.worldHeight -1][0].y + room.blockSize); // draw right line
		g.drawLine(room.block[0][room.worldWidth-1].x + room.blockSize ,0, room.block[0][room.worldWidth-1].x + room.blockSize, room.block[room.worldHeight -1][0].y + room.blockSize);//draw left line
		g.drawLine(room.block[0][0].x, room.block[room.worldHeight-1][0].y + room.blockSize, room.block[0][room.worldWidth-1].x + room.blockSize,room.block[room.worldHeight-1][0].y +room.blockSize);//draw bottom line
		room.draw(g); // drawing the room
		store.draw(g);//drawing the store
	}
	
	public void run()//runs the game
	{
		while(true)// game loop
		{
			if(!isFirst)// if not the first
			{
				room.physics();//runs the physics engine

			}
			repaint();//repaints
			try{
				thread.sleep(1);
			} catch(Exception e) {}
		}
		
	}
}


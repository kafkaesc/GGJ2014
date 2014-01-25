import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Screen extends JPanel implements Runnable
{
	public static int myWidth, myHeight;

	public static Image[] tileSet_Grass = new Image[100];
	public static Image[] tileSet_Air   = new Image[100]; 
	public static Image[] tileSet_res   = new Image[100];

	public static boolean isFirst = true; // for the first run through
	
	public static boolean isRed = true;

	public static Point mse = new Point(0,0);

	public Thread thread = new Thread(this);// the game loop

	public static Room room;    // creates the room
	public static Room floor;   // the floor characters move on
	public static Save save;    // for save file
	public static Store store;
	public static int prevCommand;
	public static int command = -1;

	
	public Screen(Frame frame)   // for the screen
	{
		frame.addMouseListener(new KeyHandel());
		frame.addMouseMotionListener(new KeyHandel());
		thread.start();           // starts the thread
	}

	public void addEnemy(int r, int c, int enemyID)
	{
		room.block[r][c].groundID = enemyID;
	}
	
	public void addUnit(int r, int c, int unitID)
	{
		room.block[r][c].groundID = unitID;
	}
	
	// r1, c1 => the attacker
	// r2, c2 => the defender
	public void attack(int r1, int c1, int r2, int c2)
	{
		if((room.block[r1][c1].groundID >= 30 &&
			room.block[r1][c1].groundID <= 39) &&
			room.block[r2][c2].groundID == 50)
		{
			room.block[r1][c1].groundID = floor.block[r1][c1].groundID;
			room.block[r2][c2].groundID = floor.block[r2][c2].groundID;

		}
	}
	
	public void enemyMarch(int r, int c)
	{
		if(room.block[r][c].groundID >= 30 && 
				room.block[r][c].groundID <= 39)
		{
			if(r == 7)
			{
				room.block[r][c].groundID = floor.block[r][c].groundID;
				// do damage
			}
			else if(room.block[r+1][c].groundID >= 50 && 
					room.block[r+1][c].groundID <= 59)
			{
				attack(r, c, r+1, c);
			}
			else if(room.block[r+1][c].groundID >= 0 && 
					room.block[r+1][c].groundID <= 2)
			{
				room.block[r+1][c].groundID = room.block[r][c].groundID;
				room.block[r][c].groundID = floor.block[r][c].groundID;
			}
		}
	}
	
	public int pickSnowmanID()
	{
		Random r = new Random();
		int num =  r.nextInt(100) + 1;
		int snowmanID = -1;

		if (num >= 1 && num <= 69) {
			snowmanID = 30; }
		else if (num >= 70 && num <= 89) {
			snowmanID = 31; }
		else if (num >= 90 && num <= 100) {
			snowmanID = 32; }

		assert(snowmanID != -1);
		return snowmanID;
	}

	public void define()//
	{
		room = new Room();  // creates a new room
		floor = new Room(); // the floor saves terrain pieces
		save = new Save();  // creates a new save(levels)
		save.loadFloor();
		store = new Store();

		for(int i = 0; i < tileSet_Grass.length; i++)//runs for loop from 0 to length of grass tile
		{
			tileSet_Grass[i] = new ImageIcon("Resources/mars01.png").getImage();//gets the image
			tileSet_Grass[i] = createImage(new FilteredImageSource(tileSet_Grass[i].getSource(),
					new CropImageFilter(0, 52*i, 52, 52)));
			//create and image and crops it to the size given in room
		}

		for(int i = 0; i < tileSet_Air.length; i++)
		{
			tileSet_Air[i] = new ImageIcon("Resources/tileSet_Air.png").getImage();
			tileSet_Air[i] = createImage(new FilteredImageSource(tileSet_Air[i].getSource(),new CropImageFilter(0,26*i,26,26)));
		}
	}

	public void paintComponent(Graphics g)    //opens paint
	{
		// if it is the first run
		if(isFirst)
		{
			myWidth  = getWidth();  //get the width
			myHeight = getHeight(); //get the height
			define();               //run define
			isFirst = false;        //set to not first anymore
		}
		g.setColor(new Color(100,100,100));
		g.fillRect(0, 0, getWidth(), getHeight());//clears the rectangle
		g.setColor(new Color (50,50,50));
		g.drawLine(room.block[0][0].x -1 ,0, room.block[0][0].x-1, room.block[room.worldHeight -1][0].y + room.blockSize); // draw right line
		g.drawLine(room.block[0][room.worldWidth-1].x + room.blockSize ,0, room.block[0][room.worldWidth-1].x + room.blockSize, room.block[room.worldHeight -1][0].y + room.blockSize);//draw left line
		g.drawLine(room.block[0][0].x, room.block[room.worldHeight-1][0].y + room.blockSize, room.block[0][room.worldWidth-1].x + room.blockSize,room.block[room.worldHeight-1][0].y +room.blockSize);//draw bottom line
		room.draw(g);   // drawing the room
		store.draw(g);  // drawing the store
	}
	


	public void run()      //runs the game
	{
		// get the random number generator
		// create the first few random creatures
	    addMouseListener(new KeyHandel());

		Random r = new Random();
		int enemyID[]     = new int[3];
		int coordinates[] = new int[3];
		for(int i = 0; i < 3; i++) coordinates[i] = r.nextInt(14);
			
		while(true)        // game loop
		{

			if(!isFirst)    // if not the first
			{
				for(int i = room.block.length - 1; i >= 0; i--)
				{
					for(int j = room.block[i].length-1; j >= 0; j--)
					{
						enemyMarch(i, j);
					}
				}
				
				// randomly add new enemy units (they move next turn)
				r = new Random();
				for(int i = 0; i < 3; i++) enemyID[i] = pickSnowmanID();
//				System.out.println(enemyID[0]);
//				System.out.println(enemyID[1]);
//				System.out.println(enemyID[2]);



				for(int i = 0; i < 3; i++) coordinates[i] = r.nextInt(14);
				
				addEnemy(0, coordinates[0], enemyID[0]);
				addEnemy(0, coordinates[1], enemyID[1]);
				addEnemy(0, coordinates[2], enemyID[2]);
				boolean wait = true;
				int temp1;
				while(wait)
				{
					temp1 = command;
					System.out.print("");
//					System.out.println("LOOK HERE : " + command + " and " + temp1);
					if(temp1 != command)
					{
						isRed = !isRed;
						wait = false;
					}
				}
				addUnit(7, 4, 50);
			}
			repaint();      // repaints
			try{
				Thread.sleep(1);
				repaint();      // repaints

			} catch(Exception e) {}
		}

	}
}


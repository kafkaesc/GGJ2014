import javax.swing.*;
import java.awt.*;
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

	public static Point mse = new Point(0,0);

	public Thread thread = new Thread(this);// the game loop

	public static Room room;    // creates the room
	public static Save save;    // for save file
	public static Store store;

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
		if(room.block[r1][c1].groundID == 30 &&
				room.block[r2][c2].groundID == 50)
		{
			room.block[r1][c1].groundID = 0;
		}
	}
	
	public void enemyMarch(int r, int c)
	{
		System.out.println(room.block[r][c].groundID);
		if(room.block[r][c].groundID >= 30 && 
				room.block[r][c].groundID <= 39)
		{
			if(r == 7)
			{
				room.block[r][c].groundID = 0;
				// do damage
			}
			else if(room.block[r+1][c].groundID == 0)
			{
				room.block[r+1][c].groundID = room.block[r][c].groundID;
				room.block[r][c].groundID = 0;
			}
			else if(room.block[r+1][c].groundID >= 50 && 
					room.block[r+1][c].groundID <= 59)
			{
				attack(r, c, r+1, c);
			}
		}
	}

	public void define()//
	{
		room = new Room();  // creates a new room
		save = new Save();  // creates a new save(levels)
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

		//tileSet_res[0] = new ImageIcon("res/cell.png").getImage();
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
		g.setColor(new Color (0,0,0));
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
		Random r = new Random();
		int coordinates[] = new int[3];
		for(int i = 0; i < 3; i++) coordinates[i] = r.nextInt(14);
		
		while(true)        // game loop
		{
			if(!isFirst)    // if not the first
			{
				for(int i = room.block.length - 1; i >= 0; i--)
				{
					for(int j = 0; j < room.block[i].length; j++)
					{
						enemyMarch(i, j);
					}
				}
				
				// randomly add new enemy units (they move next turn)
				addEnemy(0, coordinates[0], 30);
				addEnemy(0, coordinates[1], 30);
				addEnemy(0, coordinates[2], 30);
				for(int i = 0; i < 3; i++) coordinates[i] = r.nextInt(14);
				
				boolean wait = true;
				while(wait)
				{
					Scanner sc = new Scanner(System.in);
					String eh = sc.next();
					if(eh.equals("n"))
						wait = false;
//					frame.
				}
				addUnit(7, 4, 50);
			}
			repaint();      // repaints
			try{
				thread.sleep(1);
			} catch(Exception e) {}
		}

	}
}


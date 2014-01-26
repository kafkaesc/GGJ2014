import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	
	public static Point mse = new Point(0,0);

	public Thread thread = new Thread(this);// the game loop

	public static Room room;    // creates the room
	public static Room floor;   // the floor characters move on
	public static Save save;    // for save file
	public static Store store;
	public static Player player;
	public static int prevCommand;
	
	public static boolean activelyAdding = false;
	public static boolean selectedCol    = false;
	public static boolean switchFlag = false;
	public static boolean changeGen = true;
	public static boolean gameOver = false;

	public static int newUnit = 0;
	public static int newCol  = 0;
	public static int command = -1;
	
	private static boolean[] available;
	
	Image bg = new ImageIcon("Resources/Screens/gameborder-title.png").getImage();
	
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
		if(room.block[r1][c1].groundID >= 30 &&
				room.block[r1][c1].groundID <= 39)
		{
			// if the attacker is a snowgoon
			if(room.block[r2][c2].groundID == Value.toaster0)
			{
				// if the defender is a toaster0, the snowgoon is eaten
				kill(r1, c1);
				room.block[r2][c2].groundID = Value.toaster1;
				player.profit(1);
			}
			else if(room.block[r2][c2].groundID == Value.toaster1)
			{
				// if the defender is a toaster1, the snowgoon 
				// is eaten and the toaster disappears
				kill(r1, c1);
				kill(r2, c2);
				player.profit(1);
			}
		}
		else if(room.block[r1][c1].groundID >= 50 &&
				room.block[r1][c1].groundID <= 59)
		{
			// if the attacker is an astronaut
			if(room.block[r1][c1].groundID == Value.basic)
			{
				if(room.block[r2][c2].groundID >= 30 &&
						room.block[r2][c2].groundID <= 39)
				{
					kill(r1, c1);
					kill(r2, c2);
					player.profit(1);
				}
			}
			if(room.block[r1][c1].groundID == Value.western)
			{
				// check for friendly fire!
				if(c2 - 1 >= 0 && room.block[r2][c2-1].groundID >= 30 &&
						room.block[r2][c2-1].groundID <= 39)
				{
					kill(r2, c2 - 1);
					player.profit(1);
				}
				if(room.block[r2][c2].groundID >= 30 &&
						room.block[r2][c2].groundID <= 39)
				{
					kill(r2, c2);
					player.profit(1);
				}
				if(c2 + 1 < room.block[r2].length && room.block[r2][c2+1].groundID >= 30 &&
						room.block[r2][c2+1].groundID <= 39)
				{
					kill(r2, c2 + 1);
					player.profit(1);
				}
			}
			if(room.block[r1][c1].groundID == Value.blowdryer)
			{
				// check out of bounds and then FF
				if(r2 >= 0 && room.block[r2][c2].groundID >= 30 && 
						room.block[r2][c2].groundID <= 39)
				{
					kill(r2, c2);
					player.profit(1);
				}
				if(r2 - 1 >= 0 && room.block[r2-1][c2].groundID >= 30 && 
						room.block[r2-1][c2].groundID <= 39)
				{
					kill(r2 - 1, c2);
					player.profit(1);
				}
				if(r2 - 2 >= 0 && room.block[r2-2][c2].groundID >= 30 && 
						room.block[r2-2][c2].groundID <= 39)
				{
					kill(r2 - 2, c2);
					player.profit(1);
				}
			}
			if(room.block[r1][c1].groundID == Value.cherry)
			{
				// check out of bounds and then FF
				if(r2 >= 0)
				{
					if(room.block[r2][c2].groundID >= 30 && 
							room.block[r2][c2].groundID <= 39)
					{
						kill(r2, c2);
						player.profit(1);
					}
					if(c2 - 1 >= 0 && room.block[r2][c2-1].groundID >= 30 && 
							room.block[r2][c2-1].groundID <= 39)
					{
						kill(r2, c2 - 1);
						player.profit(1);
					}
					if(c2 + 1 < room.block[r2].length && 
							room.block[r2][c2+1].groundID >= 30 && 
							room.block[r2][c2+1].groundID <= 39)
					{
						kill(r2, c2 + 1);
						player.profit(1);
					}
				}
				if(r2 - 1 >= 0)
				{
					if(room.block[r2-1][c2].groundID >= 30 && 
							room.block[r2-1][c2].groundID <= 39)
					{
						kill(r2 - 1, c2);
						player.profit(1);
					}
					if(c2 - 1 >= 0 && room.block[r2-1][c2-1].groundID >= 30 && 
							room.block[r2-1][c2-1].groundID <= 39)
					{
						kill(r2 - 1, c2 - 1);
						player.profit(1);
					}
					if(c2 + 1 < room.block[r2-1].length && 
							room.block[r2-1][c2+1].groundID >= 30 && 
							room.block[r2-1][c2+1].groundID <= 39)
					{
						kill(r2 - 1, c2 + 1);
						player.profit(1);
					}
				}
				if(r2 - 2 >= 0)
				{
					if(room.block[r2-2][c2].groundID >= 30 && 
							room.block[r2-2][c2].groundID <= 39)
					{
						kill(r2 - 2, c2);
						player.profit(1);
					}
					if(c2 - 1 >= 0 && room.block[r2-2][c2-1].groundID >= 30 && 
							room.block[r2-2][c2-1].groundID <= 39)
					{
						kill(r2 - 2, c2 - 1);
						player.profit(1);
					}
					if(c2 + 1 < room.block[r2-2].length && 
							room.block[r2-2][c2+1].groundID >= 30 && 
							room.block[r2-2][c2-1].groundID <= 39)
					{
						kill(r2 - 2, c2 + 1);
						player.profit(1);
					}
				}
			}
		}
		repaint();
	}
	
	public void enemyMarch(int r, int c)
	{
		if(room.block[r][c].groundID >= 30 && 
				room.block[r][c].groundID <= 39)
		{
			if(r == 7)
			{
				if(room.block[r][c].groundID == Value.snowman00) 
					player.burn(1);
				else if(room.block[r][c].groundID == Value.snowman01)
					player.burn(6);
				else if(room.block[r][c].groundID == Value.snowman02)
					player.burn(16);
				
				room.block[r][c].groundID = floor.block[r][c].groundID;
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
	
	public void kill(int r, int c)
	{
		room.block[r][c].groundID = floor.block[r][c].groundID;
	}
	
	public void unitMarch(int r, int c)
	{
		if(room.block[r][c].groundID >= 50 && 
				room.block[r][c].groundID <= 59)
		{
			if(r == 0)
			{
				kill(r, c);
			}
			else if(room.block[r][c].groundID == Value.toaster0 || 
					room.block[r][c].groundID == Value.toaster1)
			{
				// toaster are traps, stay still
			}
			else if(room.block[r-1][c].groundID >= 30 && 
					room.block[r-1][c].groundID <= 39)
			{
				// attack the snowgoon menace!
				attack(r, c, r-1, c);
			}
			else if(room.block[r-1][c].groundID >= 0 && 
					room.block[r-1][c].groundID <= 2)
			{
				// march onward soldier!
				room.block[r-1][c].groundID = room.block[r][c].groundID;
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

	public void checkPrices()
	{
		int wallet = player.checkResources();
		if (wallet == 0)
		{
			for(int i = 1; i < 8; i++)
				available[i] = false;
		}
		if (wallet == 1)
		{
			for(int i = 1; i < 8; i++)
				available[i] = false;
			available[2] = true;
		}
		if (wallet >= 2 && wallet < 5)
		{
			for(int i = 2; i < 7; i++)
				available[i] = true;
			available[5] = false;
		}
		if (wallet >= 5 && wallet < 15)
		{
			for(int i = 1; i < 7; i++)
				available[i] = true;
		}
		if (wallet >= 15 )
		{
			for(int i = 1; i < 8; i++)
				available[i] = true;
		}
	}
	
	public void define()//
	{
		// game set-up
		room   = new Room();  // creates a new room
		floor  = new Room(); // the floor saves terrain pieces
		save   = new Save();  // creates a new save(levels)
		save.loadFloor();
		store  = new Store();
		player = new Player();
		available = new boolean[8];
		
		for(int i = 0; i < 7; i++) available[i] = true;
		available[7] = false;

		for(int i = 0; i < tileSet_Grass.length; i++)//runs for loop from 0 to length of grass tile
		{
			tileSet_Grass[i] = new ImageIcon("Resources/mars01.png").getImage();//gets the image
			tileSet_Grass[i] = createImage(new FilteredImageSource(tileSet_Grass[i].getSource(),
					new CropImageFilter(0, 52*i, 52, 52)));
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
		g.setColor(new Color(204, 204, 204));
		g.drawImage(bg, 0 , 0, 1220, 700, null);
		g.setColor(new Color (50, 50, 50));
		g.drawLine(room.block[0][0].x -1 ,0, room.block[0][0].x-1, room.block[room.worldHeight -1][0].y + room.blockSize); // draw right line
		g.drawLine(room.block[0][room.worldWidth-1].x + room.blockSize ,0, room.block[0][room.worldWidth-1].x + room.blockSize, room.block[room.worldHeight -1][0].y + room.blockSize);//draw left line
		g.drawLine(room.block[0][0].x, room.block[room.worldHeight-1][0].y + room.blockSize, room.block[0][room.worldWidth-1].x + room.blockSize,room.block[room.worldHeight-1][0].y +room.blockSize);//draw bottom line
		room.draw(g);   // drawing the room
		store.draw(g, available, changeGen, gameOver);  // drawing the store
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
		
		boolean gameOver = false;
		while(true && !gameOver)        // game loop
		{

			if(!isFirst)    // if not the first
			{

				for(int i = 0; i < room.block.length; i++)
					for(int j = 0; j < room.block[i].length; j++)
						unitMarch(i, j);
				
				for(int i = room.block.length - 1; i >= 0; i--)
					for(int j = room.block[i].length - 1; j >= 0; j--)
						enemyMarch(i, j);
				
				System.out.println("Life: " + player.checkLife() + 
						", Money: " + player.checkResources());
				
				checkPrices();
				
				// randomly add new enemy units (they move next turn)
				r = new Random();
				for(int i = 0; i < 3; i++) enemyID[i] = pickSnowmanID();
				for(int i = 0; i < 3; i++) coordinates[i] = r.nextInt(14);
				
				addEnemy(0, coordinates[0], enemyID[0]);
				addEnemy(0, coordinates[1], enemyID[1]);
				addEnemy(0, coordinates[2], enemyID[2]);
				boolean turnOver = false;
				while(command == -1 && !turnOver && !gameOver)
				{
					System.out.print("");
					if(player.checkLife() == 0) break;
					
					if(command == 1)
					{
						turnOver = true;
						repaint();
					}
					if(command == 2 && available[1])
					{
						player.switchGeneral();
						repaint();
						player.loss(3);
						changeGen = !changeGen;
						repaint();
						checkPrices();
						available[1] = false;

					}
					if(command == 3 && available[2])
					{
						activelyAdding = true;
						while(!selectedCol) { System.out.print(""); }
						addUnit(7, newCol, Value.basic);
						activelyAdding = false;
						selectedCol = false;
						repaint();
						player.loss(1);
						checkPrices();
					}
					if(command == 4 && available[3])
					{
						activelyAdding = true;
						while(!selectedCol) { System.out.print(""); }
						addUnit(7, newCol, Value.blowdryer);
						activelyAdding = false;
						selectedCol = false;
						repaint();
						player.loss(2);
						checkPrices();
					}
					if(command == 5 && available[4])
					{
						activelyAdding = true;
						while(!selectedCol) { System.out.print(""); }
						addUnit(7, newCol, Value.western);
						activelyAdding = false;
						selectedCol = false;
						repaint();
						player.loss(2);
						checkPrices();
					}
					if(command == 6 && available[5])
					{
						activelyAdding = true;
						while(!selectedCol) { System.out.print(""); }
						addUnit(7, newCol, Value.cherry);
						activelyAdding = false;
						selectedCol = false;
						repaint();
						player.loss(5);
						checkPrices();
					}
					if(command == 7 && available[6])
					{
						activelyAdding = true;
						while(!selectedCol) { System.out.print(""); }
						addUnit(7, newCol, Value.toaster0);
						activelyAdding = false;
						selectedCol = false;
						repaint();
						player.loss(2);
						checkPrices();
					}
					if(command == 8 && available[7])
					{
						double newWealth = player.checkResources();
						for(int i = 0; i < 14; i++)
						{
							for(int j = 0; j < 8; j++)
							{
								if(room.block[j][i].groundID >= 50 &&
										room.block[j][i].groundID <= 59)
									newWealth -= 0.5;
								
								if(room.block[j][i].groundID >= 30 &&
										room.block[j][i].groundID <= 39)
									newWealth += 1.0;
								
								kill(j, i);
								player.socialism((int)newWealth);
							}
						}
						repaint();
						player.loss(15);
						checkPrices();
					}
					command = -1;
				}
				if(player.checkLife() == 0) break;
			}
			repaint();          // repaints
			try{
				Thread.sleep(1);
				repaint();      // repaints

			} catch(Exception e) {}
		}
		repaint();
	}
}


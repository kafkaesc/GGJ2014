import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

public class Block extends Rectangle
{
	public int groundID; //ID for grass
	public int airID;   //ID for air
	
	// our images (thanks Ash!)
	public static Image mars1      = new ImageIcon("Resources/Mars/TILE_0000_lt-bg1.png").getImage();
	public static Image mars2      = new ImageIcon("Resources/Mars/TILE_0001_lt-bg2.png").getImage();
	public static Image mars3      = new ImageIcon("Resources/Mars/TILE_0002_lt-bg3.png").getImage();
	public static Image snowBrown  = new ImageIcon("Resources/Snowgoon/snowmen_0004_black-L.png").getImage();
	public static Image snowBlue   = new ImageIcon("Resources/Snowgoon/snowmen_0003_blue-R.png").getImage();
	public static Image snowRed    = new ImageIcon("Resources/Snowgoon/snowmen_0001_red-R.png").getImage();
	public static Image astroBasic = new ImageIcon("Resources/Astronaut/astronaut_0000_astronaut.png").getImage();
	public static Image astroWest  = new ImageIcon("Resources/Astronaut/astronaut_0001_astronaut-western.png").getImage();	
	public static Image astroHair  = new ImageIcon("Resources/Astronaut/astronaut_0002_astronaut-blowdryer.png").getImage();	
	public static Image toaster0   = new ImageIcon("Resources/Weapon/weapons_0000_toaster-set.png").getImage();
	public static Image toaster1   = new ImageIcon("Resources/Weapon/weapons_0001_toaster-caught-1.png").getImage();

	public Block(int x, int y, int width, int height, int grassID, int airID)//constructor takes in parameters
	{
		setBounds(x, y, width, height);//sets bounds
		this.airID    = airID;     //sets AirID
	}
	
	// in charge of drawing icons for all pieces and terrain
	public void draw(Graphics g, int id)
	{
		switch(id)
		{
		case 0:
			g.drawImage(mars1, x ,y, width, height, null);
			break;
		case 1:
			g.drawImage(mars2, x ,y, width, height, null);			
			break;
		case 2:
			g.drawImage(mars3, x ,y, width, height, null);
			break;
		case 30:
			g.drawImage(snowBrown, x ,y, width, height, null);
			break;
		case 31:
			if(!Screen.player.isRed)
				g.drawImage(snowBlue, x ,y, width, height, null);
			else
				g.drawImage(snowBrown, x ,y, width, height, null);
			break;
		case 32:
			if(Screen.player.isRed)
				g.drawImage(snowRed, x ,y, width, height, null);
			else
				g.drawImage(snowBrown, x ,y, width, height, null);
			break;
		case 50:
			g.drawImage(astroBasic, x ,y, width, height, null);
			break;
		case 51:
			g.drawImage(astroWest, x ,y, width, height, null);
			break;
		case 52:
			g.drawImage(astroHair, x ,y, width, height, null);
			break;
		case 53:
			g.drawImage(toaster0, x ,y, width, height, null);
			break;
		case 54:
			g.drawImage(toaster1, x ,y, width, height, null);
			break;
		default:
			g.drawImage(mars1, x ,y, width, height, null);
			break;
		}
		if(airID != Value.airAir)
		{
			g.drawImage(Screen.tileSet_Grass[airID], x, y, width, height, null);
		}
	}
}
import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

public class Block extends Rectangle
{
	public int groundID; //ID for grass
	public int airID;   //ID for air
	
	// our images (thanks Ash!)
	public static Image mars1  = new ImageIcon("Resources/mars01.png").getImage();
	public static Image mars2  = new ImageIcon("Resources/mars02.png").getImage();
	public static Image mars3  = new ImageIcon("Resources/mars03.png").getImage();
	public static Image snowBrown  = new ImageIcon("Resources/snowmen_0004_black-L.png").getImage();
	public static Image snowBlue   = new ImageIcon("Resources/snowmen_0003_blue-R.png").getImage();
	public static Image snowRed    = new ImageIcon("Resources/snowmen_0001_red-R.png").getImage();
	public static Image space1 = new ImageIcon("Resources/cell.png").getImage();
	
	public Block(int x, int y, int width, int height, int grassID, int airID)//constructor takes in parameters
	{
		setBounds(x, y, width, height);//sets bounds
		this.groundID = groundID;//sets grassID
		this.airID    = airID;//sets AirID
	}
	
	public void draw(Graphics g, int id)  //draws graphics
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
			if(!Screen.isRed)
				g.drawImage(snowBlue, x ,y, width, height, null);
			else
				g.drawImage(snowBrown, x ,y, width, height, null);			break;
		case 32:
			if(Screen.isRed)
				g.drawImage(snowRed, x ,y, width, height, null);
			else
				g.drawImage(snowBrown, x ,y, width, height, null);
			break;
		case 50:
			g.drawImage(space1, x ,y, width, height, null);
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
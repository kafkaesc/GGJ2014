import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

public class Block extends Rectangle
{
	public int groundID; //ID for grass
	public int airID;   //ID for air
	public int cellID;  //ID for cell
	
	
	// our images (thanks Ash!)
	public static Image mars1  = new ImageIcon("Resources/mars01.png").getImage();
	public static Image mars2  = new ImageIcon("Resources/mars02.png").getImage();
	public static Image mars3  = new ImageIcon("Resources/mars03.png").getImage();
	public static Image snow1  = new ImageIcon("Resources/snowman01.png").getImage();
	public static Image space1 = new ImageIcon("Resources/cell.png").getImage();
	
	public Block(int x, int y, int width, int height, int grassID, int airID)//constructor takes in parameters
	{
		setBounds(x, y, width, height);//sets bounds
		this.groundID = groundID;//sets grassID
		this.airID    = airID;//sets AirID
		this.cellID   = cellID;
	}
	
	public void draw(Graphics g, int id)  //draws graphics
	{
		switch(id)
		{
		case 0:
			Random r = new Random();
			int marsTerrain = r.nextInt(3) + 1;
			switch(marsTerrain)
			{
			case 1:
				g.drawImage(mars1, x ,y, width, height, null);
				break;
			case 2:
				g.drawImage(mars2, x ,y, width, height, null);
				break;
			case 3:
				g.drawImage(mars3, x ,y, width, height, null);
				break;
			}
			break;
		case 1:
		case 2:
		case 30:
			g.drawImage(snow1, x ,y, width, height, null);
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
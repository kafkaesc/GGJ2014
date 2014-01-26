import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

//continue from 1:13:00  http://www.youtube.com/watch?v=Euiqdd09n68
public class Store
{
	public static int shopWidth = 8;
	public static int buttonSize = 52;
	public static int cellSpace = 2;
	public static int awayFromRoom = 29;
	
	public Rectangle[] button = new Rectangle[shopWidth];
	
	public static Image nextTurn   = new ImageIcon("Resources/Button/buttons_0010_next-turn.png").getImage();
	public static Image genBlue    = new ImageIcon("Resources/Button/buttons_0009_switch-BLUE.png").getImage();
	public static Image genRed     = new ImageIcon("Resources/Button/buttons_0008_switch-RED.png").getImage();
	public static Image genBlueOff = new ImageIcon("Resources/Button/buttons_0014_switch-BLUE-OFF.png").getImage();
	public static Image genRedOff  = new ImageIcon("Resources/Button/buttons_0013_switch-RED-OFF.png").getImage();
	public static Image basic      = new ImageIcon("Resources/Button/buttons_0007_normal-astronaut-ON.png").getImage();
	public static Image basicOff   = new ImageIcon("Resources/Button/buttons_0006_normal-astronaut-OFF.png").getImage();
	public static Image blowdry    = new ImageIcon("Resources/Button/buttons_0005_blowdryer-ON.png").getImage();
	public static Image blowdryOff = new ImageIcon("Resources/Button/buttons_0004_blowdryer-OFF.png").getImage();
	public static Image western    = new ImageIcon("Resources/Button/buttons_0012_western-ON.png").getImage();
	public static Image westernOff = new ImageIcon("Resources/Button/buttons_0011_western-OFF.png").getImage();
	public static Image snowRed    = new ImageIcon("Resources/Snowgoon/snowmen_0001_red-R.png").getImage();
	public static Image toasterOff = new ImageIcon("Resources/Button/buttons_0000_toaster-OFF.png").getImage();
	public static Image toaster    = new ImageIcon("Resources/Button/buttons_0001_toaster-ON.png").getImage();
	public static Image blackholeOff  = new ImageIcon("Resources/Button/buttons_0002_blackhole-OFF.png").getImage();	
	public static Image blackhole  = new ImageIcon("Resources/Button/buttons_0003_blackhole-ON.png").getImage();	
	
	public Store()
	{
		define();
	}
	
	public void define()
	{
		for(int i = 0; i < button.length; i++)
		{
			button[i] = new Rectangle((Screen.myWidth/2) - 
					((shopWidth * (buttonSize + cellSpace))/2) + 
					((buttonSize + cellSpace) * i), 
					Screen.room.block[Screen.room.worldHeight-1][0].y + 
					Screen.room.blockSize + cellSpace + 
					awayFromRoom, buttonSize, buttonSize);
			
		}
	}
	
	public void draw(Graphics g, boolean[] av, boolean Gen_to_draw)
	{
		for(int i = 0; i<button.length;i++)
		{
//				g.setColor(new Color(130, 130, 110, 100));
				if(av[0]) g.drawImage(nextTurn, 393, 447,52 , 52, null);
				
				if(Gen_to_draw && av[1]) g.drawImage(genBlue, 447, 447,52 , 52, null);
				else if(Gen_to_draw && !av[1]) g.drawImage(genBlueOff, 447, 447,52 , 52, null);
				else if(!Gen_to_draw && av[1]) g.drawImage(genRed, 447, 447,52 , 52, null);
				else if(!Gen_to_draw && !av[1])g.drawImage(genRedOff, 447, 447,52 , 52, null);
				
				if(av[2]) g.drawImage(basic, 501, 447,52 , 52, null);
				else g.drawImage(basicOff, 501, 447,52 , 52, null);
				
				if(av[3])g.drawImage(blowdry, 555, 447,52 , 52, null);
				else g.drawImage(blowdryOff, 555, 447,52 , 52, null);
				
				if(av[4])g.drawImage(western, 609, 447,52 , 52, null);
				else g.drawImage(westernOff, 609, 447,52 , 52, null);
				
				if (av[5])g.drawImage(snowRed, 663, 447,52 , 52, null);
				else g.drawImage(snowRed, 663, 447,52 , 52, null);
				
				if(av[6])g.drawImage(toaster, 717, 447,52 , 52, null);
				else g.drawImage(toasterOff, 717, 447,52 , 52, null);
				
				if(av[7])g.drawImage(blackhole, 771, 447,52 , 52, null);
				else g.drawImage(blackholeOff, 771, 447,52 , 52, null);

//				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
//			g.fillRect(/*Screen.tileSet_res[0], */button[i].x, button[i].y, button[i].width, button[i].height /*,null*/);

		}
	}
}

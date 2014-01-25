import java.awt.*;
public class Block extends Rectangle
{
	public int grassID; //ID for grass
	public int airID;   //ID for air
	public int cellID;  //ID for cell
	
	public Block(int x, int y, int width, int height, int grassID, int airID)//constructor takes in parameters
	{
		setBounds(x,y,width,height);//sets bounds
		this.grassID = grassID;//sets grassID
		this.airID = airID;//sets AirID
		this.cellID = cellID;
	}
	
	public void draw(Graphics g)//draws graphics
	{
		g.drawImage(Screen.tileSet_Grass[grassID], x, y, width, height, null);
		if(airID != Value.airAir)
		{
			g.drawImage(Screen.tileSet_Grass[airID], x, y, width, height, null);
		}
	}
}
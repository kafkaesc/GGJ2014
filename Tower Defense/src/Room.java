import java.awt.*;
public class Room
{
	
	public int worldWidth  = 14;  //sets the width of the world
	public int worldHeight = 8;   //sets the height of the world
	public int blockSize   = 52; //sets the size of each block
	public Block [][] block;      //creates a 2d array for the blocks
			
	public Room()
	{
		define();//calls define
	}
	
	public void define() //defines the room
	{
		block = new Block[worldHeight][worldWidth];//sets the size of the 2d array to the world height and width
		for(int y = 0;y<block.length;y++)//loops through columns
		{
			for(int x = 0; x<block[0].length;x++)//loops through rows
			{
				block[y][x] = new Block((Screen.myWidth/2) - ((worldWidth*blockSize)/2) + (x*blockSize) , y*blockSize ,blockSize, blockSize , Value.mars1, Value.airAir);
			}
		}
	}
	public void draw(Graphics g)
	{
		for(int y = 0; y<block.length; y++)  //loops through columnns
		{
			for(int x = 0; x < block[0].length; x++)  //loops thorugh rows
			{
				block[y][x].draw(g, block[y][x].groundID);  //draws the block 2d array
			}
		}
	}
	
	public void physics()//physics engine
	{
		
	}
	
}

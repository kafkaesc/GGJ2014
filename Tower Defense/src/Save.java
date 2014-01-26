import java.io.*;
import java.util.*;

public class Save 
{
	public void loadFloor()
	{
		Random r = new Random();
		int terrainSource = r.nextInt(3);
		for(int x = 0; x < 14; x++)
		{
			for(int y = 0; y < 8; y++)
			{
				Screen.floor.block[y][x].groundID = terrainSource;
				Screen.room.block[y][x].groundID = terrainSource;
				terrainSource = r.nextInt(3);
			}
		}
	}
	
	public void loadSave(File loadPath)
	{
		try{
			Scanner sc = new Scanner(loadPath);
			
			while(sc.hasNext())
			{
				for(int y = 0; y<Screen.room.block.length;y++)
				{
					for(int x = 0; x<Screen.room.block[0].length;x++)
					{
						Screen.room.block[y][x].groundID = sc.nextInt();
					}
				}
				
				for(int y = 0; y<Screen.room.block.length;y++)
				{
					for(int x = 0; x<Screen.room.block[0].length;x++)
					{
						Screen.room.block[y][x].airID = sc.nextInt();
					}
				}
			}
			sc.close();
		}catch (Exception e){}
	}
}

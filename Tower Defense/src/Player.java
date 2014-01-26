
public class Player {

	// instance vars
	private int life;
	private int resources;
	public boolean isRed;
	
	Player()
	{
		life  = 100;
		resources = 8;
		isRed = false;
	}
	
	public void burn(int n)
	{
		life -= n;
		if(life < 0) life = 0;
	}
	
	public int checkLife()
	{
		return life;
	}
	
	public int checkResources()
	{
		return resources;
	}
	
	public void profit(int n)
	{
		resources += n;
	}
	
	public void loss(int n)
	{
		resources -= n;
	}
	
	public void socialism(int n)
	{
		resources = n;
	}
	
	public void switchGeneral() 
	{ 
		isRed = !isRed; 
	}
}

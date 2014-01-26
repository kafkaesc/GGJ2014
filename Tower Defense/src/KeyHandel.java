import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KeyHandel implements MouseMotionListener, MouseListener
{

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("(" + e.getX() + ", " + e.getY() + ")");
		// clicks for the menu
		if(e.getX() >= 394 && e.getX() <= 446 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.command = 1;
		}
		if(e.getX() >= 447 && e.getX() <= 499 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.command = 2;
		}
		if(e.getX() >= 500 && e.getX() <= 552 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.command = 3;
			System.out.println("C");
		}
		if(e.getX() >= 553 && e.getX() <= 605 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.command = 4;
			System.out.println("D");
		}
		if(e.getX() >= 606 && e.getX() <= 658 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.command = 5;
			System.out.println("E");
		}
		if(e.getX() >= 659 && e.getX() <= 711 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.command = 6;
			System.out.println("F");
		}
		if(e.getX() >= 712 && e.getX() <= 764 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.command = 7;
			System.out.println("G");
		}
		if(e.getX() >= 765 && e.getX() <= 817 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.command = 8;
			System.out.println("H");
		}

		// clicks for the bottom row
		//Y is alwyas 0 -416
		// col1 X bound is 242 and 294. add 52 to both bounds for subsequent Columns 
		if(e.getX() >= 242 && e.getX() <= 294 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding)
			{
				Screen.newCol = 0;
				Screen.selectedCol = true;
			}
			//			System.out.println("col1");
		}
		if(e.getX() >= 294 && e.getX() <= 346 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 1;
				Screen.selectedCol = true;
			}
			//			System.out.println("col2");
		}
		if(e.getX() >= 346 && e.getX() <= 398 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) {
				Screen.newCol = 2;
				Screen.selectedCol = true;
			}
			//			System.out.println("col3");
		}
		if(e.getX() >= 398 && e.getX() <= 450 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 3;
				Screen.selectedCol = true;

			}
			//			System.out.println("col4");
		}
		if(e.getX() >= 450 && e.getX() <= 502 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 4;
				Screen.selectedCol = true;
			}
			//			System.out.println("col5");
		}
		if(e.getX() >= 502 && e.getX() <= 554 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 5;
				Screen.selectedCol = true;
			}
			//			System.out.println("col6");
		}
		if(e.getX() >= 554 && e.getX() <= 606 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 6;
				Screen.selectedCol = true;
			}
			//			System.out.println("col7");
		}
		if(e.getX() >= 606 && e.getX() <= 658 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 7;
				Screen.selectedCol = true;
			}
			//			System.out.println("col8");
		}
		if(e.getX() >= 658 && e.getX() <= 710 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 8;
				Screen.selectedCol = true;
			}
			//			System.out.println("col9");
		}
		if(e.getX() >= 710 && e.getX() <= 762 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 9;
				Screen.selectedCol = true;
			}
			//			System.out.println("col10");
		}
		if(e.getX() >= 762 && e.getX() <= 814 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 10;
				Screen.selectedCol = true;
			}
			//			System.out.println("col11");
		}
		if(e.getX() >= 814 && e.getX() <= 866 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 11;
				Screen.selectedCol = true;
			}
			//			System.out.println("col12");
		}
		if(e.getX() >= 866 && e.getX() <= 918 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 12;
				Screen.selectedCol = true;
			}
			//			System.out.println("col13");
		}
		if(e.getX() >= 918 && e.getX() <= 970 && e.getY() >= 0 && e.getY() <= 416)
		{
			if(Screen.activelyAdding) 
			{
				Screen.newCol = 13;
				Screen.selectedCol = true;
			}
			//			System.out.println("col14");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		Screen.mse = new Point((e.getX()) + ((Frame.size.width - Screen.myWidth)/2), 
				(e.getY()) + ((Frame.size.height - Screen.myWidth)/2));	
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		Screen.mse = new Point((e.getX()) + ((Frame.size.width - Screen.myWidth)/2), 
				(e.getY()) + ((Frame.size.height - Screen.myWidth)/2) +50);
	}

}

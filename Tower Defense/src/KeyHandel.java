import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KeyHandel implements MouseMotionListener, MouseListener
{

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX() >= 394 && e.getX() <= 446 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.prevCommand = Screen.command;
			Screen.command++;
		}
		if(e.getX() >= 447 && e.getX() <= 499 && e.getY() <= 501 && e.getY() >= 449)
		{
			System.out.println("B");
		}
		if(e.getX() >= 500 && e.getX() <= 552 && e.getY() <= 501 && e.getY() >= 449)
		{
			System.out.println("C");
		}
		if(e.getX() >= 553 && e.getX() <= 605 && e.getY() <= 501 && e.getY() >= 449)
		{
			System.out.println("D");
		}
		if(e.getX() >= 606 && e.getX() <= 658 && e.getY() <= 501 && e.getY() >= 449)
		{
			System.out.println("E");
		}
		if(e.getX() >= 659 && e.getX() <= 711 && e.getY() <= 501 && e.getY() >= 449)
		{
			System.out.println("F");
		}
		if(e.getX() >= 712 && e.getX() <= 764 && e.getY() <= 501 && e.getY() >= 449)
		{
			System.out.println("G");
		}
		if(e.getX() >= 765 && e.getX() <= 817 && e.getY() <= 501 && e.getY() >= 449)
		{
			System.out.println("H");
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

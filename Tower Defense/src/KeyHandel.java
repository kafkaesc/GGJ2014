import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KeyHandel implements MouseMotionListener, MouseListener
{

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX() >=160 && e.getX() <= 590 && e.getY() <= 501 && e.getY() >= 449)
		{
			Screen.prevCommand = Screen.command;
			Screen.command++;
			//System.out.println(Screen.command);
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

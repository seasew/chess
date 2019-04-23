import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestClickListener implements MouseListener
{

	private TestComp c;

	public TestClickListener(TestComp c)
	{
		this.c = c;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		int xs = e.getXOnScreen();
		int ys = e.getYOnScreen();
		System.out.println("(" + x + ", " + y + ")");
		System.out.println("(" + xs + ", " + ys + ")");
		c.drawCircle(x, y);
		c.drawCircle(xs, ys);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

}

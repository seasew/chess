import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public class ResizeListener implements ComponentListener
{
	private ChessComp comp;
	private JFrame frame;

	public ResizeListener(ChessComp comp, JFrame frame)
	{
		this.comp = comp;
		this.frame = frame;
	}

	@Override
	public void componentResized(ComponentEvent e)
	{

	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e)
	{
		// TODO Auto-generated method stub

	}

}

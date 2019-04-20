import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickListener implements MouseListener
{
	private boolean firstClick;
	private ChessComp comp;

	private int firstPixelX;
	private int firstPixelY;

	private String description;

	public ClickListener(ChessComp comp)
	{
		firstClick = true;
		this.comp = comp;
		firstPixelX = 0;
		firstPixelY = 0;
		description = "";
	}

	public String getDescription()
	{
		return description;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// first click or second click?
		if (firstClick)
		{
			description = "";
			firstPixelX = e.getXOnScreen();
			firstPixelY = e.getYOnScreen();
		}
		// move if second click
		else
		{
			description = comp.movePiece(firstPixelX, firstPixelY, e.getXOnScreen(), e.getYOnScreen());
		}

		firstClick = !firstClick;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// do nothing

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// do nothing

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// do nothing

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// do nothing

	}

}

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class TestComp extends JComponent
{
	private Ellipse2D.Double cur;

	public TestComp()
	{
		cur = new Ellipse2D.Double();
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(cur);

		repaint();
	}

	public void drawCircle(int pixelX, int pixelY)
	{
		cur = new Ellipse2D.Double(pixelX, pixelY, 10, 10);
	}
}

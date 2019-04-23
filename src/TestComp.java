import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class TestComp extends JComponent
{
	private Ellipse2D.Double cur;
	private Ellipse2D.Double cur2;

	public TestComp()
	{
		cur = new Ellipse2D.Double();
		cur2 = new Ellipse2D.Double();
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(cur);
		g2.draw(cur2);
		repaint();
	}

	public void drawCircle(int pixelX, int pixelY, int pixelX2, int pixelY2)
	{
		cur = new Ellipse2D.Double(pixelX, pixelY, 10, 10);
		cur2 = new Ellipse2D.Double(pixelX2, pixelY2, 10, 10);
	}
}

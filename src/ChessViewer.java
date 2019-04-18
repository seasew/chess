import javax.swing.JFrame;

public class ChessViewer
{

	public static void main(String[] args)
	{

		JFrame frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ChessComp comp = new ChessComp();
		frame.add(comp);

		// Mouse Listener for clicking the squares
		ClickListener clickL = new ClickListener();

		frame.addMouseListener(clickL);

		frame.setVisible(true);

	}

}

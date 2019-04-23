import javax.swing.JFrame;

public class TestViewer
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TestComp comp = new TestComp();
		frame.add(comp);

		TestClickListener cl = new TestClickListener(comp);
		frame.addMouseListener(cl);

		frame.setVisible(true);
	}

}

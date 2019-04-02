import java.awt.Graphics;

import javax.swing.JComponent;

public class ChessComp extends JComponent
{

    public static final int SQUARE_SIZE = 100;
    public static final int X = 200;
    public static final int Y = 200;

    private ChessGame game;

    public ChessComp()
    {
	game = new ChessGame();
    }

    @Override
    public void paint(Graphics g)
    {
	// print text at the (0, 0)

	// print the coordinates
	// print the chessboard & pieces images
    }

    /**
     * Moves the piece at pixelX to pixelY if the move is valid based on the
     * requirements from ChessGame.<br>
     * Returns a description of what occured, using standard chess lingo :)
     * 
     * @param pixelX
     *            the amount of pixels in the x direction
     * @param pixelY
     *            the amount of pixels in the y directions
     * @return
     */
    public String movePiece(int pixelX1, int pixelY1, int pixelX2, int pixelY2)
    {
	// converts the pixels to i and j (row and col)

    }
}

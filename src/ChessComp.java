import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class ChessComp extends JComponent
{

    // the size (in pixels) of the squares of the board
    public static final int SQUARE_SIZE = 100;

    // the pixel coordinates of the top left corner of the chessboard
    public static final int X = 200;
    public static final int Y = 200;

    private ChessGame game;

    /**
     * Constructs a ChessComp with a default ChessGame.<br>
     * This displays the chessboard and the corresponding piece images, as well
     * as a title and descriptions of the moves.
     */
    public ChessComp()
    {
	game = new ChessGame();
    }

    @Override
    public void paint(Graphics g)
    {
	Graphics2D g2 = (Graphics2D) g;
	// print text at the (0, 0)

	// print the coordinates
	// print the chessboard & pieces images
    }

    /**
     * Moves the piece at pixelX to pixelY if the move is valid based on the
     * requirements from ChessGame.<br>
     * Returns a description of what occured, using standard chess lingo :)
     * 
     * @param pixelX1
     *            the amount of pixels in the x direction of the starting
     *            position
     * @param pixelY1
     *            the amount of pixels in the y directions of the starting
     *            position
     * @param pixelX2
     *            the amount of pixels in the x direction of the ending position
     * @param pixelY2
     *            the amount of pixels in the y direction of the ending position
     * @return
     */
    public String movePiece(int pixelX1, int pixelY1, int pixelX2, int pixelY2)
    {
	// converts the pixels to i and j (row and col)
	Position p1 = new Position((pixelX1 - X) / SQUARE_SIZE, (pixelY1 - Y) / SQUARE_SIZE);
	Position p2 = new Position((pixelX2 - X) / SQUARE_SIZE, (pixelY2 - Y) / SQUARE_SIZE);

	// if both positions are valid
	if (Board.isValid(p1) && Board.isValid(p2))
	{
	    return game.movePiece(p1, p2);
	}

	return "These are invalid coordinates.";
    }
}

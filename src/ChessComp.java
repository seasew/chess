import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class ChessComp extends JComponent
{

    private static final long serialVersionUID = 1L;

    // the size (in pixels) of the squares of the board
    public static final double SQUARE_SIZE = 100;

    // the pixel coordinates of the top left corner of the chessboard
    public static final double X = 200;
    public static final double Y = 200;

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
	g2.drawString("Welcome to Chess! Click on a " + game.getCurChessColor()
		+ " piece to move it. Then, click on the desired location.", 10, 40);
	g2.drawString(game.getCurChessColor() + " to move.", 10, 70);

	// print the coordinates
	double xRanks = X - 80;
	double yRanks = Y + SQUARE_SIZE / 2;
	double xFiles = X + SQUARE_SIZE / 2;
	double yFiles = Y + 100;
	for (int i = 0; i < ChessGame.getBoardSize(); i++)
	{
	    // draw the ranks' number
	    g2.drawString((i + 1) + "", (int) xRanks, (int) yRanks);
	}

	// print the chessboard & pieces images
	double y = Y;
	for (int i = 0; i < ChessGame.getBoardSize(); i++)
	{
	    double x = X;
	    for (int j = 0; j < ChessGame.getBoardSize(); j++)
	    {
		Position cur = new Position(i, j);
		// get the color and piece
		Rectangle2D.Double square = new Rectangle2D.Double(x, y, SQUARE_SIZE, SQUARE_SIZE);

		if (game.colorAtPos(cur) == ChessColor.BLACK)
		{
		    g2.setColor(Color.black);
		} else
		{
		    g2.setColor(Color.WHITE);
		}

		g2.fill(square);
		g2.draw(square);
		g2.drawString(game.pieceAtPos(cur).getID(), (int) x + 20, (int) y + 40);

		// update x value
		x += SQUARE_SIZE;
	    }

	    y += SQUARE_SIZE;
	}
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
     * @return a description of the actions taken, or why the action could not
     *         be done
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
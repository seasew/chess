import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class ChessComp extends JComponent
{

    private static final long serialVersionUID = 1L;

    // the size (in pixels) of the squares of the board
    public static final double SQUARE_SIZE = 80;

    // the pixel coordinates of the top left corner of the chessboard
    public static final double X = 50;
    public static final double Y = 100;

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
	double xRanks = X / 2;
	double yRanks = Y + SQUARE_SIZE / 2;
	double xFiles = X + SQUARE_SIZE / 2;
	double yFiles = Y + SQUARE_SIZE * ChessGame.getBoardSize() + 30;
	for (int a = 0; a < ChessGame.getBoardSize(); a++)
	{
	    // draw the ranks' number
	    g2.drawString((new Position(a, 0)).iToString(), (int) xRanks, (int) yRanks);
	    yRanks += SQUARE_SIZE;
	    // draw files letter
	    g2.drawString((new Position(0, a)).jToString(), (int) xFiles, (int) yFiles);
	    xFiles += SQUARE_SIZE;
	}

	// print the chessboard & pieces images
	double y = Y;
	for (int i = 0; i < ChessGame.getBoardSize(); i++)
	{
	    double x = X;
	    for (int j = 0; j < ChessGame.getBoardSize(); j++)
	    {
		Position cur = new Position(i, j);
		ChessColor color = game.colorAtPos(cur);

		// get the color and piece
		Rectangle2D.Double square = new Rectangle2D.Double(x, y, SQUARE_SIZE, SQUARE_SIZE);

		g2.setColor(getGraphicsColor(color));

		g2.fill(square);
		g2.draw(square);
		Piece curPiece = game.pieceAtPos(cur);
		if (curPiece != null)
		{
		    g2.setColor(swapGraphicsColor(color));
		    g2.drawString(curPiece.toString(), (int) x + 20, (int) y + 40);
		}

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
	Position p1 = new Position((int) ((pixelX1 - X) / SQUARE_SIZE), (int) ((pixelY1 - Y) / SQUARE_SIZE));
	Position p2 = new Position((int) ((pixelX2 - X) / SQUARE_SIZE), (int) ((pixelY2 - Y) / SQUARE_SIZE));

	// if both positions are valid
	if (Board.isValid(p1) && Board.isValid(p2))
	{
	    return game.movePiece(p1, p2);
	}

	return "These are invalid coordinates.";
    }

    private static Color swapGraphicsColor(ChessColor c)
    {
	if (c == ChessColor.WHITE)
	{
	    return Color.BLACK;

	}
	return Color.WHITE;
    }

    private static Color getGraphicsColor(ChessColor c)
    {
	if (c == ChessColor.WHITE)
	{
	    return Color.WHITE;
	}
	return Color.BLACK;
    }
}

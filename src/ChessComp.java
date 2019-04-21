import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class ChessComp extends JComponent
{

	private static final long serialVersionUID = 1L;

	// the size (in pixels) of the squares of the board
	public double squareSize;

	// the pixel coordinates of the top left corner of the chessboard
	public static final double X = 50;
	public static final double Y = 100;

	public static final double DEFAULT_SIZE = 40;

	private ChessGame game;

	private Ellipse2D.Double click2;

	/**
	 * Constructs a ChessComp with a default ChessGame.<br>
	 * This displays the chessboard and the corresponding piece images, as well
	 * as a title and descriptions of the moves.
	 */
	public ChessComp(double frameWidth, double frameHeight)
	{
		game = new ChessGame();

		click2 = new Ellipse2D.Double();

		this.resizeBoard(frameWidth, frameHeight);

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
		double yRanks = Y + squareSize / 2;
		double xFiles = X + squareSize / 2;
		double yFiles = Y + squareSize * ChessGame.getBoardSize() + 30;
		for (int a = 0; a < ChessGame.getBoardSize(); a++)
		{
			// draw the ranks' number
			g2.drawString((new Position(a, 0)).iToString(), (int) xRanks, (int) yRanks);
			yRanks += squareSize;
			// draw files letter
			g2.drawString((new Position(0, a)).jToString(), (int) xFiles, (int) yFiles);
			xFiles += squareSize;
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
				Rectangle2D.Double square = new Rectangle2D.Double(x, y, squareSize, squareSize);

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
				x += squareSize;
			}

			y += squareSize;
		}

		Color orig = g2.getColor();
		g2.setColor(Color.pink);
		g2.draw(click2);
		g2.setColor(orig);
	}

	/**
	 * Moves the piece at pixelX to pixelY if the move is valid based on the
	 * requirements from ChessGame.<br>
	 * Returns a description of what occured.
	 * 
	 * @param pixelX1
	 *                the amount of pixels in the x direction of the starting
	 *                position
	 * @param pixelY1
	 *                the amount of pixels in the y directions of the starting
	 *                position
	 * @param pixelX2
	 *                the amount of pixels in the x direction of the ending position
	 * @param pixelY2
	 *                the amount of pixels in the y direction of the ending position
	 * @return a description of the actions taken, or why the action could not
	 *         be done
	 */
	public String movePiece(int pixelX1, int pixelY1, int pixelX2, int pixelY2)
	{
		// converts the pixels to i and j (row and col)
		Position p1 = new Position((int) ((pixelX1 - X) / squareSize), (int) ((pixelY1 - Y) / squareSize));
		Position p2 = new Position((int) ((pixelX2 - X) / squareSize), (int) ((pixelY2 - Y) / squareSize));

		click2 = new Ellipse2D.Double(pixelX2, pixelY2, 20, 20);
		repaint();

		System.out.println(p1);
		System.out.println(p2);
		// if both positions are valid
		if (Board.isValid(p1) && Board.isValid(p2))
		{
			return game.movePiece(p1, p2);
		}

		return "These are invalid coordinates.";
	}

	/**
	 * Resizes the board based on the frame width and height.<br>
	 * If the frame is too small for a board with square size 50 pixels, it uses the
	 * DEFAULTSIZE value.
	 * 
	 * @param frameWidth
	 *                    the width of the frame
	 * @param frameHeight
	 *                    the height of the frame
	 */
	public void resizeBoard(double frameWidth, double frameHeight)
	{
		final double BUFFER = 120;

		// if frame is too small
		if (frameWidth - (X + BUFFER) < DEFAULT_SIZE * ChessGame.getBoardSize()
				|| frameHeight - (Y + BUFFER) < DEFAULT_SIZE * ChessGame.getBoardSize())
		{
			// default squareSize
			squareSize = DEFAULT_SIZE;
		} else
		{
			this.squareSize = Math.min((frameWidth - X - BUFFER) / ChessGame.getBoardSize(),
					(frameHeight - Y - BUFFER) / ChessGame.getBoardSize());
		}
		this.repaint();
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

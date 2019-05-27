import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ChessComp extends JComponent
{

	private static final long serialVersionUID = 1L;

	// the size (in pixels) of the squares of the board
	public double squareSize;

	// the pixel coordinates of the top left corner of the chessboard
	public static final double X = 50;
	public static final double Y = 100;

	// the default square size
	public static final double DEFAULT_SIZE = 40;

	private ChessGame game;

	private Ellipse2D.Double click1;
	private Ellipse2D.Double click2;

	/**
	 * Constructs a ChessComp with a default ChessGame.<br>
	 * This displays the chessboard and the corresponding piece images, as well
	 * as a title and descriptions of the moves.
	 */
	public ChessComp(double frameWidth, double frameHeight)
	{
		// initialize the ChessGame
		game = new ChessGame();

		click1 = new Ellipse2D.Double();
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

				// draw square
				Rectangle2D.Double square = new Rectangle2D.Double(x, y, squareSize, squareSize);

				g2.setColor(getGraphicsColor(color));

				g2.fill(square);
				g2.draw(square);

				// swap color for the next square
				color = swapChessColor(color);

				// draw piece image
				Piece curPiece = game.pieceAtPos(cur);
				if (curPiece != null)
				{
					String filePath = curPiece.getFilePath();

					BufferedImage inputImg = null;
					try
					{
						inputImg = ImageIO.read(new File(filePath));
					} catch (IOException e)
					{
						throw new RuntimeException(e);
					}

					// output image with desired size
					Image newImg = inputImg.getScaledInstance((int) squareSize, (int) squareSize, Image.SCALE_DEFAULT);
					// draw inputImg with outputImg's graphics
					g2.drawImage(newImg, (int) x, (int) y, null);
				}

				// update x value
				x += squareSize;
			}

			y += squareSize;
		}

		Color orig = g2.getColor();
		g2.setColor(Color.pink);
		g2.draw(click1);
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
		Position p1 = new Position((int) ((pixelY1 - Y) / squareSize), (int) ((pixelX1 - X) / squareSize));
		Position p2 = new Position((int) ((pixelY2 - Y) / squareSize), (int) ((pixelX2 - X) / squareSize));

		click1 = new Ellipse2D.Double(pixelX1, pixelY1, 10, 10);
		click2 = new Ellipse2D.Double(pixelX2, pixelY2, 10, 10);
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
	 * Updates the images' size.<br>
	 * If the new frame size requires a board with square size smaller than
	 * DEFAULT_SIZE, the square size is automatically set to DEFAULT_SIZE.
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

	private static ChessColor swapChessColor(ChessColor c)
	{
		if (c == ChessColor.WHITE)
		{
			return ChessColor.BLACK;

		}
		return ChessColor.WHITE;
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

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Piece
{

	public static final String ABBREV = "X";

	public static final int INVALID_MOVE = -1;
	public static final int EMPTY_MOVE = 0;
	public static final int CAPTURE_MOVE = 1;

	private ChessColor color;
	private String id;

	/**
	 * Constructs a new Piece object.
	 * 
	 * @param color
	 *              the ChessColor of the piece
	 */
	public Piece(ChessColor color, String id)
	{
		this.color = color;
		this.id = id;
	}

	/**
	 * Returns a positive integer if a Piece can move to the new position, given
	 * the Piece's specific requirements.<br>
	 * The integer can be: <br>
	 * -1: cannot move there<br>
	 * 0: can move, into an empty spot<br>
	 * 1: can move, to capture<br>
	 * For Pawns:<br>
	 * 2: can move, is a double-step <br>
	 * 3: can move, is en passant
	 * 
	 * @param board
	 *              the board that the Positions are on
	 * @param p1
	 *              the position that the desired piece is on
	 * @param p2
	 *              the position that the piece should move to
	 * @return true if the move is valid for the piece, false if not
	 */
	public abstract int canMove(Board board, Position p1, Position p2);

	/**
	 * Returns the color of the piece.
	 * 
	 * @return the color of the piece
	 */
	public ChessColor getChessColor()
	{
		return color;
	}

	/**
	 * Returns if the move from p1 to p2 is a valid move.<br>
	 * For general use by Piece subclasses. <br>
	 * <br>
	 * Conditions: <br>
	 * If p2 is occupied: the colors in p1 and p2 must be opposite colors <br>
	 * If p2 is unoccupied: returns true automatically<br>
	 * <br>
	 * This method assumes that the path from p1 to p2 is a valid path given the
	 * specific requirements for the Piece at p2. <br>
	 * 
	 * @param board
	 *              the Board the piece is on
	 * @param p1
	 *              the first position refering to the piece to move
	 * @param p2
	 *              the second position refering to the place to move to
	 * @return true if the above requirements are met, false if not
	 */
	protected int isValidMove(Board board, Position p1, Position p2)
	{
		// it can capture when...
		// p1 is occupied and p2 is occupied
		// p2 has a piece with an opposite color
		// move has an empty path (except for Knight) --> this won't be included

		// if this particular move is a capture, check with
		// canCapture method
		if (!board.isEmpty(p2))
		{
			// if the colors are opposite
			if (board.pieceAtPos(p1).getChessColor() == Board.swapChessColor(board.pieceAtPos(p2).getChessColor()))
			{
				return Piece.CAPTURE_MOVE;
			}
		}
		// if this move is into an empty spot, then return true
		else
		{
			return Piece.EMPTY_MOVE;
		}

		return Piece.INVALID_MOVE;
	}

	@Override
	public String toString()
	{
		return id;
	}

	public static Image defaultResizeImg(String filePath, int newSize)
	{
		BufferedImage inputImg = null;
		try
		{
			inputImg = ImageIO.read(new File(filePath));
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}

		// output image with desired size
		return inputImg.getScaledInstance((int) newSize, (int) newSize, Image.SCALE_DEFAULT);
	}

	public abstract void resizeImg(int newSize);

	public abstract Image getImage();

	public abstract String getFilePath();
}

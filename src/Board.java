
public class Board
{
	public static final int SIZE = 8;

	private Piece[][] pieceboard;
	private ChessColor[][] colorboard;

	/**
	 * Constructs a new Board object with set colors and empty Pieces.
	 */
	public Board()
	{
		colorboard = new ChessColor[SIZE][SIZE];
		pieceboard = new Piece[SIZE][SIZE];
		// initialize empty color board with alternating colors
		// initalize empty piece board with null elements
		for (int i = 0; i < SIZE; i++)
		{
			ChessColor c;
			// if it is an even row
			if (i % 2 == 0)
			{
				c = ChessColor.WHITE;
			} else
			{
				c = ChessColor.BLACK;
			}

			for (int j = 0; j < SIZE; j++)
			{
				colorboard[i][j] = c;
				c = swapChessColor(c);

				pieceboard[i][j] = null;
			}
		}

	}

	/**
	 * Puts a piece on the board (not moving it) --> used at the beginning of
	 * the game. <br>
	 * Preconditions: the new position must be on the board <br>
	 * Returns true if the piece was successfully placed, false otherwise.
	 * 
	 * @param piece
	 *              Piece object to put on board
	 * @param pos
	 *              New position for the Piece
	 * @return true if operation was successful, false if not
	 */
	public boolean putPiece(Piece piece, Position pos)
	{
		// if the position is valid
		if (isValid(pos))
		{
			// assign the piece at this position
			pieceboard[pos.getI()][pos.getJ()] = piece;
			return true;
		}

		// otherwise
		return false;
	}

	/**
	 * Returns the Piece at the given position on the board. <br>
	 * Position must be a valid place.
	 * 
	 * @param pos
	 *            a valid position
	 * @return the piece that pos refers to
	 */
	public Piece pieceAtPos(Position pos)
	{
		// if pos is valid
		if (isValid(pos))
		{
			return pieceboard[pos.getI()][pos.getJ()];
		}

		return null;
	}

	/**
	 * Returns the color at the given position.
	 * 
	 * @param pos
	 *            the given position
	 * @return the color
	 */
	public ChessColor colorAtPos(Position pos)
	{
		if (isValid(pos))
		{
			return colorboard[pos.getI()][pos.getJ()];
		}

		return null;
	}

	/**
	 * Returns the Position of the given Piece.<br>
	 * It finds a matching id. If the Piece is not found, it returns (-1, -1).
	 * 
	 * @param piece
	 *              the Piece to find
	 * @return the Position of the piece
	 */
	public Position getPos(Piece piece)
	{
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				Position cur = new Position(i, j);
				if (pieceAtPos(cur) != null && (pieceAtPos(cur)).equals(piece))
				{
					return new Position(i, j);
				}
			}
		}

		return new Position(-1, -1);
	}

	/**
	 * Returns if the position is empty and in range.
	 * 
	 * @param pos
	 *            the Position object to check
	 * @return true if the position is empty and in range, false if not
	 */
	public boolean isEmpty(Position pos)
	{
		if (isValid(pos) && pieceAtPos(pos) == null)
		{
			return true;
		}
		return false;
	}

	/**
	 * Returns if the position is on the board.
	 * 
	 * @param pos
	 *            the Position to check
	 * @return true if the position is on the board, false if not
	 */
	public static boolean isValid(Position pos)
	{
		if (pos.getI() >= 0 && pos.getI() < SIZE && pos.getJ() >= 0 && pos.getJ() < SIZE)
		{
			return true;
		}
		return false;
	}

	/**
	 * Moves a piece at the first position to the second. <br>
	 * Returns a String describing the move. Only if both positions are valid
	 * and p1 has a piece.<br>
	 * 
	 * @param p1
	 *           the Position corresponding to Piece to move
	 * @param p2
	 *           the new Position of the Piece
	 */
	public boolean movePiece(Position p1, Position p2)
	{
		// check that the positions are on the board
		// check that p1 has a piece on it
		if (isValid(p1) && !isEmpty(p1))
		{
			Piece piece1 = pieceAtPos(p1);

			putPiece(piece1, p2);
			putPiece(null, p1);

			return true;

		}
		return false;
	}

	/**
	 * Swaps the given color.
	 * 
	 * @param c
	 *          the color to change
	 * @return the new color
	 */
	public static ChessColor swapChessColor(ChessColor c)
	{
		if (c == ChessColor.WHITE)
		{
			return ChessColor.BLACK;
		}
		return ChessColor.WHITE;
	}

	/**
	 * Returns the new Position if the given position was moved North by x.
	 * 
	 * @param p
	 *          the position to move
	 * @param x
	 *          the amount to move
	 * @return the new position after moving north by x
	 */
	public static Position moveNorth(Position p, int x)
	{
		// calculate the new position after moving North on board by x
		Position newPos = new Position(p.getI() - x, p.getJ());
		return newPos;
	}

	/**
	 * Returns the new Position if the given position was moved East by x.
	 * 
	 * @param p
	 *          the position to move
	 * @param x
	 *          the amount to move
	 * @return the new position after moving east by x
	 */
	public static Position moveEast(Position p, int x)
	{
		// calculate the new position after moving East on board by x
		Position newPos = new Position(p.getI(), p.getJ() + x);
		return newPos;
	}

	/**
	 * Returns the new Position if the given position was moved South by x.
	 * 
	 * @param p
	 *          the position to move
	 * @param x
	 *          the amount to move
	 * @return the new position after moving south by x
	 */
	public static Position moveSouth(Position p, int x)
	{
		// calculate the new position after moving South on board by x
		Position newPos = new Position(p.getI() + x, p.getJ());
		return newPos;
	}

	/**
	 * Returns the new Position if the given position was moved West by x.
	 * 
	 * @param p
	 *          the position to move
	 * @param x
	 *          the amount to move
	 * @return the new position after moving west by x
	 */
	public static Position moveWest(Position p, int x)
	{
		// calculate the new position after moving West on board by x
		Position newPos = new Position(p.getI(), p.getJ() - x);
		return newPos;
	}

	/**
	 * Returns the new Position if the given position was moved NE by x.
	 * 
	 * @param p
	 *          the position to move
	 * @param x
	 *          the amount to move
	 * @return the new position after moving west by x
	 */
	public static Position moveNE(Position p, int x)
	{
		Position newPos = moveEast(moveNorth(p, x), x);
		return newPos;
	}

	/**
	 * Returns the new Position if the given position was moved SE by x.
	 * 
	 * @param p
	 *          the position to move
	 * @param x
	 *          the amount to move
	 * @return the new position after moving west by x
	 */
	public static Position moveSE(Position p, int x)
	{
		Position newPos = moveEast(moveSouth(p, x), x);
		return newPos;
	}

	/**
	 * Returns the new Position if the given position was moved SW by x.
	 * 
	 * @param p
	 *          the position to move
	 * @param x
	 *          the amount to move
	 * @return the new position after moving west by x
	 */
	public static Position moveSW(Position p, int x)
	{
		Position newPos = moveWest(moveSouth(p, x), x);
		return newPos;
	}

	/**
	 * Returns the new Position if the given position was moved NW by x.
	 * 
	 * @param p
	 *          the position to move
	 * @param x
	 *          the amount to move
	 * @return the new position after moving west by x
	 */
	public static Position moveNW(Position p, int x)
	{
		Position newPos = moveWest(moveNorth(p, x), x);
		return newPos;
	}

	@Override
	public String toString()
	{
		String spacer = "  +-----+-----+-----+-----+-----+-----+-----+-----+\n";
		String out = "";

		// add the first line
		out += spacer;

		for (int i = 0; i < SIZE; i++)
		{
			// add the row label
			out += (new Position(i, 0).iToString()) + " |";
			for (int j = 0; j < SIZE; j++)
			{
				Position cur = new Position(i, j);
				// add each square
				String id = "";
				String buffer = "     ";
				if (pieceboard[i][j] != null)
				{
					id = pieceAtPos(cur).toString();
					// calculate the amount of extra space needed
					// if the id is too long, shorten it
					if (id.length() > 5)
					{
						id = id.substring(0, 5);
					}

					buffer = "";
					for (int a = id.length(); a < 5; a++)
					{
						buffer += " ";
					}
				}

				out += id + buffer + "|";
			}

			// add a line
			out += "\n" + spacer;
		}

		// add the numbers for the col
		for (int j = 0; j < SIZE; j++)
		{
			out += "     " + (new Position(0, j).jToString());
		}

		return out + "\n";
	}
}

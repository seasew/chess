
public class Board
{
    public static final int SIZE = 8;

    private Piece[][] pieceboard;
    private Color[][] colorboard;

    /**
     * Constructs a new Board object with set colors and empty Pieces.
     */
    public Board()
    {
	// initialize empty color board with alternating colors
	// initalize empty piece board with null elements
	for (int i = 0; i < SIZE; i++)
	{
	    Color c;
	    // if it is an even row
	    if (i % 2 == 0)
	    {
		c = Color.WHITE;
	    } else
	    {
		c = Color.BLACK;
	    }

	    for (int j = 0; j < SIZE; j++)
	    {
		colorboard[i][j] = c;
		c = swapColor(c);

		pieceboard[i][j] = null;
	    }
	}

    }

    /**
     * Puts a piece on the board (not moving it) --> used at the beginning of
     * the game. <br>
     * Preconditions: the new position must be on the board and empty <br>
     * Returns true if the piece was successfully placed, false otherwise.
     * 
     * @param piece
     *            Piece object to put on board
     * @param pos
     *            New position for the Piece
     * @return true if operation was successful, false if not
     */
    public boolean putPiece(Piece piece, Position pos)
    {
	// if the position is empty
	if (isEmpty(pos))
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
    public boolean isValid(Position pos)
    {
	if (pos.getI() > 0 && pos.getI() < SIZE && pos.getJ() > 0 && pos.getJ() < SIZE)
	{
	    return true;
	}
	return false;
    }

    /**
     * Moves a piece at the first position to the second. <br>
     * Only if both positions are valid and p1 has a piece.
     * 
     * @param p1
     *            the Position corresponding to Piece to move
     * @param p2
     *            the new Position of the Piece
     */
    public void movePiece(Position p1, Position p2)
    {
	// check that the positions are on the board
	// check that p1 has a piece on it
	if (isValid(p1) && !isEmpty(p1))
	{
	    // if the move is valid (according to the piece at p1)
	    if ((pieceAtPos(p1)).canMove(this, p1, p2))
	    {
		// moves the piece at p1 to p2
		putPiece(pieceAtPos(p1), p2);
		putPiece(null, p1);
	    }

	}
    }

    /**
     * Swaps the given color.
     * 
     * @param c
     *            the color to change
     * @return the new color
     */
    public Color swapColor(Color c)
    {
	if (c == Color.WHITE)
	{
	    return Color.BLACK;
	}
	return Color.WHITE;
    }

    /**
     * Returns the new Position if the given position was moved North by x.
     * 
     * @param p
     *            the position to move
     * @param x
     *            the amount to move
     * @return the new position after moving north by x
     */
    public Position moveNorth(Position p, int x)
    {
	// calculate the new position after moving North on board by x
	Position newPos = new Position(p.getI() - x, p.getJ());
	return newPos;
    }

    /**
     * Returns the new Position if the given position was moved East by x.
     * 
     * @param p
     *            the position to move
     * @param x
     *            the amount to move
     * @return the new position after moving east by x
     */
    public Position moveEast(Position p, int x)
    {
	// calculate the new position after moving East on board by x
	Position newPos = new Position(p.getI(), p.getJ() + x);
	return newPos;
    }

    /**
     * Returns the new Position if the given position was moved South by x.
     * 
     * @param p
     *            the position to move
     * @param x
     *            the amount to move
     * @return the new position after moving south by x
     */
    public Position moveSouth(Position p, int x)
    {
	// calculate the new position after moving South on board by x
	Position newPos = new Position(p.getI() + x, p.getJ());
	return newPos;
    }

    /**
     * Returns the new Position if the given position was moved West by x.
     * 
     * @param p
     *            the position to move
     * @param x
     *            the amount to move
     * @return the new position after moving west by x
     */
    public Position moveWest(Position p, int x)
    {
	// calculate the new position after moving West on board by x
	Position newPos = new Position(p.getI(), p.getJ() - x);
	return newPos;
    }
}

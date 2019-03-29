
public class Board
{
    public static final int SIZE = 8;

    private Piece[][] board;

    /**
     * Constructs a new Board object with set colors and empty Pieces.
     */
    public Board()
    {
	// initialize empty board with Piece objects
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
		board[i][j] = new Piece(c, true);
		c = swapColor(c);
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
	    board[pos.getI()][pos.getJ()] = piece;
	    return true;
	}

	// otherwise
	return false;
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
	if (pos.getI() > 0 && pos.getI() < SIZE && pos.getJ() > 0 && pos.getJ() < SIZE
		&& board[pos.getI()][pos.getJ()].isEmpty())
	{
	    return true;
	}
	return false;
    }

    /**
     * Moves
     * 
     * @param p1
     * @param p2
     */
    public void movePiece(Position p1, Position p2)
    {
	// check that the positions are on the board

	// if the move is valid (according to the piece at p1)

	// moves the piece at p1 to p2
    }

    public Color swapColor(Color c)
    {
	if (c == Color.WHITE)
	{
	    return Color.BLACK;
	}
	return Color.WHITE;
    }

    public boolean moveNorth(Position p, int x)
    {
	// calculate the new position after moving North on board by x
	Position newPos = new Position(p.getI() - 1, p.getJ());
	return isEmpty(newPos);
    }

    public boolean moveEast(Position p, int x)
    {
	// calculate the new position after moving East on board by x
	Position newPos = new Position(p.getI(), p.getJ() + 1);
	return isEmpty(newPos);
    }

    public boolean moveSouth(Position p, int x)
    {
	// calculate the new position after moving South on board by x
	Position newPos = new Position(p.getI() + 1, p.getJ());
	return isEmpty(newPos);
    }

    public boolean moveWest(Position p, int x)
    {
	// calculate the new position after moving West on board by x
	Position newPos = new Position(p.getI(), p.getJ() - 1);
	return isEmpty(newPos);
    }
}

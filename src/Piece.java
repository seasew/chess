
public abstract class Piece
{

    private Color color;
    private String id;

    /**
     * Constructs a new Piece object.
     * 
     * @param color
     *            the Color of the piece
     */
    public Piece(Color color, String id)
    {
	this.color = color;
	this.id = id;
    }

    /**
     * Returns whether or not a Piece can move to the new position, given the
     * Piece's specific requirements.
     * 
     * @param board
     *            the board that the Positions are on
     * @param p1
     *            the position that the desired piece is on
     * @param p2
     *            the position that the piece should move to
     * @return true if the move is valid for the piece, false if not
     */
    public abstract boolean canMove(Board board, Position p1, Position p2);

    /**
     * Returns the color of the piece.
     * 
     * @return the color of the piece
     */
    public Color getColor()
    {
	return color;
    }

    public String getID()
    {
	return id;
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
     *            the Board the piece is on
     * @param p1
     *            the first position refering to the piece to move
     * @param p2
     *            the second position refering to the place to move to
     * @return true if the above requirements are met, false if not
     */
    protected boolean isValidMove(Board board, Position p1, Position p2)
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
	    if (board.pieceAtPos(p1).getColor() == board.swapColor(board.pieceAtPos(p2).getColor()))
	    {
		return true;
	    }
	}
	// if this move is into an empty spot, then return true
	else
	{
	    return true;
	}

	return false;
    }

    @Override
    public String toString()
    {
	return id;
    }
}

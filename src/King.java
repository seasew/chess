
public class King extends Piece
{

    /**
     * Constructs a new King. <br>
     * Moving Requirements: It can only move north, east, south, and west by 1
     * square.
     * 
     * @param color
     *            the color of the king
     */
    public King(Color color)
    {
	super(color);
	// TODO Auto-generated constructor stub
    }

    public boolean canMove(Board board, Position p1, Position p2)
    {
	// check that p2 is empty, p1 has a piece on it and both positions are
	// empty
	if (board.isValid(p1) && board.isEmpty(p2) && !board.isEmpty(p1))
	{
	    // king can only move N1, E1, S1, or W1
	    // if p2 is any of these, then this is a valid move
	    if (p2.equals(board.moveNorth(p1, 1)) || p2.equals(board.moveEast(p1, 1))
		    || p2.equals(board.moveSouth(p1, 1)) || p2.equals(board.moveWest(p1, 1)))
	    {
		return true;
	    }
	}

	return false;
    }

}

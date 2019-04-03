
public class King extends Piece
{

    public static final String ABBREV = "K";
    public static final String ID = "King";

    /**
     * Constructs a new King. <br>
     * Moving Requirements: It can only move north, east, south, and west by 1
     * square.
     * 
     * @param color
     *            the color of the king
     */
    public King(ChessColor color, String id)
    {
	super(color, id);
	// TODO Auto-generated constructor stub
    }

    public boolean canMove(Board board, Position p1, Position p2)
    {
	// for moving to an empty spot or an occupied spot
	// check that p1 has a piece on it & both positions are valid
	if (Board.isValid(p1) && Board.isValid(p2) && !board.isEmpty(p1))
	{
	    // king can only move N1, E1, S1, or W1
	    // if p2 is any of these, then this is a valid move
	    if (p2.equals(board.moveNorth(p1, 1)) || p2.equals(board.moveEast(p1, 1))
		    || p2.equals(board.moveSouth(p1, 1)) || p2.equals(board.moveWest(p1, 1)))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }
	}

	return false;
    }

}

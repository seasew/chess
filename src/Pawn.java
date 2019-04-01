
public class Pawn extends Piece
{
    private boolean isFirst;

    /**
     * Constructs a Pawn. <br>
     * Moving Requirements: <br>
     * for white pawns --> 1 move North; capture NE, NW <br>
     * for black pawns --> 1 move South; capture SE, SW<br>
     * <br>
     * The pawn also records whether or not the pawn has done the first move.
     * The value starts at true.<br>
     * If it still is on the first move, the pawn can move two squares forward.
     * 
     * @param color
     *            the color of the pawn
     */
    public Pawn(Color color, String id)
    {
	super(color, id);
	isFirst = true;
    }

    public boolean canMove(Board board, Position p1, Position p2)
    {
	// this part is for moving into an empty spot
	if (board.isValid(p1) && board.isEmpty(p2) && !board.isEmpty(p1))
	{
	    // if it is a white pawn and p2 equals one move north
	    if ((getColor() == Color.WHITE) && p2.equals(board.moveNorth(p1, 1)))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }
	    // if it is a black pawn and p2 equals one move north
	    if ((getColor() == Color.BLACK) && p2.equals(board.moveSouth(p1, 1)))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }
	}

	// this part is for killing an opponent in a diagonal spot
	if (board.isValid(p1) && !board.isEmpty(p2) && !board.isEmpty(p1))
	{
	    // if it is a white pawn and p2 equals 1 move NE or 1 move NW
	    if ((getColor() == Color.WHITE) && (p2.equals(board.moveNorth(board.moveEast(p1, 1), 1))
		    || p2.equals(board.moveNorth(board.moveWest(p1, 1), 1))))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }

	    // if it is a black pawn and p2 equals 1 move SE or 1 move SW
	    if ((getColor() == Color.BLACK) && (p2.equals(board.moveSouth(board.moveEast(p1, 1), 1))
		    || p2.equals(board.moveSouth(board.moveWest(p1, 1), 1))))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }
	}

	return false;
    }

    /**
     * Changes the value of isFirst.
     */
    public void toggleFirst()
    {
	isFirst = !isFirst;
    }
}

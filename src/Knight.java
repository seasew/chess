
public class Knight extends Piece
{

    public static final String ID = "Knight";

    /**
     * Constructs a Knight.<br>
     * Moving requirements: <br>
     * 2N & 1W, 1E<br>
     * 2S & 1W, 1E<br>
     * 2E & 1N, 1S<br>
     * 2W & 1N, 1S<br>
     * The knight can jump over other pieces.
     * 
     * @param color
     *            the color of the knight
     */
    public Knight(Color color, String id)
    {
	super(color, id);
	// TODO Auto-generated constructor stub
    }

    public boolean canMove(Board board, Position p1, Position p2)
    {
	// for moving to an empty spot or an occupied spot
	// check that p1 has a piece on it & both positions are valid
	if (board.isValid(p1) && board.isValid(p2) && !board.isEmpty(p1))
	{
	    Position twoMoves = board.moveNorth(p1, 2);
	    // check 1W and 1E for North
	    if (p2.equals(board.moveWest(twoMoves, 1)) || p2.equals(board.moveEast(twoMoves, 1)))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }

	    twoMoves = board.moveSouth(p1, 2);
	    // check 1W and 1E for South
	    if (p2.equals(board.moveWest(twoMoves, 1)) || p2.equals(board.moveEast(twoMoves, 1)))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }

	    twoMoves = board.moveEast(p1, 2);
	    // check 1N and 1S for East
	    if (p2.equals(board.moveNorth(twoMoves, 1)) || p2.equals(board.moveSouth(twoMoves, 1)))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }

	    twoMoves = board.moveWest(p1, 2);
	    // check 1N and 1S for West
	    if (p2.equals(board.moveNorth(twoMoves, 1)) || p2.equals(board.moveSouth(twoMoves, 1)))
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

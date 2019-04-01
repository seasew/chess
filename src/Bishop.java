
public class Bishop extends Piece
{

    /**
     * Constructs a new Bishop. <br>
     * Moving Requirements: In any diagonal direction.
     * 
     * @param color
     *            the color of the bishop
     */
    public Bishop(Color color)
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
	    // bishop can move diagonally
	    // North and East
	    // East and South
	    // South and West
	    // West and North
	    int x = 1;
	    while (x < Board.SIZE)
	    {
		Position ne = board.moveNorth(board.moveEast(p1, x), x);
		Position nw = board.moveNorth(board.moveWest(p1, x), x);
		Position se = board.moveSouth(board.moveEast(p1, x), x);
		Position sw = board.moveSouth(board.moveWest(p1, x), x);

		if (p2.equals(ne) || p2.equals(nw) || p2.equals(se) || p2.equals(sw))
		{
		    return true;
		}
	    }
	}

	return false;
    }

}


public class Bishop extends Piece
{

    /**
     * Constructs a new Bishop. <br>
     * Moving Requirements: In any diagonal direction.<br>
     * The entire path must be empty
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
	    boolean nepath = true;
	    // North and West
	    boolean nwpath = true;
	    // South and East
	    boolean sepath = true;
	    // South and West
	    boolean swpath = true;

	    int x = 1;
	    while (x < Board.SIZE)
	    {
		Position ne = board.moveNorth(board.moveEast(p1, x), x);
		Position nw = board.moveNorth(board.moveWest(p1, x), x);
		Position se = board.moveSouth(board.moveEast(p1, x), x);
		Position sw = board.moveSouth(board.moveWest(p1, x), x);

		// if the current NE position is filled
		if (!board.isEmpty(ne))
		{
		    // then this path is invalid
		    nepath = false;
		}

		// if the current NW position is filled
		if (!board.isEmpty(nw))
		{
		    // then this path is invalid
		    nwpath = false;
		}

		// if the current SE position is filled
		if (!board.isEmpty(se))
		{
		    // then this path is invalid
		    sepath = false;
		}

		// if the current SW position is filled
		if (!board.isEmpty(sw))
		{
		    // then this path is invalid
		    swpath = false;
		}

		// if the position matches a path and the selected path is empty
		if ((p2.equals(ne) && nepath) || (p2.equals(nw) && nwpath) || (p2.equals(se) && sepath)
			|| (p2.equals(sw) && swpath))
		{
		    return true;
		}
	    }
	}

	return false;
    }

}

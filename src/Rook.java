
public class Rook extends Piece
{

    /**
     * Constructs a new Rook. <br>
     * Moving Requirements: It can move up, down, left, or right<br>
     * The entire path must be empty
     * 
     * @param color
     *            the color of the rook
     */
    public Rook(Color color)
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
	    // true if the path is empty
	    boolean npath = true;
	    boolean epath = true;
	    boolean spath = true;
	    boolean wpath = true;
	    // rook can move anywhere up, down, left, right
	    // Check all values of x less than SIZE
	    int x = 1;
	    while (x < Board.SIZE)
	    {
		Position north = board.moveNorth(p1, x);
		Position east = board.moveEast(p1, x);
		Position south = board.moveSouth(p1, x);
		Position west = board.moveWest(p1, x);

		// if the current north position is filled
		if (!board.isEmpty(north))
		{
		    // then npath does not work
		    npath = false;
		}

		// if the current north position is filled
		if (!board.isEmpty(east))
		{
		    // then npath does not work
		    epath = false;
		}

		// if the current north position is filled
		if (!board.isEmpty(south))
		{
		    // then npath does not work
		    spath = false;
		}

		// if the current north position is filled
		if (!board.isEmpty(west))
		{
		    // then npath does not work
		    wpath = false;
		}

		// if the position matches and the selected path is empty
		if ((p2.equals(north) && npath) || (p2.equals(east) && epath) || (p2.equals(south) && spath)
			|| (p2.equals(west) && wpath))
		{
		    return true;
		}
	    }
	}

	return false;
    }
}

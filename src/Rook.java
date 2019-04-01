
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
    public Rook(Color color, String id)
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
		// current positions for all 4 directions
		Position north = board.moveNorth(p1, x);
		Position east = board.moveEast(p1, x);
		Position south = board.moveSouth(p1, x);
		Position west = board.moveWest(p1, x);

		// if the position matches and the selec ted path is empty
		if ((p2.equals(north) && npath) || (p2.equals(east) && epath) || (p2.equals(south) && spath)
			|| (p2.equals(west) && wpath))
		{
		    if (isValidMove(board, p1, p2))
		    {
			return true;
		    }
		}

		// if the current north position is filled
		if (!board.isEmpty(north))
		{
		    // then npath does not work for the next x value
		    npath = false;
		}

		// if the current north position is filled
		if (!board.isEmpty(east))
		{
		    // then npath does not work for the next x value
		    epath = false;
		}

		// if the current north position is filled
		if (!board.isEmpty(south))
		{
		    // then npath does not work for the next x value
		    spath = false;
		}

		// if the current north position is filled
		if (!board.isEmpty(west))
		{
		    // then npath does not work for the next x value
		    wpath = false;
		}

	    }
	}
	return false;
    }
}

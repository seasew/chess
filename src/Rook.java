
public class Rook extends Piece
{

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
	    // queen can move anywhere diagonally, up, down, left, right
	    // Check all values of x less than SIZE
	    int x = 1;
	    while (x < Board.SIZE - 1)
	    {
		if (p2.equals(board.moveNorth(p1, x)) || p2.equals(board.moveEast(p1, x))
			|| p2.equals(board.moveSouth(p1, x)) || p2.equals(board.moveWest(p1, x)))
		{
		    return true;
		}
	    }
	}

	return false;
    }
}

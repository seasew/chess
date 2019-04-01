
public class Queen extends Piece
{

    /**
     * Constructs a new Queen.<br>
     * Moving Requirements: A queen can move anywhere diagonally, up, down,
     * left, or right.
     * 
     * @param color
     *            the color of the queen
     */
    public Queen(Color color, String id)
    {
	super(color, id);
    }

    public boolean canMove(Board board, Position p1, Position p2)
    {
	// for moving to an empty spot or an occupied spot
	// check that p1 has a piece on it & both positions are valid
	if (board.isValid(p1) && board.isValid(p2) && !board.isEmpty(p1))
	{
	    Rook lateral = new Rook(getColor(), "rook used for queen");
	    Bishop diagonal = new Bishop(getColor(), "bishop used for queen");

	    // if this move is valid for a Rook or a Bishop
	    // then it is valid for a queen
	    if (lateral.canMove(board, p1, p2) || diagonal.canMove(board, p1, p2))
	    {
		return true;
	    }
	}

	return false;
    }
}

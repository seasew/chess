
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
    public Queen(Color color)
    {
	super(color);
    }

    public boolean canMove(Board board, Position p1, Position p2)
    {
	// check that p2 is empty, p1 has a piece on it and both positions are
	// empty
	if (board.isValid(p1) && board.isEmpty(p2) && !board.isEmpty(p1))
	{
	    Rook lateral = new Rook(getColor());
	    Bishop diagonal = new Bishop(getColor());

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


public abstract class Piece
{

    private Color color;

    /**
     * Constructs a new Piece object.
     * 
     * @param color
     *            the Color of the piece
     */
    public Piece(Color color)
    {
	this.color = color;
    }

    /**
     * Returns whether or not a Piece can move to the new position, given the
     * Piece's specific requirements.
     * 
     * @param board
     *            the board that the Positions are on
     * @param p1
     *            the position that the desired piece is on
     * @param p2
     *            the position that the piece should move to
     * @return true if the move is valid for the piece, false if not
     */
    public abstract boolean canMove(Board board, Position p1, Position p2);

    /**
     * Returns the color of the piece.
     * 
     * @return the color of the piece
     */
    public Color getColor()
    {
	return color;
    }

    /**
     * Returns if the move from p1 to p2 is a valid Capture.<br>
     * Both positions must be occupied, and the pieces at each position must
     * have opposite colors.<br>
     * For pieces except for the Knight, the path to p2 must be an unoccupied
     * path. However, this method will assume that the path is valid.<br>
     * <br>
     * For use by the subclasses of Piece, as they have similar implementations.
     * 
     * @param board
     *            the Board the piece is on
     * @param p1
     *            the first position refering to the piece to move
     * @param p2
     *            the second poisiton refering to the place to move to
     * @return true if the above requirements are met, false if not
     */
    private boolean canCapture(Board board, Position p1, Position p2)
    {
	// it can capture when...
	// p1 is occupied and p2 is occupied
	// p2 has a piece with an opposite color
	// move has an empty path (except for Knight) --> this won't be included
	if (!board.isEmpty(p1) && !board.isEmpty(p2)
		&& (board.pieceAtPos(p1).getColor() == board.swapColor(board.pieceAtPos(p2).getColor())))
	{
	    return true;
	}
	return false;
    }
}

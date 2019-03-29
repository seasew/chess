
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
}

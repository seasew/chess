
public class Knight extends Piece
{

    /**
     * Constructs a Knight.<br>
     * Moving requirements: <br>
     * 2N & 1W, 1E<br>
     * 2S & 1W, 1E<br>
     * 2E & 1N, 1S<br>
     * 2W & 1N, 1S<br>
     * 
     * @param color
     *            the color of the knight
     */
    public Knight(Color color)
    {
	super(color);
	// TODO Auto-generated constructor stub
    }

    public boolean canMove(Board board, Position p1, Position p2)
    {

    }
}


public class Piece
{

    private Color color;
    private boolean empty;

    public Piece(Color color, boolean empty)
    {
	this.color = color;
	this.empty = empty;
    }

    public boolean canMove(Board board, Position p1, Position p2)
    {
	return false;
    }

    public boolean isEmpty()
    {
	return empty;
    }
}

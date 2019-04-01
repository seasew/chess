
public class Tester
{

    public static void main(String[] args)
    {
	Board board = new Board();

	Position p1 = new Position(5, 5);
	Position p2 = new Position(0, 5);

	Piece piece1 = new Rook(Color.WHITE, "P1");
	board.putPiece(piece1, p1);
	System.out.println(board);

	Piece piece2 = new Pawn(Color.WHITE, "P2");
	// board.putPiece(piece2, p2);

	if (piece1.canMove(board, p1, p2))
	{
	    board.movePiece(p1, p2);
	    System.out.println(piece1 + " was able to move");
	    System.out.println(board);
	}
    }

}

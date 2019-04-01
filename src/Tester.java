
public class Tester
{

    public static void main(String[] args)
    {
	Board board = new Board();

	Position p1 = new Position(6, 6);
	Position p2 = new Position(4, 6);

	Pawn pawn = new Pawn(Color.WHITE, "WPawn");
	board.putPiece(pawn, p1);
	System.out.println(board);

	if (pawn.canMove(board, p1, p2))
	{
	    board.movePiece(p1, p2);
	    System.out.println("Pawn was able to move");
	    System.out.println(board);
	}
    }

}

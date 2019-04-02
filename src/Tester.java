import javax.swing.JFrame;

public class Tester
{

    public static void main(String[] args)
    {
	Board board = new Board();

	Position p1 = new Position(3, 4);
	Position p2 = new Position(2, 3);
	Position p3 = new Position(7, 4);

	Piece piece1 = new Queen(ChessColor.WHITE, "P1");
	board.putPiece(piece1, p1);

	Piece piece2 = new Pawn(ChessColor.BLACK, "P2");
	board.putPiece(piece2, p2);

	System.out.println(board);

	if (piece1.canMove(board, p1, p3))
	{
	    board.movePiece(p1, p3);
	    System.out.println(piece1 + " was able to move");
	    System.out.println(board);
	}

	ChessGame game = new ChessGame();

	JFrame frame = new JFrame("Chess");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1000, 1000);

	ChessComp comp = new ChessComp();
	frame.add(comp);
	frame.setVisible(true);
    }

}

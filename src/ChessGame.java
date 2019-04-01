
public class ChessGame
{

    public static final int NPIECES = 16;
    private Board board;
    private Piece[] white;
    private Piece[] black;

    public ChessGame()
    {
	board = new Board();
	white = new Piece[NPIECES];
	white[0] = new Pawn(Color.WHITE) wp1;
	black = new Piece[NPIECES];
    }
}

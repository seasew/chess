
public class ChessGame
{

    public static final int NPIECES = 16;
    private Board board;
    private Piece[] white;
    private Piece[] black;

    public ChessGame()
    {
	// initalize a default board
	board = new Board();

	white = new Piece[NPIECES];
	// initalize white pieces
	white[0] = new Pawn(Color.WHITE, "WP1");
	white[1] = new Pawn(Color.WHITE, "WP2");
	white[2] = new Pawn(Color.WHITE, "WP3");
	white[3] = new Pawn(Color.WHITE, "WP4");
	white[4] = new Pawn(Color.WHITE, "WP5");
	white[5] = new Pawn(Color.WHITE, "WP6");
	white[6] = new Pawn(Color.WHITE, "WP7");
	white[7] = new Pawn(Color.WHITE, "WP8");
	white[8] = new Rook(Color.WHITE, "WR1");
	white[9] = new Knight(Color.WHITE, "WK1");
	white[10] = new Bishop(Color.WHITE, "WB1");
	white[11] = new Queen(Color.WHITE, "WQ");
	white[12] = new King(Color.WHITE, "WK");
	white[13] = new Bishop(Color.WHITE, "WB2");
	white[14] = new Knight(Color.WHITE, "WK2");
	white[15] = new Rook(Color.WHITE, "WR2");

	black = new Piece[NPIECES];
	// initalize black pieces
	black[0] = new Pawn(Color.BLACK, "BP1");
	black[1] = new Pawn(Color.BLACK, "BP2");
	black[2] = new Pawn(Color.BLACK, "BP3");
	black[3] = new Pawn(Color.BLACK, "BP4");
	black[4] = new Pawn(Color.BLACK, "BP5");
	black[5] = new Pawn(Color.BLACK, "BP6");
	black[6] = new Pawn(Color.BLACK, "BP7");
	black[7] = new Pawn(Color.BLACK, "BP8");
	black[8] = new Rook(Color.BLACK, "BR1");
	black[9] = new Knight(Color.BLACK, "BK1");
	black[10] = new Bishop(Color.BLACK, "BB1");
	black[11] = new Queen(Color.BLACK, "BQ");
	black[12] = new King(Color.BLACK, "BK");
	black[13] = new Bishop(Color.BLACK, "BB2");
	black[14] = new Knight(Color.BLACK, "BK2");
	black[15] = new Rook(Color.BLACK, "BR2");

    }
}

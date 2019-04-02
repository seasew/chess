import java.util.ArrayList;

public class ChessGame
{

    public static final int NPIECES = 16;
    private Board board;
    private Piece[] white;
    private Piece[] black;

    private boolean check;
    private Color curColor;

    /**
     * Constructs a new ChessGame. <br>
     * The starting color is White. <br>
     * Initialize both sides with 16 pieces (8 pawns, 2 rooks, 2 knights, 2
     * bishops, 1 queen, 1 king).<br>
     * The ids for the pieces are as follows: ColorFirstletterofpieceNumber<br>
     * --> WP1 (White Pawn 1) or BQ (Black Queen) <br>
     * <br>
     * The pieces are placed in set locations (row, col)-id: <br>
     * <br>
     * (6,0)-WP1, (6,1)-WP2, (6,2)-WP3, (6,3)-WP4, (6,4)-WP5, (6,5)-WP6,
     * (6,6)-WP7, (6,7)-WP8, (7,0)-WR1, (7,1)-WK1, (7,2)-WB1, (7,3)-WQ,
     * (7,4)-WK, (7,5)-WB2, (7,6)-WK2, (7,7)-WR2<br>
     * <br>
     * (1,0)-BP1, (1,1)-BP2, (1,2)-BP3, (1,3)-BP4, (1,4)-BP5, (1,5)-BP1,
     * (1,6)-BP0, (1,7)-BP8, (0,0)-BR1, (0,1)-BK1, (0,2)-BB1, (0,3)-BQ,
     * (0,4)-BK, (0,5)-BB2, (0,6)-BK2, (0,7)-BR2
     */
    public ChessGame()
    {
	check = false;
	curColor = Color.WHITE;
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

	// put pieces on the board
	int curWRow = 6;
	int curBRow = 1;
	int curCol = 0;
	for (int i = 0; i < NPIECES; i++)
	{
	    board.putPiece(white[i], new Position(curWRow, curCol));
	    board.putPiece(black[i], new Position(curBRow, curCol));

	    curCol = (curCol + 1) % 8;

	    // when it is the second row
	    if (i >= NPIECES / 2)
	    {
		curWRow++;
		curBRow--;
	    }
	}
    }

    /**
     * Moves the piece at p1 to p2 if it meets the requirements.<br>
     * If the current color is not in check: <br>
     * - The move has to be allowed by the piece's specific requirements.<br>
     * - The move either is to an empty spot or captures a piece (cannot capture
     * king). <br>
     * - The move can be promotion, passant, or castling. <br>
     * If the current color is in check: <br>
     * - The king moves to a square that is not in check. <br>
     * - The move(must be a valid move for that specific piece) blocks or
     * captures the piece that is checking the king.<br>
     * <br>
     * Return Value: <br>
     * - moving to an empty spot<br>
     * - capturing an opponent's piece (cannot be the king)<br>
     * - game is over (checkmating the opponent)<br>
     * - draw
     * 
     * @param p1
     *            the position of the piece to move
     * @param p2
     *            the position that the piece will move to
     * @return a String describing the outcome of the move
     */
    public String movePiece(Position p1, Position p2)
    {
	String out = "ERROR: Blank String for movePiece(p1, p2)";

	Piece piece1 = board.pieceAtPos(p1);
	Piece piece2 = board.pieceAtPos(p2);

	// find the position of the current color's king
	Position kingPos = board.getPos(new King(curColor, curColor + "K"));

	// Is it a valid move?
	// First, check the default move method for the piece
	boolean valid = piece1.canMove(board, p1, p2);

	// is the move castling?

	// is the move en passant?

	// if the curColor king is being checked right now
	Piece[] checks = inCheck(kingPos, board.swapColor(curColor));
	if (checks.length > 0)
	{
	    // Option 1: The king moves to a position that is not 'checked'
	    // by moving into an empty spot
	    // or capturing a piece that is checking it
	    // if the starting position refers to the current king
	    if (p1.equals(kingPos))
	    {
		// then, see if p2 is a position that is not checked
		if (inCheck(p2, board.swapColor(curColor)).length == 0)
		{
		    // king is allowed to move there
		    board.movePiece(p1, p2);
		}
	    }

	    // Option 2: The move blocks or captures the piece that is checking
	    // the king
	}
	// If not in check...
	else
	{
	    // move the piece if the move is valid
	    if (valid)
	    {
		board.movePiece(p1, p2);
		out = piece1.getID() + " moved from " + p1 + " to " + p2;
	    }
	}

	// Promotion?
	// if the piece moved was a pawn
	if ((piece1.ID == Pawn.ID))
	{

	}

	// Is there check after this move?
	// Is there a checkmate after this move?

	// change color
	curColor = board.swapColor(curColor);

    }

    /**
     * Returns whether the given position is in check. <br>
     * If the given color's side has pieces that are attacking 'pos', return an
     * array containing all the pieces that are checking it.<br>
     * A piece is considered attacking a position if the piece can legally move
     * to that position.
     * 
     * @param pos
     *            the Position to check
     * @param opponent
     *            the Color that the position may be attacked by
     * @return an array of Pieces that are currently checking the position, or
     *         an empty array
     */
    public Piece[] inCheck(Position pos, Color opponent)
    {
	Piece[] out = new Piece[0];
	if (!Board.isValid(pos))
	{
	    return out;
	}

	// check every piece on the opponent's side that might be attacking the
	// position
	Piece[] pieces;
	if (opponent == Color.WHITE)
	{
	    pieces = white;
	} else
	{
	    pieces = black;
	}

	ArrayList<Piece> list = new ArrayList<Piece>();

	// for each piece
	for (int i = 0; i < NPIECES; i++)
	{
	    // if it can move to pos, then the position is in check
	    if (pieces[i].canMove(board, board.getPos(pieces[i]), pos))
	    {
		list.add(pieces[i]);
	    }
	}

	out = new Piece[list.size()];
	for (int i = 0; i < list.size(); i++)
	{
	    out[i] = list.get(i);
	}

	return out;
    }

    /**
     * Returns the current color/side of the game.<br>
     * After the side/color makes a move, curColor changes.<br>
     * The first curColor is White.
     * 
     * @return the current color
     */
    public Color getCurColor()
    {
	return curColor;
    }

    /**
     * Returns the board's color at the given position. <br>
     * The Position must be valid.
     * 
     * @param pos
     *            the position
     * @return the color
     */
    public Color colorAtPos(Position pos)
    {
	return board.colorAtPos(pos);
    }

    /**
     * Returns the board's piece at the given position.<br>
     * The Position must be valid.
     * 
     * @param pos
     *            the position
     * @return the color
     */
    public Piece pieceAtPos(Position pos)
    {
	return board.pieceAtPos(pos);
    }
}

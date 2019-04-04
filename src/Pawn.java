public class Pawn extends Piece
{

    public static final String ABBREV = "P";
    public static final String ID = "Pawn";
    private boolean isFirst;
    private Position prevPawnMove;

    /**
     * Constructs a Pawn. <br>
     * Moving Requirements: <br>
     * for white pawns --> 1 move North; capture NE, NW <br>
     * for black pawns --> 1 move South; capture SE, SW<br>
     * <br>
     * The pawn also records whether or not the pawn has done the first move.
     * The value starts at true.<br>
     * If it still is on the first move, the pawn can move two squares forward.
     * 
     * @param color
     *            the color of the pawn
     */
    public Pawn(ChessColor color, String id)
    {
	super(color, id);
	isFirst = true;
	prevPawnMove = null;
    }

    public boolean canMove(Board board, Position p1, Position p2)
    {
	// this part is for moving into an empty spot
	if (Board.isValid(p1) && board.isEmpty(p2) && !board.isEmpty(p1))
	{
	    // if it is a white pawn and A. p2 equals one move north
	    // or B. p2 equals 2 moves north and the move is the first one
	    if ((getChessColor() == ChessColor.WHITE)
		    && (p2.equals(board.moveNorth(p1, 1)) || (p2.equals(board.moveNorth(p1, 2)) && isFirst)))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }
	    // if it is a black pawn and A. p2 equals one move north
	    // or B. p2 equals 2 moves north and the move is the first one
	    if ((getChessColor() == ChessColor.BLACK)
		    && (p2.equals(board.moveSouth(p1, 1)) || (p2.equals(board.moveSouth(p1, 1)) && isFirst)))
	    {
		if (isValidMove(board, p1, p2))
		{
		    return true;
		}
	    }
	}
	// this part is for killing an opponent in a diagonal spot
	else if (Board.isValid(p1) && Board.isValid(p2) && !board.isEmpty(p2) && !board.isEmpty(p1))
	{
	    if (isDiagonalMove(board, p1, p2))
	    {
		return true;
	    }
	}
	// this part is for en passant
	// p2 is empty, p1 has a pawn on it
	else if (board.isEmpty(p2) && !board.isEmpty(p1) && Board.isValid(p1) && prevPawnMove != null
		&& isDiagonalMove(board, p1, p2))
	{
	    // the color of the piece that may be captured
	    ChessColor pieceColor = board.pieceAtPos(prevPawnMove).getChessColor();
	    // if piece to capture is white, then it is north of p2
	    // if piece to capture is black, then it is south of p2
	    if ((pieceColor == ChessColor.WHITE && prevPawnMove.equals(board.moveNorth(p2, 1)))
		    || (pieceColor == ChessColor.BLACK && prevPawnMove.equals(board.moveSouth(p2, 1))))
	    {
		//
	    }
	}

	return false;
    }

    private boolean isDiagonalMove(Board board, Position p1, Position p2)
    {
	// if it is a white pawn and p2 equals 1 move NE or 1 move NW
	if ((getChessColor() == ChessColor.WHITE) && (p2.equals(board.moveNorth(board.moveEast(p1, 1), 1))
		|| p2.equals(board.moveNorth(board.moveWest(p1, 1), 1))))
	{
	    if (isValidMove(board, p1, p2))
	    {
		return true;
	    }
	}

	// if it is a black pawn and p2 equals 1 move SE or 1 move SW
	if ((getChessColor() == ChessColor.BLACK) && (p2.equals(board.moveSouth(board.moveEast(p1, 1), 1))
		|| p2.equals(board.moveSouth(board.moveWest(p1, 1), 1))))
	{
	    if (isValidMove(board, p1, p2))
	    {
		return true;
	    }
	}

	return false;
    }

    /**
     * Changes the value of isFirst.
     */
    public void toggleFirst()
    {
	isFirst = !isFirst;
    }

    /**
     * Sets previous pawn move position to given postion.<br>
     * The value is null if the previous move was not a pawn's double step. <br>
     * The value is the position of the pawn after double step if the move was a
     * double step.
     * 
     * @param pos
     *            the position to set
     */
    public void setPrevPawnMove(Position pos)
    {
	prevPawnMove = pos;
    }

}

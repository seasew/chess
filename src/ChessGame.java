import java.util.ArrayList;

public class ChessGame
{

    public static final int NPIECES = 16;
    private Board board;
    private Piece[] white;
    private Piece[] black;

    // null if the prev move was not a 2-step pawn move
    // if it was a 2-pawn, record where it moved to
    private Position prevPawnMove;

    private ChessColor curChessColor;

    /**
     * Constructs a new ChessGame. <br>
     * The starting color is White. <br>
     * Initialize both sides with 16 pieces (8 pawns, 2 rooks, 2 knights, 2
     * bishops, 1 queen, 1 king).<br>
     * The ids for the pieces are as follows:
     * ChessColorFirstletterofpieceNumber<br>
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
	prevPawnMove = null;
	curChessColor = ChessColor.WHITE;
	// initalize a default board
	board = new Board();

	white = new Piece[NPIECES];
	black = new Piece[NPIECES];

	// pawns
	for (int i = 0; i < 8; i++)
	{
	    white[i] = new Pawn(ChessColor.WHITE, ChessColor.WHITE.getAbbrev() + Pawn.ABBREV + (i + 1));
	    black[i] = new Pawn(ChessColor.BLACK, ChessColor.BLACK.getAbbrev() + Pawn.ABBREV + (i + 1));
	}

	// initalize white pieces
	white[8] = new Rook(ChessColor.WHITE, ChessColor.WHITE.getAbbrev() + Rook.ABBREV + 1);
	white[9] = new Knight(ChessColor.WHITE, ChessColor.WHITE.getAbbrev() + Knight.ABBREV + 1);
	white[10] = new Bishop(ChessColor.WHITE, ChessColor.WHITE.getAbbrev() + Bishop.ABBREV + 1);
	white[11] = new Queen(ChessColor.WHITE, ChessColor.WHITE.getAbbrev() + Queen.ABBREV);
	white[12] = new King(ChessColor.WHITE, ChessColor.WHITE.getAbbrev() + King.ABBREV);
	white[13] = new Bishop(ChessColor.WHITE, ChessColor.WHITE.getAbbrev() + Bishop.ABBREV + 2);
	white[14] = new Knight(ChessColor.WHITE, ChessColor.WHITE.getAbbrev() + Knight.ABBREV + 2);
	white[15] = new Rook(ChessColor.WHITE, ChessColor.WHITE.getAbbrev() + Rook.ABBREV + 2);

	// initalize black pieces
	black[8] = new Rook(ChessColor.BLACK, ChessColor.BLACK.getAbbrev() + Rook.ABBREV + 1);
	black[9] = new Knight(ChessColor.BLACK, ChessColor.BLACK.getAbbrev() + Knight.ABBREV + 1);
	black[10] = new Bishop(ChessColor.BLACK, ChessColor.BLACK.getAbbrev() + Bishop.ABBREV + 1);
	black[11] = new Queen(ChessColor.BLACK, ChessColor.BLACK.getAbbrev() + Queen.ABBREV);
	black[12] = new King(ChessColor.BLACK, ChessColor.BLACK.getAbbrev() + King.ABBREV);
	black[13] = new Bishop(ChessColor.BLACK, ChessColor.BLACK.getAbbrev() + Bishop.ABBREV + 2);
	black[14] = new Knight(ChessColor.BLACK, ChessColor.BLACK.getAbbrev() + Knight.ABBREV + 2);
	black[15] = new Rook(ChessColor.BLACK, ChessColor.BLACK.getAbbrev() + Rook.ABBREV + 2);

	// put pieces on the board
	int curWRow = 6;
	int curBRow = 1;
	int curCol = 0;
	for (int i = 0; i < NPIECES; i++)
	{
	    // when it is the second row
	    if (i == NPIECES / 2)
	    {
		curWRow++;
		curBRow--;
	    }
	    board.putPiece(white[i], new Position(curWRow, curCol));

	    board.putPiece(black[i], new Position(curBRow, curCol));

	    curCol = (curCol + 1) % 8;

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
	String out = "Error";

	Piece piece1 = board.pieceAtPos(p1);
	Piece piece2 = board.pieceAtPos(p2);

	String errorMsg = "Error: " + p1 + " to " + p2 + " is an invalid move for " + piece1 + ".";

	// basic checks
	if (board.isEmpty(p1))
	{
	    return "Error: Please select a square with a piece on it.";
	}

	// find the position of the current color's king
	Position kingPos = board.getPos(new King(curChessColor, curChessColor.getAbbrev() + "K"));
	// if the curChessColor king is being checked right now
	Piece[] checks = inCheck(kingPos, Board.swapChessColor(curChessColor));

	// Is it a valid move?
	// First, check the default move method for the piece
	int valid = piece1.canMove(board, p1, p2);

	// The king is in check, so this move must be a move to block, kill, or
	// move out of the way from the Check.
	if (checks.length > 0)
	{
	    // for this, all moves must be valid because there is no castling
	    // when the king is in check
	    if (valid > 0)
	    {
		// Option 1: The king moves to a position that is not 'checked'
		// -King moves into unchecked spot: the move must still be valid
		// ???: is the king being moved? is it a valid king move?
		if (p1.equals(kingPos))
		{
		    // then, see if p2 is a position that is not checked
		    if (inCheck(p2, Board.swapChessColor(curChessColor)).length == 0)
		    {
			// move piece with status update
			out = board.movePiece(p1, p2);
		    } else
		    {
			// EXIT
			return "Error: " + p2 + " is currently attacked. " + piece1 + " cannot move there.";
		    }
		}
		// Option 2: The move blocks or captures the piece that is
		// checking the king
		// This means there can only be one piece checking the king
		else
		{
		    // if this move captures checking piece, if there is only
		    // one piece checking, and if the move done is valid
		    if (checks.length == 1 && board.getPos(checks[0]).equals(p2))
		    {
			// move piece with status update
			out = board.movePiece(p1, p2);
		    } else
		    {
			return "Error: The move from " + p1 + " to " + p2 + " does not block or capture the check.";
		    }

		}

	    }
	    // if the move is invalid
	    else
	    {
		// EXIT
		return errorMsg;
	    }
	}

	// If not in check...
	else
	{
	    Position prevPawnMove = null;

	    // castling is a 2 square movement for the king
	    if (valid < 0)
	    {
		// the piece being moved is the king
		if ((p1.equals(Board.moveEast(p2, 2)) || p1.equals(Board.moveWest(p2, 2))) && piece1.ID.equals(King.ID)
			&& ((King) piece1).isFirst())
		{
		    Position rookPos;
		    Position addPos;
		    // if the king is moving to the left
		    if (p2.getJ() < Board.SIZE / 2)
		    {
			addPos = new Position(0, -1);
			if (curChessColor == ChessColor.WHITE)
			{
			    rookPos = new Position(Board.SIZE - 1, 0);
			} else
			{
			    rookPos = new Position(0, 0);
			}
		    }
		    // if the king is moving to the right
		    else
		    {
			addPos = new Position(0, 1);
			if (curChessColor == ChessColor.WHITE)
			{
			    rookPos = new Position(Board.SIZE - 1, Board.SIZE - 1);
			} else
			{
			    rookPos = new Position(0, Board.SIZE - 1);
			}
		    }

		    // check if the piece at rookPos is a Rook
		    // also check that it is the first move of Rook
		    if (board.pieceAtPos(rookPos).ID.equals(Rook.ID) && ((Rook) board.pieceAtPos(rookPos)).isFirst())
		    {
			// Check the two squares between
			boolean canCastle = true;
			Position cur = p1;
			for (int i = 0; i < 2; i++)
			{
			    // check the next square
			    cur = Position.addPos(cur, addPos);

			    // if both conditions are not met
			    if (!(board.isEmpty(cur) && inCheck(cur, Board.swapChessColor(curChessColor)).length == 0))
			    {
				canCastle = false;
				break;
			    }
			}

			if (canCastle)
			{
			    // move the rook to 1 right or 1 left of the King
			    board.movePiece(rookPos, Position.addPos(addPos, p1));
			    // move the king to 2 addPos of the cur king
			    board.movePiece(p1, Position.addPos(Position.addPos(addPos, addPos), p1));
			} else
			{
			    return errorMsg;
			}

		    } else
		    {
			return errorMsg;
		    }

		} else
		{
		    return errorMsg;
		}
	    }
	    // move the piece if it is valid
	    else if (valid > 0)
	    {
		out = board.movePiece(p1, p2);

		// if the move was a pawn's double-step
		if (valid == 2)
		{
		    // update ppm
		    prevPawnMove = p2;
		    ((Pawn) piece1).toggleFirst();
		}

		// if the move was en passant
		if (valid == 3)
		{
		    // set the captured piece to null
		    board.putPiece(null, Pawn.getEnPassant(board, p2));
		}
	    }

	    // update prevPawnMove if needed
	    for (int i = 0; i < Board.SIZE; i++)
	    {
		((Pawn) white[i]).setPrevPawnMove(prevPawnMove);
		((Pawn) white[i]).setPrevPawnMove(prevPawnMove);
	    }
	}

	// Promotion?
	// if the piece moved was a pawn
	if (piece1.ID == Pawn.ID)
	{

	}

	// Is the other color in check?
	// If so, can the other color play any moves to get out of check?

	// change color
	curChessColor = Board.swapChessColor(curChessColor);

	return out;
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
     *            the ChessColor that the position may be attacked by
     * @return an array of Pieces that are currently checking the position, or
     *         an empty array
     */
    public Piece[] inCheck(Position pos, ChessColor opponent)
    {
	Piece[] out = new Piece[0];
	if (!Board.isValid(pos))
	{
	    return out;
	}

	// check every piece on the opponent's side that might be attacking the
	// position
	Piece[] pieces;
	if (opponent == ChessColor.WHITE)
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
	    if (pieces[i].canMove(board, board.getPos(pieces[i]), pos) > 0)
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
     * After the side/color makes a move, curChessColor changes.<br>
     * The first curChessColor is White.
     * 
     * @return the current color
     */
    public ChessColor getCurChessColor()
    {
	return curChessColor;
    }

    /**
     * Returns the board's color at the given position. <br>
     * The Position must be valid.
     * 
     * @param pos
     *            the position
     * @return the color
     */
    public ChessColor colorAtPos(Position pos)
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

    /**
     * Returns the board size from Board.<br>
     * This is so that ChessComp will not have depend on Board.
     * 
     * @return the size of Board
     */
    public static int getBoardSize()
    {
	return Board.SIZE;
    }

    @Override
    public String toString()
    {
	String out = "Current Color: " + curChessColor + "\n";
	return out + board;
    }
}

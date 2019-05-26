public class Pawn extends Piece
{

	public static final String ABBREV = "P";

	public static final String WPAWN_URL = "http://github.com/pinkbluesky/chess/blob/master/ChessIcons/whitePawn.png";
	public static final String BPAWN_URL = "http://github.com/pinkbluesky/chess/blob/master/ChessIcons/blackPawn.png";

	public static final int DOUBLE_STEP_MOVE = 2;
	public static final int EN_PASSANT_MOVE = 3;

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
	 *              the color of the pawn
	 */
	public Pawn(ChessColor color, String id)
	{
		super(color, id);
		isFirst = true;
		prevPawnMove = null;
	}

	public int canMove(Board board, Position p1, Position p2)
	{
		int isValidMove = isValidMove(board, p1, p2);
		// this part is for moving into an empty spot
		if (Board.isValid(p1) && board.isEmpty(p2) && !board.isEmpty(p1))
		{
			// if it is a white pawn and p2 equals one move north
			// or if it is a black pawn and p2 is 1South
			if ((getChessColor() == ChessColor.WHITE) && (p2.equals(Board.moveNorth(p1, 1)))
					|| (getChessColor() == ChessColor.BLACK) && (p2.equals(Board.moveSouth(p1, 1))))
			{
				return Piece.EMPTY_MOVE;
			}
			// if it is a white pawn and the move is 2 north
			// if it is a black pawn and the move is 2 south
			if (isFirst && (getChessColor() == ChessColor.WHITE && p2.equals(Board.moveNorth(p1, 2)))
					|| (getChessColor() == ChessColor.BLACK && p2.equals(Board.moveSouth(p1, 2))))
			{
				return Pawn.DOUBLE_STEP_MOVE;
			}

			// this part is for en passant
			// p2 is empty, p1 has a pawn on it
			if (prevPawnMove != null && isDiagonalMove(board, p1, p2))
			{
				// the color of the piece that may be captured
				ChessColor pieceColor = board.pieceAtPos(prevPawnMove).getChessColor();
				// if piece to capture is white, then it is north of p2
				// if piece to capture is black, then it is south of p2
				if ((pieceColor == ChessColor.WHITE && prevPawnMove.equals(Board.moveNorth(p2, 1)))
						|| (pieceColor == ChessColor.BLACK && prevPawnMove.equals(Board.moveSouth(p2, 1))))
				{
					// then, that means this move is en passant
					return Pawn.EN_PASSANT_MOVE;
				}
			}
		}
		// this part is for killing an opponent in a diagonal spot
		else if (Board.isValid(p1) && Board.isValid(p2) && !board.isEmpty(p2) && !board.isEmpty(p1))
		{
			if (isDiagonalMove(board, p1, p2))
			{
				if (isValidMove > 0)
				{
					return isValidMove;
				}
			}
		}

		return Piece.INVALID_MOVE;
	}

	/**
	 * Private method to check if the move given is a diagonal move.<br>
	 * p2 can be empty or occupied but p1 must be occupied.
	 * 
	 * @param board
	 *              the board
	 * @param p1
	 *              the starting position of the move
	 * @param p2
	 *              the ending position of the move
	 * @return true if it is a diagonal move, false if not
	 */
	private boolean isDiagonalMove(Board board, Position p1, Position p2)
	{
		// if it is a white pawn and p2 equals 1 move NE or 1 move NW
		if ((getChessColor() == ChessColor.WHITE) && (p2.equals(Board.moveNE(p1, 1)) || p2.equals(Board.moveNW(p1, 1))))
		{
			if (isValidMove(board, p1, p2) >= 0)
			{
				return true;
			}
		}

		// if it is a black pawn and p2 equals 1 move SE or 1 move SW
		if ((getChessColor() == ChessColor.BLACK) && (p2.equals(Board.moveSE(p1, 1)) || p2.equals(Board.moveSW(p1, 1))))
		{
			if (isValidMove(board, p1, p2) >= 0)
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the position of the pawn that en passant captures.<br>
	 * Uses p2 (the destination of the en passant move) to calculate the pawn to
	 * capture.<br>
	 * En passant move must have already been done.
	 * 
	 * @param board
	 *              the board
	 * @param p2
	 *              the destination of the en pasant
	 * @return the position of the captured pawn
	 */
	public static Position getEnPassant(Board board, Position p2)
	{
		// if the attacking color is white
		if (board.pieceAtPos(p2).getChessColor() == ChessColor.WHITE)
		{
			// the captured piece is 1 South of p2
			return Board.moveSouth(p2, 1);
		}
		// if the attacking color is black
		else
		{
			// the captured piece is 1 North of p2
			return Board.moveNorth(p2, 1);
		}
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

	@Override
	public String getImgURL()
	{
		if (this.getChessColor() == ChessColor.WHITE)
		{
			return WPAWN_URL;
		} else
		{
			return BPAWN_URL;
		}
	}

}


public class Queen extends Piece
{

	public static final String ABBREV = "Q";

	public static final String WQUEEN_URL = "http://github.com/pinkbluesky/chess/blob/master/ChessIcons/whiteQueen.png";
	public static final String BQUEEN_URL = "http://github.com/pinkbluesky/chess/blob/master/ChessIcons/blackQueen.png";

	/**
	 * Constructs a new Queen.<br>
	 * Moving Requirements: A queen can move anywhere diagonally, up, down,
	 * left, or right.
	 * 
	 * @param color
	 *              the color of the queen
	 */
	public Queen(ChessColor color, String id)
	{
		super(color, id);
	}

	public int canMove(Board board, Position p1, Position p2)
	{
		// for moving to an empty spot or an occupied spot
		// check that p1 has a piece on it & both positions are valid
		if (Board.isValid(p1) && Board.isValid(p2) && !board.isEmpty(p1))
		{
			Rook lateral = new Rook(getChessColor(), "rook used for queen");
			Bishop diagonal = new Bishop(getChessColor(), "bishop used for queen");
			int latOut = lateral.canMove(board, p1, p2);
			int diaOut = diagonal.canMove(board, p1, p2);

			// if this move is valid for a Rook or a Bishop
			// then it is valid for a queen
			if (latOut > 0)
			{
				return latOut;
			}
			if (diaOut > 0)
			{
				return diaOut;
			}
		}

		return Piece.INVALID_MOVE;
	}

	@Override
	public String getImgURL()
	{
		if (this.getChessColor() == ChessColor.WHITE)
		{
			return WQUEEN_URL;
		} else
		{
			return BQUEEN_URL;
		}
	}

}

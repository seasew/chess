
public class Knight extends Piece
{

	public static final String ABBREV = "N";

	public static final String WKNIGHT_URL = "http://github.com/pinkbluesky/chess/blob/master/ChessIcons/whiteKnight.png";
	public static final String BKNIGHT_URL = "http://github.com/pinkbluesky/chess/blob/master/ChessIcons/blackKnight.png";

	/**
	 * Constructs a Knight.<br>
	 * Moving requirements: <br>
	 * 2N & 1W, 1E<br>
	 * 2S & 1W, 1E<br>
	 * 2E & 1N, 1S<br>
	 * 2W & 1N, 1S<br>
	 * The knight can jump over other pieces.
	 * 
	 * @param color
	 *              the color of the knight
	 */
	public Knight(ChessColor color, String id)
	{
		super(color, id);
		// TODO Auto-generated constructor stub
	}

	public int canMove(Board board, Position p1, Position p2)
	{
		int isValidMove = isValidMove(board, p1, p2);
		// for moving to an empty spot or an occupied spot
		// check that p1 has a piece on it & both positions are valid
		if (Board.isValid(p1) && Board.isValid(p2) && !board.isEmpty(p1))
		{
			Position twoMoves = Board.moveNorth(p1, 2);
			// check 1W and 1E for North
			if (p2.equals(Board.moveWest(twoMoves, 1)) || p2.equals(Board.moveEast(twoMoves, 1)))
			{
				if (isValidMove > 0)
				{
					return isValidMove;
				}
			}

			twoMoves = Board.moveSouth(p1, 2);
			// check 1W and 1E for South
			if (p2.equals(Board.moveWest(twoMoves, 1)) || p2.equals(Board.moveEast(twoMoves, 1)))
			{
				if (isValidMove > 0)
				{
					return isValidMove;
				}
			}

			twoMoves = Board.moveEast(p1, 2);
			// check 1N and 1S for East
			if (p2.equals(Board.moveNorth(twoMoves, 1)) || p2.equals(Board.moveSouth(twoMoves, 1)))
			{
				if (isValidMove > 0)
				{
					return isValidMove;
				}
			}

			twoMoves = Board.moveWest(p1, 2);
			// check 1N and 1S for West
			if (p2.equals(Board.moveNorth(twoMoves, 1)) || p2.equals(Board.moveSouth(twoMoves, 1)))
			{
				if (isValidMove > 0)
				{
					return isValidMove;
				}
			}
		}
		return -1;
	}

	@Override
	public String getImgURL()
	{
		if (this.getChessColor() == ChessColor.WHITE)
		{
			return WKNIGHT_URL;
		} else
		{
			return BKNIGHT_URL;
		}
	}
}

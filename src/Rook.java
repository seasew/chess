
public class Rook extends Piece
{

	public static final String ABBREV = "R";

	public static final String WROOK_URL = "http://github.com/pinkbluesky/chess/blob/master/ChessIcons/whiteRook.png";
	public static final String BROOK_URL = "http://github.com/pinkbluesky/chess/blob/master/ChessIcons/blackRook.png";

	private boolean isFirst;

	/**
	 * Constructs a new Rook. <br>
	 * Moving Requirements: It can move up, down, left, or right<br>
	 * The entire path must be empty
	 * 
	 * @param color
	 *              the color of the rook
	 */
	public Rook(ChessColor color, String id)
	{
		super(color, id);
		isFirst = true;
	}

	public int canMove(Board board, Position p1, Position p2)
	{
		// for moving to an empty spot or an occupied spot
		// check that p1 has a piece on it & both positions are valid
		if (Board.isValid(p1) && Board.isValid(p2) && !board.isEmpty(p1))
		{
			// true if the path is empty
			boolean npath = true;
			boolean epath = true;
			boolean spath = true;
			boolean wpath = true;
			// rook can move anywhere up, down, left, right
			// Check all values of x less than SIZE
			int x = 1;
			while (x < Board.SIZE)
			{
				// current positions for all 4 directions
				Position north = Board.moveNorth(p1, x);
				Position east = Board.moveEast(p1, x);
				Position south = Board.moveSouth(p1, x);
				Position west = Board.moveWest(p1, x);

				// if the position matches and the selec ted path is empty
				if ((p2.equals(north) && npath) || (p2.equals(east) && epath) || (p2.equals(south) && spath)
						|| (p2.equals(west) && wpath))
				{
					if (isValidMove(board, p1, p2) > 0)
					{
						return isValidMove(board, p1, p2);
					}
				}

				// if the current north position is filled
				if (!board.isEmpty(north))
				{
					// then npath does not work for the next x value
					npath = false;
				}

				// if the current north position is filled
				if (!board.isEmpty(east))
				{
					// then npath does not work for the next x value
					epath = false;
				}

				// if the current north position is filled
				if (!board.isEmpty(south))
				{
					// then npath does not work for the next x value
					spath = false;
				}

				// if the current north position is filled
				if (!board.isEmpty(west))
				{
					// then npath does not work for the next x value
					wpath = false;
				}

				x++;

			}
		}
		return -1;
	}

	/**
	 * Changes the value of isFirst.
	 */
	public void toggleFirst()
	{
		isFirst = !isFirst;
	}

	/**
	 * Returns isFirst.
	 * 
	 * @return isFirst
	 */
	public boolean isFirst()
	{
		return isFirst;
	}

	@Override
	public String getImgURL()
	{
		if (this.getChessColor() == ChessColor.WHITE)
		{
			return WROOK_URL;
		} else
		{
			return BROOK_URL;
		}
	}
}

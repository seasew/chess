
public class Bishop extends Piece
{

	public static final String ABBREV = "B";

	/**
	 * Constructs a new Bishop. <br>
	 * Moving Requirements: In any diagonal direction.<br>
	 * The entire path must be empty
	 * 
	 * @param color
	 *              the color of the bishop
	 */
	public Bishop(ChessColor color, String id)
	{
		super(color, id);
		// TODO Auto-generated constructor stub
	}

	public int canMove(Board board, Position p1, Position p2)
	{
		// for moving to an empty spot or an occupied spot
		// p1 has a piece on it and both positions are valid
		if (Board.isValid(p1) && Board.isValid(p2) && !board.isEmpty(p1))
		{
			// bishop can move diagonally
			// North and East
			boolean nepath = true;
			// North and West
			boolean nwpath = true;
			// South and East
			boolean sepath = true;
			// South and West
			boolean swpath = true;

			int x = 1;
			while (x < Board.SIZE)
			{
				// the current positions for the 4 directions
				Position ne = Board.moveNE(p1, x);
				Position nw = Board.moveNW(p1, x);
				Position se = Board.moveSE(p1, x);
				Position sw = Board.moveSW(p1, x);

				// if the position matches a path and the selected path is
				// empty(excluding the last piece)
				if ((p2.equals(ne) && nepath) || (p2.equals(nw) && nwpath) || (p2.equals(se) && sepath)
						|| (p2.equals(sw) && swpath))
				{
					if (isValidMove(board, p1, p2) > 0)
					{
						return isValidMove(board, p1, p2);
					}
				}

				// if the current NE position is filled
				if (!board.isEmpty(ne))
				{
					// then this path is invalid for the next x
					nepath = false;
				}

				// if the current NW position is filled
				if (!board.isEmpty(nw))
				{
					// then this path is invalid for the next x
					nwpath = false;
				}

				// if the current SE position is filled
				if (!board.isEmpty(se))
				{
					// then this path is invalid for the next x
					sepath = false;
				}

				// if the current SW position is filled
				if (!board.isEmpty(sw))
				{
					// then this path is invalid for the next x
					swpath = false;
				}

				x++;
			}
		}

		return -1;
	}

	@Override
	public static String getPieceName()
	{
		return "Bishop";
	}

}

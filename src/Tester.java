public class Tester
{

	public static void main(String[] args)
	{

		ChessGame game = new ChessGame();

		System.out.println(game);
		// white
		System.out.println(game.movePiece(new Position(6, 3), new Position(4, 3)));
		System.out.println(game);
		// black
		System.out.println(game.movePiece(new Position(1, 1), new Position(2, 1)));
		System.out.println(game);
		// white
		System.out.println(game.movePiece(new Position(4, 3), new Position(3, 3)));
		System.out.println(game);
		// black
		System.out.println(game.movePiece(new Position(1, 2), new Position(3, 2)));
		System.out.println(game);
		// white
		System.out.println(game.movePiece(new Position(3, 3), new Position(2, 2)));
		System.out.println(game);

	}

}

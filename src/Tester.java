public class Tester
{

    public static void main(String[] args)
    {

	ChessGame game = new ChessGame();

	System.out.println(game);
	System.out.println(game.movePiece(new Position(6, 3), new Position(4, 3)));
	System.out.println(game);
	System.out.println(game.movePiece(new Position(4, 3), new Position(2, 3)));
	System.out.println(game);

    }

}

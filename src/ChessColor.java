
public enum ChessColor
{
    BLACK("B"), WHITE("W");

    private String id;

    private ChessColor(String id)
    {
	this.id = id;
    }

    public String getID()
    {
	return id;
    }

}

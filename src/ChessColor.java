
public enum ChessColor
{
    BLACK("B"), WHITE("W");

    private String abbrev;

    private ChessColor(String abbrev)
    {
	this.abbrev = abbrev;
    }

    public String getAbbrev()
    {
	return abbrev;
    }

}

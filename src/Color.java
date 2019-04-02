
public enum Color
{
    BLACK("B"), WHITE("W");

    private String id;

    private Color(String id)
    {
	this.id = id;
    }

    @Override
    public String toString()
    {
	return id;
    }
}


public class Position
{

    private int i;
    private int j;

    /**
     * Constructs a Position object with i and j.
     * 
     * @param i
     *            the row index
     * @param j
     *            the col index
     */
    public Position(int i, int j)
    {
	this.i = i;
	this.j = j;
    }

    /**
     * Returns if the position and other are the same.
     * 
     * @param other
     *            the Position to compare with
     * @return true if they are the same, false if not
     */
    @Override
    public boolean equals(Object ob)
    {
	Position pos = (Position) ob;
	// if both i&j are equal to each other
	if (pos.getI() == i && pos.getJ() == j)
	{
	    return true;
	}
	return false;
    }

    /**
     * Returns i.
     * 
     * @return i
     */
    public int getI()
    {
	return i;
    }

    /**
     * Returns j.
     * 
     * @return j
     */
    public int getJ()
    {
	return j;
    }

    @Override
    public String toString()
    {
	return ((char) (j + 97)) + "" + (i + 1);
    }
}

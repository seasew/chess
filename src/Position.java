
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

    /**
     * Adds this position with the given position.
     * 
     * @param pos1
     *            the first position
     * @param pos2
     *            the second position
     */
    public static Position addPos(Position pos1, Position pos2)
    {
	return new Position(pos1.getI() + pos2.getI(), pos1.getJ() + pos2.getJ());
    }

    /**
     * Returns the string representation of i (row number).<br>
     * 1 to 8
     * 
     * @return the string representation of i
     */
    public String iToString()
    {
	return (8 - i) + "";
    }

    /**
     * Returns the string representation of j (col number). <br>
     * a to h
     * 
     * @return the string representation of j
     */
    public String jToString()
    {
	return ((char) (j + 97) + "");

    }

    @Override
    public String toString()
    {
	return jToString() + iToString();
    }
}

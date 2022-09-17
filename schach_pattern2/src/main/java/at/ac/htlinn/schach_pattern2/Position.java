package at.ac.htlinn.schach_pattern2;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("(%d,%d)", x, y);
	}
	
	/**
	 * @return true, wenn die x-Werte gleich sind
	 */
	public boolean equalsX(Position p)
	{
		return x == p.x;
	}

	/**
	 * @return true, wenn die y-Werte gleich sind
	 */
	public boolean equalsY(Position p)
	{
		return y == p.y;
	}
	
	/**
	 * @return true, wenn die Punkte auf der gleichen Diagonale (beginnend links unten (0,0)) liegen
	 */
	public boolean equalsDiagLinksUnten(Position p)
	{
		return (x + y) == (p.getX() + p.getY());
	}

	/**
	 * @return true, wenn die Punkte auf der gleichen Diagonale (beginnend links oben (0,0)) liegen
	 */
	public boolean equalsDiagLinksOben(Position p)
	{
		return (x - y) == p.getX() - p.getY();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		Position p = (Position)obj;
		return (x == p.getX() && y == p.getY());
	}
	
	/**
	 * 
	 * @return gibt ein Array mit den sortierten x-Punkten zurueck
	 */
	public static Position[] getOrderedByX(Position p1, Position p2)
	{
		if (p1.getX() < p2.getX())
		{
			return new Position[] {p1,p2};
		} else
		{
			return new Position[] {p2, p1};
		}
	}
	
}

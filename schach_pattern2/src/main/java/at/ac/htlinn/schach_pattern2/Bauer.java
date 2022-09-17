package at.ac.htlinn.schach_pattern2;

public class Bauer extends Figur {
	public Bauer(boolean farbeweiss) {
		super(farbeweiss);
	}

	public boolean spielZugMoeglich(SpielFeld sp, Position von, Position nach) {
		if (!super.spielZugMoeglich(sp, von, nach)) return false;
		int dy = Math.abs(von.getY() - nach.getY());
		int diffX = von.getX() - nach.getX();
		if (dy == 0  && !sp.isFigur(nach)) // gerade fahren
		{
			if (farbeweiss) {
				return (diffX == 1) || (!bewegt && diffX == 2);

			} else {
				return (diffX == -1) || (!bewegt && diffX == -2);
			}
		} else if (dy == 1 && sp.isFigur(nach)) // schlagen
		{
			// checken ob entsprechend der Farbe sich in die y-Position um genau eins
			// unterscheidet
			return (farbeweiss && diffX == 1) || (!farbeweiss && diffX == -1);
		} else // alles andere ist ungueltig
		{
			return false;
		}
	}

	public String toString() {
		return "B" + super.toString();
	}

}

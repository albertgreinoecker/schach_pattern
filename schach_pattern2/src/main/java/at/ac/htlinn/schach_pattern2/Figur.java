package at.ac.htlinn.schach_pattern2;

/**
 * Basisklasse für alle Figuren auf dem Spielfeld
 * 
 * @author albert
 *
 */
public class Figur extends Feld {
	protected boolean farbeweiss;
	protected boolean bewegt;

	public Figur(boolean farbeweiss) {
		this.farbeweiss = farbeweiss;
		this.bewegt = false;
	}

	public boolean isFarbeweiss() {
		return farbeweiss;
	}

	public boolean isBewegt() {
		return bewegt;
	}

	public boolean spielZugMoeglich(SpielFeld sp, Position von, Position nach) {
		if (von.equals(nach))
			return false;

		if (!sp.isFigur(von))
			return false;

		Figur vonFigur = (Figur) sp.getFeld(von);

		if (sp.isFigur(nach)) {

			Figur nachFigur = (Figur) sp.getFeld(nach);
			if (vonFigur.farbeweiss == nachFigur.farbeweiss) {
				return false;
			}
		}
		return true;
	}

	protected boolean spielZugMoeglichGerade(SpielFeld sp, Position von, Position nach) {
		if (von.equalsX(nach)) {
			int startY = Math.min(von.getY(), nach.getY());
			int endY = Math.max(von.getY(), nach.getY());
			for (int i = startY + 1; i < endY; i++) {
				if (sp.isFigur(new Position(von.getX(), i)))
					return false;
			}
			return true; // Keine Figur im Weg
		} else if (von.equalsY(nach)) {
			int startX = Math.min(von.getX(), nach.getX());
			int endX = Math.max(von.getX(), nach.getX());
			for (int i = startX + 1; i < endX; i++) {
				if (sp.isFigur(i, von.getY()))
					return false;
			}
			return true; // Keine Figur im Weg
		} else
			return false;
	}

	protected boolean spielZugMoeglichDiagonal(SpielFeld sp, Position von, Position nach) {
		if (von.equalsDiagLinksOben(nach)) {
			Position[] ps = Position.getOrderedByX(von, nach);
			for (int i = 1; i < ps[1].getX() - ps[0].getX(); i++) {
				Position p = new Position(ps[0].getX() + i, ps[0].getY() + i);
				if (sp.isFigur(p)) {
					return false;
				}
			}
			return true;
		} else if (von.equalsDiagLinksUnten(nach)) {
			Position[] ps = Position.getOrderedByX(von, nach);
			for (int i = 1; i < ps[1].getX() - ps[0].getX(); i++) {
				Position p = new Position(ps[0].getX() + i, ps[0].getY() - i);
				if (sp.isFigur(p)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Bewege die Figur von <i>von></i> nach <i>nach</i>
	 */
	public void spielZug(SpielFeld sf, Position von, Position nach) {
		Feld vonFeld = sf.getFeld(von);
		sf.setFeld(new Feld(), von);
		sf.setFeld(vonFeld, nach);
		bewegt = true;
	}

	public String toString() {
		return (farbeweiss) ? "W" : "B";
	}
	
	/**
	 * Sind gleich wenn die toString gleich ist
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Figur))
		{
			return false;
		}
		Figur f = (Figur)obj;
		return f.toString().equals(toString());
	}
	
}

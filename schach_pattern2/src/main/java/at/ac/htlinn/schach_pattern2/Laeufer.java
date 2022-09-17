package at.ac.htlinn.schach_pattern2;

public class Laeufer extends Figur {

	public Laeufer(boolean farbeweiss) {
		super(farbeweiss);
	}

	public boolean spielZugMoeglich(SpielFeld sp, Position von, Position nach) {
		return super.spielZugMoeglich(sp, von, nach) && spielZugMoeglichDiagonal(sp, von, nach);
	}

	public String toString() {
		return "L" + super.toString();
	}

}

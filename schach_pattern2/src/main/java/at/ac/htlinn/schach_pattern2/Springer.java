package at.ac.htlinn.schach_pattern2;

public class Springer extends Figur {
	public Springer(boolean farbeweiss) {
		super(farbeweiss);
	}

	public boolean spielZugMoeglich(SpielFeld sp, Position von, Position nach) {
		//TODO: Checken ob der Spielzug für Springer möglich ist
		return false;
	}

	public String toString() {
		return "S" + super.toString();
	}

}

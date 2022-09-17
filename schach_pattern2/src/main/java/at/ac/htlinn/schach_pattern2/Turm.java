package at.ac.htlinn.schach_pattern2;

public class Turm extends Figur {
	public Turm(boolean farbeweiss) {
		super(farbeweiss);
	}

	public boolean spielZugMoeglich(SpielFeld sp, Position von, Position nach) {
		return super.spielZugMoeglich(sp, von, nach) && spielZugMoeglichGerade(sp, von, nach);
	}

	public String toString() {
		return "T" + super.toString();
	}
}

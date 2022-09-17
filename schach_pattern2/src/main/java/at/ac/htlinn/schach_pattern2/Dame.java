package at.ac.htlinn.schach_pattern2;

public class Dame extends Figur {
	public Dame(boolean farbeweiss) {
		super(farbeweiss);
	}

	public boolean spielZugMoeglich(SpielFeld sp, Position von, Position nach) {
		return super.spielZugMoeglich(sp, von, nach) && (spielZugMoeglichDiagonal(sp, von, nach) || spielZugMoeglichGerade(sp, von, nach));
	}

	public String toString() {
		return "D" + super.toString();
	}
}

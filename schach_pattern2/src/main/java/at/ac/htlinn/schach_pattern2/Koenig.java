package at.ac.htlinn.schach_pattern2;

import java.util.ArrayList;

public class Koenig extends Figur {
	// Es gibt 4 Möglichkeiten der Rochade (von, nach, Turm)
	private ArrayList<Position[]> rochades = new ArrayList<>();

	public Koenig(boolean farbeweiss) {
		super(farbeweiss);

		rochades.add(new Position[] { new Position(7, 4), new Position(7, 2), new Position(7, 0) });
		rochades.add(new Position[] { new Position(0, 4), new Position(0, 2), new Position(0, 0) });

		rochades.add(new Position[] { new Position(7, 4), new Position(7, 6), new Position(7, 7) });
		rochades.add(new Position[] { new Position(0, 4), new Position(0, 6), new Position(0, 7) });
	}

	private boolean isRochade(SpielFeld sp, Position von, Position nach) {
		for (Position[] rochade : rochades) {
			Position rVon = rochade[0];
			Position rNach = rochade[1];
			Position rTurm = rochade[2];
			if (rVon.equals(von) && rNach.equals(nach)) {
				return ((sp.getFeld(rTurm) instanceof Turm && !((Figur)sp.getFeld(rTurm)).isBewegt())  
						&& !((Figur)sp.getFeld(rVon)).isBewegt()
						&& spielZugMoeglichGerade(sp, rVon, rTurm));
			}
		}
		return false;
	}

	public boolean spielZugMoeglich(SpielFeld sp, Position von, Position nach) {
		if (!super.spielZugMoeglich(sp, von, nach))
			return false;
		if (isRochade(sp, von, nach))
			return true;
		int dx = Math.abs(von.getX() - nach.getX());
		int dy = Math.abs(von.getY() - nach.getY());
		return dx <= 1 && dy <= 1 && !sp.schach(von, nach);
	}

	@Override
	public void spielZug(SpielFeld sf, Position von, Position nach) {
		super.spielZug(sf, von, nach);
		if (isRochade(sf, von, nach)) {
			for (Position[] rochade : rochades) {
				Position rVon = rochade[0];
				Position rTurm = rochade[2];

				if (rVon.equals(von)) {
					Feld turm = sf.getFeld(rTurm);
					sf.setFeld(new Feld(), rTurm);
					sf.setFeld(turm, rTurm);
				}
			}
		}
	}

	public String toString() {
		return "K" + super.toString();
	}
}

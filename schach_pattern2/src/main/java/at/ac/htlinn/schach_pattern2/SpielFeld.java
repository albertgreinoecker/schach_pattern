package at.ac.htlinn.schach_pattern2;

import java.util.ArrayList;

public class SpielFeld {

	/**
	 * (0,0) ist links oben!!! weiss ist immer unten!!
	 * 
	 * (0,0) (0,1) (0,2) .... (1,0) (1,1) (1,2) .... ...... (7,0) (7,1) (7,2) ....
	 */
	private Feld[][] mat; // Zeile, Spalte
	private boolean werAmZug = true;

	public boolean isWerAmZug() {
		return werAmZug;
	}
		
	/**
	 * Per default soll einfach ein Spielfeld mit lauter leeren Feldern erzeugt
	 * werden
	 */
	public SpielFeld() {
		mat = new Feld[8][8];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = new Feld();
			}
		}
	}
	
	public SpielFeld(Feld[][] mat) {
		this.mat = mat;
	}

	/**
	 * @param zug Bsp. e2-e4
	 */
	public boolean spielzugMoeglich(String zug) {
		String[] p = zug.split("-");
		Position von = schach2koordinate(p[0]);
		Position nach = schach2koordinate(p[1]);
		if (isFigur(von) && ((Figur)getFeld(von)).isFarbeweiss() == werAmZug) {
			Figur f = (Figur) getFeld(von);
			return f.spielZugMoeglich(this, von, nach);
		}
		return false;
	}

	/**
	 * @param zug Bsp. e2-e4
	 * @return false, wenn der Spielzug nicht moeglich ist
	 */
	public boolean spielzug(String zug) {
		String[] p = zug.split("-");
		Position von = schach2koordinate(p[0]);
		Position nach = schach2koordinate(p[1]);
		if (isFigur(von) && ((Figur)getFeld(von)).isFarbeweiss() == werAmZug) 
		{
			Figur f = (Figur) getFeld(von);
			if (f.spielZugMoeglich(this, von, nach)) {
				f.spielZug(this, von, nach);
				werAmZug = !werAmZug; // Der andere Spieler ist jetzt am Zug
			}
			return true;
		}
		return false;
	}

	/**
	 * Setze ein Feld auf eine bestimmte Position
	 */
	public void setFeld(Feld f, Position p) {
		mat[p.getX()][p.getY()] = f;
	}

	/**
	 * Uebertragung einer Schach-Koordinate in die Koordinaten der Matrix
	 * 
	 * @param schach z.B. e2 => (4,6)
	 */
	private Position schach2koordinate(String schach) {
		int y = schach.charAt(0) - 'a';
		int x = '8' - schach.charAt(1);
		return new Position(x, y);
	}

	/**
	 * @see #getFeld(Position)
	 */
	public Feld getFeld(int x, int y) {
		return mat[x][y];
	}

	/**
	 * Hole das Feld von einer bestimmten position
	 */
	public Feld getFeld(Position p) {
		return getFeld(p.getX(), p.getY());
	}

	/**
	 * @see #isFigur(int, int)
	 */
	public boolean isFigur(Position p) {
		return isFigur(p.getX(), p.getY());
	}

	/**
	 * Steht auf <i>p</i> eine Figur oder ist das Feld leer?
	 */
	public boolean isFigur(int x, int y) {
		return (mat[x][y] instanceof Figur);
	}

	/**
	 * 
	 * @param ps eine Menge an Positionen die Überprüft werden sollen
	 * @return true wenn alle Felder frei sind
	 */
	public boolean felderFrei(ArrayList<Position> ps) {
		for (Position p : ps) {
			if (isFigur(p)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return Die Positionen der beiden Koenige
	 */
	private ArrayList<Position> holeKoenige() {
		//TODO: Hole die Koenige
		return new ArrayList<Position>();
	}

	/**
	 * @param weiss Die Farbe fuer die die Positionen ermittelt werden sollen
	 * @return Alle Positionen von Figuren einer bestimmten Farbe
	 */
	private ArrayList<Position> holeFiguren(boolean weiss) {
		ArrayList<Position> figuren = new ArrayList<>();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (getFeld(i, j) instanceof Figur && ((Figur) getFeld(i, j)).isFarbeweiss() == weiss) {
					figuren.add(new Position(i, j));
				}
			}
		}
		return figuren;
	}
	
	/**
	 * Ist nach Umsetzen des Koenigs von der alten- auf die neue Position dieser in
	 * Schach Nach dem Umsetzen wird wieder zurueckgesetzt, dass keine Seiteneffekte
	 * entstehen koennen
	 */
	public boolean schach(Position alt, Position neu) {
		Feld fAlt = getFeld(alt);
		Feld fNeu = getFeld(neu);
		// umsetzen
		setFeld(fAlt, neu);
		setFeld(new Feld(), alt);

		boolean schach = schach();
		// wieder zuruecksetzen
		setFeld(fAlt, alt);
		setFeld(fNeu, neu);
		return schach;
	}

	/**
	 * 
	 * @return true wenn allgemein eine Schach-Situation ist
	 */
	public boolean schach() {
		ArrayList<Position> koenige = holeKoenige();
		for (Position koenig : koenige) {
			Koenig k = ((Koenig) getFeld(koenig));
			ArrayList<Position> figuren = holeFiguren(!k.isFarbeweiss());
			for (Position figur : figuren) {
				Figur f = (Figur) getFeld(figur);
				if (f.spielZugMoeglich(this, figur, koenig)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param weiss die Farbe für die der Koenig zu holen ist.
	 * @return Den Koenig einer bestimmten Farbe
	 */
	private Position holeKoenig(boolean weiss)
	{
		ArrayList<Position> koenige = holeKoenige();
		Koenig k0 = (Koenig)getFeld(koenige.get(0));
		return (k0.isFarbeweiss() == weiss ? koenige.get(0) : koenige.get(1));
	}
	
	public boolean schachmatt() {
		if (!schach())
			return false;
		Position koenig = holeKoenig(werAmZug);
		return (moeglicheSpielZuege(koenig).size() == 0);
	}

	/**
	 * Schaue ob ein Spielzug fuer eine bestimmte Farbe moeglich ist
	 */
	private boolean patt(boolean weiss) {
		ArrayList<Position> ps = holeFiguren(weiss);
		for (Position p : ps) {
			if (moeglicheSpielZuege(p).size() > 0)
				return false;
		}
		return true;
	}

	public boolean patt() {
		return !schach() && (patt(true) || patt(false));
	}

	/**
	 * Es wird ueberprueft welche SpielZuege von der Position <i>p</i> aus moeglich
	 * sind
	 */
	private ArrayList<Position> moeglicheSpielZuege(Position p) {
		ArrayList<Position> posMoeglich = new ArrayList<Position>();
		if (!(getFeld(p) instanceof Figur))
			return posMoeglich;
		Figur figurVon = (Figur) getFeld(p);

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				Position nach = new Position(i, j);
				if (figurVon.spielZugMoeglich(this, p, nach)) {
					posMoeglich.add(nach);
				}
			}
		}
		return posMoeglich;
	}
	
	public void ausgabe() {
		//TODO Das komplette Spielfeld soll ausgegeben werden
	}
}

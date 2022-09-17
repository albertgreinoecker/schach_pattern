package at.ac.htlinn.schach_pattern2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpielFeldIO {


	
	public static SpielFeld einlesen(String fName) throws FileNotFoundException {
		File file = new File(SpielFeldIO.class.getClassLoader().getResource(fName).getFile());
		Scanner s = new Scanner(file);
		int zeile = 0;
		Feld[][] mat = new Feld[8][8];
		while (s.hasNextLine()) {
			String st = s.nextLine();
			String[] a = st.split("\\|");
			for (int spalte = 0; spalte < a.length; spalte++) {
				Feld f = leseFeld(a[spalte]);
				mat[zeile][spalte] = f;
			}
			zeile++;
		}
		s.close();
		return new SpielFeld(mat);
	}

	private static Feld leseFeld(String feld) {
		char figur = feld.charAt(0);
		char farbe = feld.charAt(1);
		boolean farbew = (farbe == 'W');

		switch (figur) {
		case 'T':
			return new Turm(farbew);
		case 'S':
			return new Springer(farbew);
		case 'L':
			return new Laeufer(farbew);
		case 'K':
			return new Koenig(farbew);
		case 'D':
			return new Dame(farbew);
		case 'B':
			return new Bauer(farbew);
		}
		return new Feld();
	}
}

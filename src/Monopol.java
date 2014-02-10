import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Det tradisjonelle brettspillet "Monopol" spilt i terminal...
 * 
 * @author OleEskild
 * 
 */

public class Monopol {
	private static Aksje[] aksjer;
	private static Spiller[] spiller;
	private static Terning terning = new Terning();
	private static Spillebrett brett = new Spillebrett();

	private static Aksje hentAksje(int posisjon) {
		for (int i = 0; i < Monopol.aksjer.length; i++) {
			if (posisjon == Monopol.aksjer[i].hentPlassering()) {
				return Monopol.aksjer[i];
			}

		}
		return null;
	}

	private static void kjopAksje(Spiller s, Aksje aksje) {
		aksje.kjopAksje(s);
		System.out.println("Du har kjopt aksjen " + aksje.hentNavn());
	}

	public static void main(String[] args) {

		/** Leser inn alle aksjene */
		try (Scanner aksjeFil = new Scanner(new File("Aksjer.txt"))) {
			Monopol.aksjer = new AksjeLeser().leggInnAksjer(aksjeFil);
		} catch (FileNotFoundException ex) {
			System.out.println("Filen finnes ikke");
		}

		/** �nsker velkommen til spillet */
		System.out.println("Velkommen til Monopol!");
		System.out.print("Velg antall spillere: ");

		// Lar brukeren velge antall spillere
		boolean fortsett = true;
		int antallSpillere = 0;
		while (fortsett) {
			fortsett = false;
			try {
				antallSpillere = new Scanner(System.in).nextInt();
			} catch (Exception ex) {
				System.out.println("Du m� skrive inn et heltall!");
				fortsett = true;
			}
		}

		// Oppretter en tabell med antall spillere
		Monopol.spiller = new Spiller[antallSpillere];

		// Lar brukeren gi navn til spillerne
		for (int i = 0; i < Monopol.spiller.length; i++) {
			System.out.print("Skriv inn navnet til spiller " + (i + 1) + ": ");
			Monopol.spiller[i] = new Spiller(new Scanner(System.in).nextLine());
		}

		/** Meny der brukeren f�r velge hva man skal gj�re */
		boolean nyRunde = true;
		while (nyRunde) {
			for (int i = 0; i < Monopol.spiller.length; i++) {
				boolean nesteSpiller = false;
				while (!nesteSpiller) {
					System.out.println();
					Monopol.brett.tegnBrett(0);

					System.out.println(Monopol.spiller[i].hentNavn()
							+ ", velg hva du vil gjoere: ");
					System.out.println("1: Trill terning");
					System.out.println("2: Se akjser");
					System.out.println("3: Avslutt spillet!!");
					System.out.println();
					switch (new Scanner(System.in).nextLine()) {
					case "1":
						trillTerning(Monopol.spiller[i]);
						Monopol.brett.tegnBrett(Monopol.spiller[i]
								.hentPosisjon());
						valgMeny(Monopol.spiller[i]);
						nesteSpiller = true;
						break;
					case "2":
						visKjopteAksjer(Monopol.spiller[i]);
						break;
					case "3":
						nyRunde = false;
						break;
					default:
						System.out
								.println("Du maa skrive et tall som viser til en av valgene du har!");
						break;
					}
				}
			}
		}

	}

	/** Triller terning og flytter spilleren frem p� brettet */
	private static void trillTerning(Spiller s) {
		Monopol.terning.kastTerning();
		s.flyttSpiller(Monopol.terning.hentSum());
		System.out.print(Monopol.terning.toString() + " og flytter frem "
				+ Monopol.terning.hentSum() + " plasser til ");
		if (hentAksje(s.hentPosisjon()) != null) {
			System.out.println(hentAksje(s.hentPosisjon()).hentNavn());
		}
	}

	private static void valgMeny(Spiller s) {
		Aksje a;
		if (hentAksje(s.hentPosisjon()) == null) {
			System.out
					.println("Midlertidig tekst, ettersom det etterhvert ikke skal finnes steder der det ikke er aksjer");
			a = hentAksje(1);
		} else {
			a = hentAksje(s.hentPosisjon());
		}

		if (a.hentEier() == null) {
			System.out.println("�nsker du � kj�pe " + a.hentNavn()
					+ "?(ja/nei)");
			String svar = new Scanner(System.in).nextLine();
			if (svar.toLowerCase().equals("ja")) {
				kjopAksje(s, a);
			} else if (svar.toLowerCase().equals("nei")) {
				System.out.println("Du kjopte ikke aksjen!");
			} else {
				System.out.println("Du m� skrive ja eller nei..");
			}
		}

		else {
			System.out
					.println("Denne aksjen eies av " + a.hentEier().hentNavn()
							+ "(Husk at denne ikke gj�r noe)");
			// Husk � implementer overf�ring av penger
		}

	}

	private static void visKjopteAksjer(Spiller s) {
		for (int i = 0; i < Monopol.aksjer.length; i++) {
			if (Monopol.aksjer[i].hentEier() != null) {
				if (Monopol.aksjer[i].hentEier().equals(s)) {
					System.out.println(Monopol.aksjer[i].hentNavn());
				}
			}
		}
	}

}
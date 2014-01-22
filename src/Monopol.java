import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Monopol{
	private static Aksje[] aksjer;
	private static Spiller[] spiller;
	private static Terning terning = new Terning();
	private static Spillebrett brett = new Spillebrett();
	
	public static void main(String[] args){

		/**Leser inn alle aksjene*/
		try(Scanner aksjeFil = new Scanner(new File("Aksjer.txt"))){
			aksjer = new AksjeLeser().leggInnAksjer(aksjeFil);
		}catch(FileNotFoundException ex){
			System.out.println("Filen finnes ikke");
		}
		
		/**Ønsker velkommen til spillet*/
		System.out.println("Velkommen til Monopol!");
		System.out.print("Velg antall spillere: ");
		
		//Lar brukeren velge antall spillere
		boolean fortsett = true;
		int antallSpillere = 0;
		while(fortsett){
			fortsett = false;
			try{
				antallSpillere = new Scanner(System.in).nextInt();
			}catch(Exception ex){
				System.out.println("Du må skrive inn et heltall!");
				fortsett = true;
			}
		}

		//Oppretter en tabell med antall spillere
		spiller = new Spiller[antallSpillere];

		//Lar brukeren gi navn til spillerne
		for(int i = 0; i < spiller.length; i++){
			System.out.print("Skriv inn navnet til spiller " + (i+1) + ": ");
			spiller[i] = new Spiller(new Scanner(System.in).nextLine());
		}

		/**Meny der brukeren får velge hva man skal gjøre */
		boolean nyRunde = true;
		while(nyRunde){
			for(int i = 0; i<spiller.length; i++){
				fortsett = true;
				while(fortsett){
				System.out.println();
				brett.tegnBrett(0);
				System.out.println(spiller[i].hentNavn() + ", velg hva du vil gjoere: ");
				System.out.println("1: Trill terning");
				System.out.println("2: Se akjser");
				System.out.println("3: Avslutt spillet!!");
				System.out.println();
				switch(new Scanner(System.in).nextLine()){
					case "1" : trillTerning(spiller[i]); 
					brett.tegnBrett(spiller[i].hentPosisjon());
					valgMeny(spiller[i]); 
					fortsett = false; 
					break;
					case "2" : visKjopteAksjer(spiller[i]); break;
					case "3" : nyRunde = false;
					default : System.out.println("Du maa skrive et tall som viser til en av valgene du har!"); break;
					}
				}
			}
		}
		

	}
	
	private static void kjopAksje(Spiller s, Aksje aksje){
		aksje.kjopAksje(s);
		System.out.println("Du har kjøpt aksjen " +  aksje.hentNavn());
	}
		
	private static void visKjopteAksjer(Spiller s){
		for(int i = 0; i<aksjer.length; i++){
			if(aksjer[i].hentEier() != null)
				if(aksjer[i].hentEier().equals(s))
					System.out.println(aksjer[i].hentNavn());
		}
	}
	
	private static Aksje hentAksje(int posisjon){
		for(int i = 0; i<aksjer.length; i++){
			if(posisjon == aksjer[i].hentPlassering())
				return aksjer[i];
			
		}
		return null;
	}
	
	/**Triller terning og flytter spilleren frem på brettet*/
	private static void trillTerning(Spiller s){
		terning.kastTerning(); 
		s.flyttSpiller(terning.hentSum());
		System.out.print(terning.toString() + " og flytter frem "+ terning.hentSum() + " plasser til "); 
		if(hentAksje(s.hentPosisjon()) != null)
			System.out.println(hentAksje(s.hentPosisjon()).hentNavn());
	}
	
	private static void valgMeny(Spiller s){
		Aksje a;
		if(hentAksje(s.hentPosisjon()) == null){
			System.out.println("Midlertidig tekst, ettersom det etterhvert ikke skal finnes steder der det ikke er aksjer");
			a = hentAksje(1);
		}
		else
			a = hentAksje(s.hentPosisjon());
		
		if(a.hentEier() == null){
			System.out.println("Ønsker du å kjøpe " + a.hentNavn() + "?(ja/nei)");
			String svar = new Scanner(System.in).nextLine();
			if(svar.toLowerCase().equals("ja"))
				kjopAksje(s, a);
			
			else if(svar.toLowerCase().equals("nei"))
				System.out.println("Du kjopte ikke aksjen!");
			
			else 
				System.out.println("Du må skrive ja eller nei..");	
		}
		
		else{
			System.out.println("Denne aksjen eies av " + a.hentEier().hentNavn() + "(Husk at denne ikke gjør noe)");
			//Husk å implementer overføring av penger
			}
		
			
	}
	
}
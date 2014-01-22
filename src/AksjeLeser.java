import java.util.Scanner;

public class AksjeLeser{
	
	public Aksje[] leggInnAksjer(Scanner fil){
		fil.nextLine(); //Hopper over den foerste linjen som bare er en forklaring til hvordan filen ska skrives
		int antall = Integer.parseInt(fil.nextLine());
		
		Aksje[] aksje = new Aksje[antall];
		String[] info;
		for(int i = 0; i<antall; i++){
			info = fil.nextLine().split(" ");
			aksje[i] = new Aksje(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[3]), new int[]{Integer.parseInt(info[4]), Integer.parseInt(info[5]), Integer.parseInt(info[6]), Integer.parseInt(info[7]), Integer.parseInt(info[8]), Integer.parseInt(info[9])}, Integer.parseInt(info[10]));
		}

		return aksje;
	}
}
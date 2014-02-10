import java.util.*;
import java.io.*;

public class Spiller{
	private String navn;
	private int posisjon;
	private final int antallPosisjoner = 40;
	private int antallAksjer; //antall aksjer som spilleren eier
	Aksje[] aksjer; 
	

	public Spiller(String navn){
		this.navn = navn;
		posisjon = 1;
		antallAksjer = 0;
		
	}

	public boolean equals(Spiller s){
			return this.navn.equals(s.navn);
	}
	
	public void flyttSpiller(int antallTrekk){
		posisjon += antallTrekk;
		if(posisjon > antallPosisjoner)
			posisjon -= antallPosisjoner;
	}


	public String hentNavn(){
		return navn;
	}
	
	public int hentPosisjon(){
		return posisjon;
	}
		
		
	
}


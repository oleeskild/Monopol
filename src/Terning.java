import java.util.Random;

/**
 * 
 * @author OleEskild
 *
 */

public class Terning{
	Random generator = new Random();
	private int[] terningVerdi = new int[2];

	//konstrukt�r
	public Terning(){}

	/** Henter summen som en int av de to ternigene som ble kastet */
	public int hentSum(){
		int sum = 0;
		for(int i = 0; i<terningVerdi.length; i++){
			sum += terningVerdi[i];
		}
		return sum;
	}

	/**Generer to tilfeldige tall som returneres som en tabell me ints*/
	public int[] kastTerning(){
		for(int i = 0; i < terningVerdi.length; i++){
			terningVerdi[i] = generator.nextInt(6) + 1;
		}
		return terningVerdi;
	}

	@Override
	public String toString(){
		return "Du har trillet " + terningVerdi[0] + " og " + terningVerdi[1];
	}
}
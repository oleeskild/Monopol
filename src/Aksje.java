public class Aksje{
	private static int id = 0;
	private int minId;
	private String navn;
	private int pris;
	private int[] leiePris; // 0 = bare tomt 1 = 1 leilighet ... 4 = 4 leiligheter 5 = hotell
	private int pantePris;
	private int prisForHus;
	private int gruppe; //Hvilken fargegruppe aksjen hører til. 0 = brun. 1 = blå. 2 = rosa. 3 = orange. 4 = roed. 5 = gul. 6 = gronn. 7 = blaa
	private Spiller eier = null;
	private int plassering;

	public Aksje(String navn, int pris, int prisForHus, int gruppe, int[] leiePris, int plassering){
		minId = id++;
		this.navn = navn;
		this.pris = pris;
		this.gruppe = gruppe;
		this.leiePris = leiePris;
		this.pantePris = (pris/2);
		this.prisForHus = prisForHus;
		this.plassering = plassering;
	}

	public int hentPris(){
		return pris;
	}

	public int hentPantePris(){
		return pantePris;
	}

	public int hentPrisForHus(){
		return prisForHus;
	}
	
	public void kjopAksje(Spiller spiller){
		this.eier = spiller; 
	}

	public Spiller hentEier(){
		return this.eier;
	}

	public String hentNavn(){
		return this.navn;
	}

	public int hentPlassering(){
		return plassering;
	}
}

public class Spillebrett {

	private String[] brett;
	
	public Spillebrett(){
		brett  = new String[7];
		brett[0] = "|Holmlia |        |Torggata|        |        | Bjerke |        |Gron.lei|Ringgata|        |";
		brett[1] = "|________|________|________|________|________|________|________|________|________|________|";
		for(int i = 2; i < brett.length-1; i++)
			brett[i] ="|        |        |        |        |        |        |        |        |        |        |";
		brett[6] = brett[1];
		}
	
	public void tegnBrett(int posisjon){
		if(posisjon >0)
			brett[3] = brett[3].substring(0, ((posisjon)*8)+4) + 'x' + brett[3].substring(((posisjon)*8)+5,brett[3].length()-1);
		System.out.println();
		for(int j = 0; j<brett.length; j++){
			System.out.println(brett[j]);
		}
		System.out.println();
	}
}


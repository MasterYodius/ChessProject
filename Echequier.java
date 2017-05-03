
public class Echequier {
	private Case[][] plateau;
	
	public Echequier(){
		this.plateau = new Case[8][8];
		for (int i = 0 ; i < 8 ; i++)
		{
			 for (int j = 0 ; j < 8 ; j++)
			 {
				 plateau[i][j] = new Case();
			 }
		}
	}
	
	public Case getCase(int i, int j){
		return this.plateau[i][j];
 	}
	
	public void setCase(int i,int j,Case c){
		this.plateau[i][j] = c;
	}
	
	public String toString(){
		/*//int i=
		String ligne = "  *----------*----------*----------*----------*----------*----------*----------*----------*\n";
		String s = "        A          B          C          D          E          F          G          H\n";
		//String nom =  
		String affichage=s+ligne+"|"+getCase(1,1);
		return affichage;
				
	}*/

}


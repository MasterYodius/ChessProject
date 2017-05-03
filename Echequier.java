
public class Echequier {
	private Case[][] plateau;
	
	/*public Echequier(){
		this.plateau = new Case[8][8];
		for (int i = 0 ; i < 8 ; i++)
		{
			 for (int j = 0 ; j < 8 ; j++)
			 {
				 plateau[i][j] = new Case();
			 }
		}
	}*/
	public Echequier(){
		this.plateau = new Case[8][8];
		
		this.plateau[0][0]=new Case(0,0,new Tour(false),false);
		this.plateau[0][7]=new Case(0,7,new Tour(false),false);
		this.plateau[0][1]=new Case(0,1,new Cavalier(false),false);
		this.plateau[0][6]=new Case(0,6,new Cavalier(false),false);
		this.plateau[0][2]=new Case(0,2,new Fou(false),false);
		this.plateau[0][5]=new Case(0,5,new Fou(false),false);
		this.plateau[0][3]=new Case(0,3,new Reine(false),false);
		this.plateau[0][4]=new Case(0,4,new Roi(false),false);
		
		this.plateau[7][0]=new Case(7,0,new Tour(true),false);
		this.plateau[7][7]=new Case(7,7,new Tour(true),false);
		this.plateau[7][1]=new Case(7,1,new Cavalier(true),false);
		this.plateau[7][6]=new Case(7,6,new Cavalier(true),false);
		this.plateau[7][2]=new Case(7,2,new Fou(true),false);
		this.plateau[7][5]=new Case(7,5,new Fou(true),false);
		this.plateau[7][3]=new Case(7,3,new Reine(true),false);
		this.plateau[7][4]=new Case(7,4,new Roi(true),false);
		
		
		
		
		for (int x = 0; x<8;x++)
		{
			this.plateau[1][x]=new Case(1,x,new Pion(false),false);
		}
		
		for (int y = 0; y<8;y++)
		{
			this.plateau[6][y]=new Case(6,y,new Pion(true),false);
		}
		
		for(int i=2;i<5;i++){
			for(int j=0;j<8;j++){
				this.plateau[i][j]=new Case(i,j,true);
			}
		}
		
		
		
		
		
		
	}
	
	public Case getCase(int i, int j){
		return this.plateau[i][j];
 	}
	
	public void setCase(int i,int j,Piece p){
		this.plateau[i][j].setPiece(p);
		this.plateau[i][j].setLigne(i);
		this.plateau[i][j].setColonne(j);
	}
	
	public String toString(){
		String s="";
		String ligne = "--------------------------------------------------------------------------------------------------------\n";
		String affichage=ligne;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
			/*	if(this.plateau[i][j].getPiece()==null){
					s=s+"            |";
				}
				else{*/
					s=s+this.getCase(i,j).getPiece().getNom();
					if(this.getCase(i,j).getPiece().getestBlanc())
						s=s+"B   |";
					else
						s=s+"N   |";
				//}
			}
			s=s+"\n"+ligne;
		}
		affichage=affichage+s;
		return affichage;
				
	}

}




public class Echequier {
	private Case[][] plateau;
	

	
	public Echequier(){
		this.plateau = new Case[8][8];
		/*
		for(int l=0; l<8; l++)
			for(int c=0; c<8; c++)
				this.plateau[l][c] = new Case(l, c, false);
		*/
		this.plateau[0][0]=new Case(0,0,new Tour(false),false);
		this.plateau[0][7]=new Case(0,7,new Tour(false),false);
		this.plateau[0][1]=new Case(0,1,new Cavalier(false),false);
		this.plateau[0][6]=new Case(0,6,new Cavalier(false),false);
		this.plateau[0][2]=new Case(0,2,new Fou(false),false);
		this.plateau[0][5]=new Case(0,5,new Fou(false),false);
		this.plateau[0][3]=new Case(0,3,new Reine(false),false);
		this.plateau[0][4]=new Case(0,4,new Roi(false,0,4),false);
		
		this.plateau[7][0]=new Case(7,0,new Tour(true),false);
		this.plateau[7][7]=new Case(7,7,new Tour(true),false);
		this.plateau[7][1]=new Case(7,1,new Cavalier(true),false);
		this.plateau[7][6]=new Case(7,6,new Cavalier(true),false);
		this.plateau[7][2]=new Case(7,2,new Fou(true),false);
		this.plateau[7][5]=new Case(7,5,new Fou(true),false);
		this.plateau[7][3]=new Case(7,3,new Reine(true),false);
		this.plateau[7][4]=new Case(7,4,new Roi(true,7,4),false);
		
		
		
		
		for (int x = 0; x<8;x++)
		{
			this.plateau[1][x]=new Case(1,x,new Pion(false,1,x),false);
		}
		
		for (int y = 0; y<8;y++)
		{
			this.plateau[6][y]=new Case(6,y,new Pion(true,6,y),false);
		}

		
		for(int i=2;i<6;i++){
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
		String s= new String();
		String ligne = "-----------------------------------------\n";
		String affichage=ligne;
			
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(j==0)
					s = s + "| ";
				if(this.plateau[i][j]!=null)
					s = s + this.plateau[i][j].getPiece() + " | ";
				else
					s = s + "   | ";
				if(j==7){
					s = s + "\n" + ligne + "\n";
				}
				
			}

		}

		
		affichage=affichage+s;
		return affichage;
				
	}
	
	
	public void deplacement(int x1,int y1,int x2,int y2){
		if((this.plateau[x1][y1].getPiece().deplacementValide(x1,y1, x2, y2) && this.plateau[x2][y2].isEstVide() && this.cheminPossible(x1, y1, x2, y2)) // si le deplacement est valide et la case d arriver est vide
			// si le deplacement est valide et que la case n est pas vide et que la case est mangeable
				|| (this.plateau[x2][y2].isEstVide()==false && this.estMangeableCouleur(x1, y1, x2, y2)==true && this.plateau[x1][y1].getPiece().deplacementValide(x1,y1, x2, y2) &&  this.cheminPossible(x1, y1, x2, y2))){
						
			this.plateau[x2][y2]=new Case(x2,y2,this.plateau[x1][y1].getPiece(),false);
			this.plateau[x1][y1].libererCase();
		}
		
		
		
	}
	
	
	public boolean estMangeableCouleur(int x1, int y1, int x2 ,int y2){
		if(this.plateau[x2][y2].isEstVide()) 
			return false;
		else if(this.getCase(x1, y1).getPiece().getestBlanc()!=this.getCase(x2, y2).getPiece().getestBlanc())
			return true;
		return false;
	}
	
	public boolean cheminPossible(int x1,int y1, int x2 , int y2){
		Piece pieceDepart = this.plateau[x1][y1].getPiece();
		if((pieceDepart instanceof Cavalier) || (pieceDepart instanceof Roi)) // si la piece est un cavalier
			return true;
		
		
		if(pieceDepart instanceof Pion){	// si la piece est un pion
			if(x1-x2==2 ){
				return this.plateau[x2][y2].isEstVide() && this.plateau[x2+1][y2].isEstVide();
			}
			else if(x1-x2==-2){
				return this.plateau[x2][y2].isEstVide() && this.plateau[x2-1][y2].isEstVide();
			}
			else if(y1==y2)
				return true;
			
				if(this.estMangeableCouleur(x1, y1, x2, y2) && ((x2 == x1+1 && y2== y1+1) || (x2 == x1+1 && y2== y1-1) || (x2 == x1-1 && y2== y1+1) || (x2 == x1-1 && y2== y1-1))) 
					return true;
				else 
					return false;
		
		}
		else if( pieceDepart instanceof Tour){
			if(Math.abs(x1-x2)==1 || Math.abs(y1-y2) == 1)
				return true;
		
			if(x1==x2 && y1<y2){// la tour va a droite
				int nb = y2-y1;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloqué  
						if(!(this.plateau[x1][y1+i].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;
				
			}
			else if(x1==x2 && y1>y2){  //gauche
				int nb = y1-y2;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloqué  
					if(!(this.plateau[x1][y1-i].isEstVide())){
						bloquer=true;
					}
					i++;
				}
				return !bloquer;
				
			}
			else if(y1==y2 && x1<x2 ){      //bas
				int nb = x2-x1;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloqué  
					if(!(this.plateau[x1+i][y1].isEstVide())){
						bloquer=true;
					}
					i++;
				}
					return !bloquer;
			}
			else if(y1==y2 && x1>x2){                     //haut
				int nb = x1-x2;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloqué  
						if(!(this.plateau[x1-i][y1].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;					
			}
		}
		else if( pieceDepart instanceof Fou ){
			if(Math.abs(x1-x2)==1 )
				return true;
		
			if(x1>x2 && y1>y2){// le fou va en haut a gauche
				int nb = x1-x2;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloqué  
						if(!(this.plateau[x1-i][y1-i].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;				
			}
			else if(x1>x2 && y1<y2){// le fou va en haut a droite
				int nb = x1-x2;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloqué  
						if(!(this.plateau[x1-i][y1+i].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;
			}
			else if(x1<x2 && y1<y2){// le fou va en bas a droite
				int nb = x2-x1;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloqué  
					if(!(this.plateau[x1+i][y1+i].isEstVide())){
						bloquer=true;
					}
					i++;
				}
				return !bloquer;
			}
			else if(x1<x2 && y1>y2){// le fou va en bas a gauche
				int nb = x2-x1;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloqué  
					if(!(this.plateau[x1+i][y1-i].isEstVide())){
						bloquer=true;
					}
					i++;
				}
					
					return !bloquer;
			}
			
		}
		else if( pieceDepart instanceof Reine ){
			if(Math.abs(x1-x2)==1 || Math.abs(y1-y2) == 1)
				return true;
		
			if(x1==x2 && y1<y2){// la tour va a droite
				int nb = y2-y1;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloqué  
						if(!(this.plateau[x1][y1+i].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;
				
			}
			else if(x1==x2 && y1>y2){  //gauche
				int nb = y1-y2;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloqué  
					if(!(this.plateau[x1][y1-i].isEstVide())){
						bloquer=true;
					}
					i++;
				}
				return !bloquer;
				
			}
			else if(y1==y2 && x1<x2 ){      //bas
				int nb = x2-x1;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloqué  
					if(!(this.plateau[x1+i][y1].isEstVide())){
						bloquer=true;
					}
					i++;
				}
					return !bloquer;
			}
			else if(y1==y2 && x1>x2){                     //haut
				int nb = x1-x2;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloqué  
						if(!(this.plateau[x1-i][y1].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;					
			}
		
			else if(x1>x2 && y1>y2){// le fou va en haut a gauche
				int nb = x1-x2;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloqué  
						if(!(this.plateau[x1-i][y1-i].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;				
			}
			else if(x1>x2 && y1<y2){// le fou va en haut a droite
				int nb = x1-x2;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloqué  
						if(!(this.plateau[x1-i][y1+i].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;
			}
			else if(x1<x2 && y1<y2){// le fou va en bas a droite
				int nb = x2-x1;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloqué  
					if(!(this.plateau[x1+i][y1+i].isEstVide())){
						bloquer=true;
					}
					i++;
				}
				return !bloquer;
			}
			else if(x1<x2 && y1>y2){// le fou va en bas a gauche
				int nb = x2-x1;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloqué  
					if(!(this.plateau[x1+i][y1-i].isEstVide())){
						bloquer=true;
					}
					i++;
				}
					
					return !bloquer;
			}
		}
	
		return false;

	}
	
	
	
	
	
	
	
	
	

}



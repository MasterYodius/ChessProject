import java.util.Scanner;

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
		this.plateau[0][1]=/*new Case(0,1,true);*/new Case(0,1,new Cavalier(false),false);
		this.plateau[0][6]=/*new Case(0,6,true);*/new Case(0,6,new Cavalier(false),false);
		this.plateau[0][2]=new Case(0,2,new Fou(false),false);
		this.plateau[0][5]=new Case(0,5,new Fou(false),false);
		this.plateau[0][3]=new Case(0,3,new Reine(false),false);
		this.plateau[0][4]=new Case(0,4,new Roi(false,0,4),false);
		
		this.plateau[7][0]=new Case(7,0,new Tour(true),false);
		this.plateau[7][7]=new Case(7,7,new Tour(true),false);
		this.plateau[7][1]=/*new Case(7,1,true);*/new Case(7,1,new Cavalier(true),false);
		this.plateau[7][6]=/*new Case(7,6,true);*/new Case(7,6,new Cavalier(true),false);
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
		String ligne = "-----------------------------------------";
		String affichage="-----------------------------------------\n";
			
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(j==0)
					s = s + "| ";
				if(!(this.plateau[i][j].isEstVide()))
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
	
	
	public void deplacement(int x1,int y1,int x2,int y2) throws Exception{
		if(this.plateau[x1][y1].getPiece().deplacementValide(x1,y1, x2, y2)){
			if(this.plateau[x1][y1].getPiece() instanceof Pion && ((this.plateau[x1][y1].getPiece().getestBlanc() && x2==0) || (!(this.plateau[x1][y1].getPiece().getestBlanc()) && x2==7))){
					promotion(x1, y1, x2, y2);
			}
			else if((/*this.plateau[x1][y1].getPiece().deplacementValide(x1,y1, x2, y2) &&*/ this.plateau[x2][y2].isEstVide() && this.cheminPossible(x1, y1, x2, y2) && this.verifCheminEchec(x1, y1, x2, y2) ) // si le deplacement est valide et la case d arriver est vide
					// si le deplacement est valide et que la case n est pas vide et que la case est mangeable
						|| (this.plateau[x2][y2].isEstVide()==false && this.estMangeableCouleur(x1, y1, x2, y2)==true && this.plateau[x1][y1].getPiece().deplacementValide(x1,y1, x2, y2) &&  this.cheminPossible(x1, y1, x2, y2)&& this.verifCheminEchec(x1, y1, x2, y2))){
							this.plateau[x2][y2]=new Case(x2,y2,this.plateau[x1][y1].getPiece(),false);
							this.plateau[x1][y1].libererCase();
			}
			
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
				return this.plateau[x2][y2].isEstVide() ;
			
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
				int nb = y2-y1;
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
				int nb = y2-y1;
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
				int nb = y2-y1;
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
				int nb = y1-y2;
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
	
	
	/*public boolean verifEchec(int x1,int y1,int x2,int y2){
		Piece pieceDepart = this.plateau[x1][y1].getPiece();
		boolean couleurPiece = pieceDepart.getestBlanc();
		int xRoiE=0; 
		int yRoiE=0;
		int xRoiA=0;
		int yRoiA=0;
		
		for(int i=0;i<8;i++){   
			for(int j=0; j<8;j++){
				if(this.plateau[i][j].isEstVide()==false){
					
				
					if(this.plateau[i][j].getPiece().getestBlanc()!=couleurPiece && this.plateau[i][j].getPiece() instanceof Roi){
						xRoiE=i; 
						yRoiE=j;
					}
					else if(this.plateau[i][j].getPiece().getestBlanc()==couleurPiece && this.plateau[i][j].getPiece() instanceof Roi){
						xRoiA=i; 
						yRoiA=j;
					}
				}
			}
		}
		if(this.cheminPossible(x2, y2, xRoiE, yRoiE))
			return true;
		
		if(x1==xRoiA && y1<yRoiA){	// la piece protege a droite
			int nb = 7-y1;
			int i=1;
				while(i<nb){ //tant que il est pas bloqué  
					if(!(this.plateau[x1][y1+i].isEstVide()) && this.plateau[x1][y1+i].getPiece().getestBlanc()==couleurPiece ){
						return false;
					}
					else if(!(this.plateau[x1][y1+i].isEstVide()) && this.plateau[x1][y1+i].getPiece().getestBlanc()!=couleurPiece && this.plateau[x1][y1+i].getPiece().deplacementValide(x1, y1+i, xRoiA, yRoiA) && this.cheminPossible(x1, y1+i, xRoiA, yRoiA)){
						return true;
					}
					i++;
				}
		}
		
		return false;
	}*/
	public boolean verifEchecs(Joueur j1){
		boolean couleurPiece=j1.isCouleurBlanc();
		
		int xRoiE=0; 
		int yRoiE=0;
		
		for(int i=0;i<8;i++){   
			for(int j=0; j<8;j++){
				if(this.plateau[i][j].isEstVide()==false){
					
				
					if(this.plateau[i][j].getPiece().getestBlanc()==couleurPiece && this.plateau[i][j].getPiece() instanceof Roi){
						xRoiE=i; 
						yRoiE=j;
					}
				}
			}
		}
		for(int i=0;i<8;i++){   
			for(int j=0; j<8;j++){
				if(this.plateau[i][j].isEstVide()==false){
					
				
					if(this.plateau[i][j].getPiece().getestBlanc()!=couleurPiece && this.cheminPossible(i, j, xRoiE, yRoiE)&& this.plateau[i][j].getPiece().deplacementValide(i, j, xRoiE, yRoiE) ){
						//System.out.println(i+" "+j); 
						return true;
						
					}
				}
			}
		}	
		return false;
	}

	public boolean verifCheminEchec(int x1,int y1,int x2,int y2){
		
		Case c1=new Case(this.plateau[x1][y1]);
		boolean couleurPiece = c1.getPiece().getestBlanc();
		
		Case c2=new Case(this.plateau[x2][y2]);
		//this.plateau[x1][y1]=c2;
		this.plateau[x2][y2]=c1;
		this.plateau[x1][y1].libererCase();

		if(this.verifEchecs(new Joueur(couleurPiece," "))){
			this.plateau[x1][y1]=c1;
			this.plateau[x2][y2]=c2;
			return false;
		}
		this.plateau[x1][y1]=c1;
		this.plateau[x2][y2]=c2;
		return true;
	}
	
	public void promotion(int x1,int y1,int x2,int y2){
		Piece pieceDepart = this.plateau[x1][y1].getPiece();
		System.out.println("en quelle piece voulez vous promouvoir?(tapez 1 pour Tour, tapez 2 pour Fou, tapez 3 Reine, tapez 4 Cavalier)");
		Scanner sc=new Scanner(System.in);
		int a= sc.nextInt();
		
		
		
		if(pieceDepart.getestBlanc()){
			if(a==1)
				this.plateau[x2][y2]=new Case(x2,y2,new Tour(true),false);
			else if(a==2)
				this.plateau[x2][y2]=new Case(x2,y2,new Fou(true),false);
			else if(a==3)
				this.plateau[x2][y2]=new Case(x2,y2,new Reine(true),false);
			else if(a==4)
				this.plateau[x2][y2]=new Case(x2,y2,new Cavalier(true),false);
			
		
		}
		else{
			if(a==1)
				this.plateau[x2][y2]=new Case(x2,y2,new Tour(false),false);
			else if(a==2)
				this.plateau[x2][y2]=new Case(x2,y2,new Fou(false),false);
			else if(a==3)
				this.plateau[x2][y2]=new Case(x2,y2,new Reine(false),false);
			else if(a==4)
				this.plateau[x2][y2]=new Case(x2,y2,new Cavalier(false),false);
			
		}
		
		this.plateau[x1][y1].libererCase();
		
	}
	
	public boolean mat(Joueur JBlanc,Joueur JNoir){
		boolean blanc =this.verifEchecs(JBlanc);
		boolean noir = this.verifEchecs(JNoir);
		
		if(blanc==false && noir==false)
			return false;
		
		boolean couleurPiece;
		
		if(blanc==true)
			couleurPiece=true;	//si roi blanc est en echec
		else
			couleurPiece=false;	//si roi noir est en echec
		int xRoi=0; 
		int yRoi=0;
		
		
		for(int i=0;i<8;i++){   
			for(int j=0; j<8;j++){
				if(this.plateau[i][j].isEstVide()==false){
					
				
					if(this.plateau[i][j].getPiece().getestBlanc()==couleurPiece && this.plateau[i][j].getPiece() instanceof Roi){
						xRoi=i; 
						yRoi=j;
					}
				}
			}
		}
		
		for(int x1=0;x1<8;x1++){
			for(int y1=0;y1<8;y1++){
				if(this.plateau[x1][y1].isEstVide()==false && this.plateau[x1][y1].getPiece().getestBlanc()==couleurPiece){
					for(int x2=0;x2<8;x2++){
						for(int y2=0;y2<8;y2++){
							if(this.verifCheminEchec(x1, y1, x2, y2)&& this.cheminPossible(x1, y1, x2, y2) && this.plateau[x1][y1].getPiece().deplacementValide(x1, y1, x2, y2) &&(this.plateau[x2][y2].isEstVide() ||(this.plateau[x2][y2].isEstVide()==false && this.estMangeableCouleur(x1, y1, x2, y2)==true ))){
								System.out.println(x1+" "+y1+" "+x2+" "+y2);
								return false;
							}
						}
					}
					
				}
			}
		}
		
		
		return true;
	}
	
	
	
	



	public boolean pat(Joueur JBlanc,Joueur JNoir){
		return false;
	}



}
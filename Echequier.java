import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Echequier {
	private Case[][] plateau;
	

	
	public Echequier(boolean b){
		this.plateau = new Case[8][8];
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				this.plateau[i][j]=new Case(i,j,true);
			}
		}
		if(b){
	
			this.plateau[0][0]=new Case(0,0,new Tour(false,false),false);
				this.plateau[0][7]=new Case(0,7,new Tour(false,false),false);
				this.plateau[0][1]=new Case(0,1,new Cavalier(false,false),false);
				this.plateau[0][6]=new Case(0,6,new Cavalier(false,false),false);
				this.plateau[0][2]=new Case(0,2,new Fou(false,false),false);
				this.plateau[0][5]=new Case(0,5,new Fou(false,false),false);
				this.plateau[0][3]=new Case(0,3,new Reine(false,false),false);
				this.plateau[0][4]=new Case(0,4,new Roi(false,false),false);
				
				this.plateau[7][0]=new Case(7,0,new Tour(true,false),false);
				this.plateau[7][7]=new Case(7,7,new Tour(true,false),false);
				this.plateau[7][1]=new Case(7,1,new Cavalier(true,false),false);
				this.plateau[7][6]=new Case(7,6,new Cavalier(true,false),false);
				this.plateau[7][2]=new Case(7,2,new Fou(true,false),false);
				this.plateau[7][5]=new Case(7,5,new Fou(true,false),false);
				this.plateau[7][3]=new Case(7,3,new Reine(true,false),false);
				this.plateau[7][4]=new Case(7,4,new Roi(true,false),false);
				
				
				
				
				for (int x = 0; x<8;x++)
				{
					this.plateau[1][x]=new Case(1,x,new Pion(false,false),false);
				}
				
				for (int y = 0; y<8;y++)
				{
					this.plateau[6][y]=new Case(6,y,new Pion(true,false),false);
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
	
	/*public String toString(){
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
	}*/
	public String toString(){
	 	StringBuilder s = new StringBuilder("     A    B    C    D    E    F    G    H   ").append(System.lineSeparator());
		s.append("  ┌────┬────┬────┬────┬────┬────┬────┬────┐").append(System.lineSeparator());
		for(int i=0; i<8; i++){
			s.append((8-i) + " │ ");
			for(int j=0; j<8; j++){
				if(!(this.plateau[i][j].isEstVide()))
					s.append(this.plateau[i][j].getPiece()).append(" │ ");
				else
					s.append("  ").append(" │ ");
			}
			s.append(System.lineSeparator());
			if(i<7)
				s.append("  ├────┼────┼────┼────┼────┼────┼────┼────┤").append(System.lineSeparator());
			else
				s.append("  └────┴────┴────┴────┴────┴────┴────┴────┘").append(System.lineSeparator());
		}
		return s.toString();
	}

	
	
	public void deplacement(int x1,int y1,int x2,int y2) throws Exception{
		if(this.plateau[x1][y1].getPiece() instanceof Roi && this.plateau[x1][y1].getPiece().isDejaDeplacer()==false){
			if(!(this.verifEchecs(new Joueur(this.plateau[x1][y1].getPiece().getestBlanc(), "nom")))){
				if(x1==x2 && y2==6){// Roque vers la droite
					int nb = y2-y1;
					int i=1;
						boolean bloquer = false;
						while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
							if(!(this.plateau[x1][y1+i].isEstVide())){
								bloquer=true;
							}
							i++;
						}
						if(bloquer==false && this.plateau[x1][7].getPiece() instanceof Tour && this.verifCheminEchec(x1, y1, x2, y2)){
							this.plateau[x2][y2]=new Case(x2,y2,this.plateau[x1][y1].getPiece(),false);// deplacement du Roi
							this.plateau[x1][y1].libererCase();
							
							this.plateau[x2][5]=new Case(x2,5,this.plateau[x1][7].getPiece(),false);// deplacement de la Tour
							this.plateau[x1][7].libererCase();
						}
					
				}
				else if(x1==x2 && y2==2){  // roque vers la gauche
					int nb = y1-y2;
					int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
						if(!(this.plateau[x1][y1-i].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					if(bloquer==false && this.plateau[x1][0].getPiece() instanceof Tour  && this.verifCheminEchec(x1, y1, x2, y2)){
						this.plateau[x2][2]=new Case(x2,2,this.plateau[x1][y1].getPiece(),false);// deplacement du Roi
						this.plateau[x1][y1].libererCase();
						
						this.plateau[x2][3]=new Case(x2,3,this.plateau[x1][0].getPiece(),false);// deplacement de la Tour
						this.plateau[x1][0].libererCase();
					}
					
				}
			}
		}
		
		if(this.plateau[x1][y1].getPiece().deplacementValide(x1,y1, x2, y2)){
			if(this.plateau[x1][y1].getPiece() instanceof Pion && ((this.plateau[x1][y1].getPiece().getestBlanc() && x2==0) || (!(this.plateau[x1][y1].getPiece().getestBlanc()) && x2==7))){
					promotion(x1, y1, x2, y2);
			}
			else if(( this.plateau[x2][y2].isEstVide() && this.cheminPossible(x1, y1, x2, y2) && this.verifCheminEchec(x1, y1, x2, y2) ) // si le deplacement est valide et la case d arriver est vide
					// si le deplacement est valide et que la case n est pas vide et que la case est mangeable
						|| (this.plateau[x2][y2].isEstVide()==false && this.estMangeableCouleur(x1, y1, x2, y2)==true && this.plateau[x1][y1].getPiece().deplacementValide(x1,y1, x2, y2) &&  this.cheminPossible(x1, y1, x2, y2)&& this.verifCheminEchec(x1, y1, x2, y2))){
							this.plateau[x2][y2]=new Case(x2,y2,this.plateau[x1][y1].getPiece(),false);
							this.plateau[x1][y1].libererCase();
							this.plateau[x2][y2].getPiece().setDejaDeplacer(true);
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
		if(x1>=8 ||x1<0||y1>=8 ||y1<0||x2>=8 ||x2<0||y2>=8 ||y2<0)
			return false;
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
					while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
				while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
				while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
					while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
					while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
					while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
				while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
				while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
					while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
				while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
				while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
					while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
						if(!(this.plateau[x1-i][y1].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;					
			}
		
			else if(x1>x2 && y1>y2 && Math.abs(x1-x2)==Math.abs(y1-y2)){// le fou va en haut a gauche
				int nb = y1-y2;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
						if(!(this.plateau[x1-i][y1-i].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;				
			}
			else if(x1>x2 && y1<y2&& Math.abs(x1-x2)==Math.abs(y1-y2)){// le fou va en haut a droite
				int nb = y2-y1;
				int i=1;
					boolean bloquer = false;
					while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
						if(!(this.plateau[x1-i][y1+i].isEstVide())){
							bloquer=true;
						}
						i++;
					}
					return !bloquer;
			}
			else if(x1<x2 && y1<y2 && Math.abs(x1-x2)==Math.abs(y1-y2)){// le fou va en bas a droite
				int nb = y2-y1;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
					if(!(this.plateau[x1+i][y1+i].isEstVide())){
						bloquer=true;
					}
					i++;
				}
				return !bloquer;
			}
			else if(x1<x2 && y1>y2 && Math.abs(x1-x2)==Math.abs(y1-y2)){// le fou va en bas a gauche
				int nb = y1-y2;
				int i=1;
				boolean bloquer = false;
				while(i<nb && !bloquer){ //tant que il est pas bloquÃ©  
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
		boolean echecs =false;
		for(int i=0;i<8;i++){   
			for(int j=0; j<8;j++){
				if(this.plateau[i][j].isEstVide()==false){
					
				
					if(this.plateau[i][j].getPiece().getestBlanc()!=couleurPiece && this.cheminPossible(i, j, xRoiE, yRoiE)&& this.plateau[i][j].getPiece().deplacementValide(i, j, xRoiE, yRoiE) ){
						//System.out.println(i+" "+j); 
						echecs=true;
						
					}
				}
			}
		}	
		return echecs;
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
		
		
		
		
			if(a==1)
				this.plateau[x2][y2]=new Case(x2,y2,new Tour(pieceDepart.getestBlanc(),false),false);
			else if(a==2)
				this.plateau[x2][y2]=new Case(x2,y2,new Fou(pieceDepart.getestBlanc(),false),false);
			else if(a==3)
				this.plateau[x2][y2]=new Case(x2,y2,new Reine(pieceDepart.getestBlanc(),false),false);
			else if(a==4)
				this.plateau[x2][y2]=new Case(x2,y2,new Cavalier(pieceDepart.getestBlanc(),false),false);
		
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
		boolean mat= true;
		for(int x1=0;x1<8;x1++){
			for(int y1=0;y1<8;y1++){
				if(this.plateau[x1][y1].isEstVide()==false && this.plateau[x1][y1].getPiece().getestBlanc()==couleurPiece){
					for(int x2=0;x2<8;x2++){
						for(int y2=0;y2<8;y2++){
							if(this.verifCheminEchec(x1, y1, x2, y2)&& this.cheminPossible(x1, y1, x2, y2) && this.plateau[x1][y1].getPiece().deplacementValide(x1, y1, x2, y2) &&(this.plateau[x2][y2].isEstVide() ||(this.plateau[x2][y2].isEstVide()==false && this.estMangeableCouleur(x1, y1, x2, y2)==true ))){
								//System.out.println(x1+" "+y1+" "+x2+" "+y2);
								mat=false;
							}
						}
					}
					
				}
			}
		}
		
		
		return mat;
	}
	
	
	
	



	public boolean pat(Joueur JBlanc,Joueur JNoir){
		if(this.verifEchecs(JBlanc)||this.verifEchecs(JNoir))
			return false;
		int xRoiB=0; 
		int yRoiB=0;
		int xRoiN=0;
		int yRoiN=0;
		
		for(int i=0;i<8;i++){  //trouver les coordonnees des 2 rois 
			for(int j=0; j<8;j++){
				if(this.plateau[i][j].isEstVide()==false){
					
				
					if(this.plateau[i][j].getPiece().getestBlanc()==true && this.plateau[i][j].getPiece() instanceof Roi){
						xRoiB=i; 
						yRoiB=j;
					}
					else if(this.plateau[i][j].getPiece().getestBlanc()==false && this.plateau[i][j].getPiece() instanceof Roi){
						xRoiN=i; 
						yRoiN=j;
					}
				}
			}
		}
		boolean patB=true;
		boolean patN=true;
		
		
		
		for(int x1=0;x1<8;x1++){
			for(int y1=0;y1<8;y1++){
				if(this.plateau[x1][y1].isEstVide()==false){
					if(this.plateau[x1][y1].getPiece().getestBlanc()==true){
						for(int x2=0;x2<8;x2++){
							for(int y2=0;y2<8;y2++){
								if(this.verifCheminEchec(x1, y1, x2, y2)&& this.cheminPossible(x1, y1, x2, y2) && this.plateau[x1][y1].getPiece().deplacementValide(x1, y1, x2, y2) &&(this.plateau[x2][y2].isEstVide() ||(this.plateau[x2][y2].isEstVide()==false && this.estMangeableCouleur(x1, y1, x2, y2)==true ))){
									patB= false;
								}
							}
						}
					}
					else{
						for(int x2=0;x2<8;x2++){
							for(int y2=0;y2<8;y2++){
								if(this.verifCheminEchec(x1, y1, x2, y2)&& this.cheminPossible(x1, y1, x2, y2) && this.plateau[x1][y1].getPiece().deplacementValide(x1, y1, x2, y2) &&(this.plateau[x2][y2].isEstVide() ||(this.plateau[x2][y2].isEstVide()==false && this.estMangeableCouleur(x1, y1, x2, y2)==true ))){
									patN = false;
								}
							}
						}
					}
				}
			}
		}
	return patB || patN;
		
		
	}

	
	public void save(String NomFichier)
	{
		try
		{
		FileWriter fch = new FileWriter( NomFichier );
		BufferedWriter bch = new BufferedWriter( fch );

		int dejaDeplacer;
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(!(this.plateau[i][j].isEstVide()))
				{	
					if(this.plateau[i][j].getPiece().isDejaDeplacer())
						dejaDeplacer=1;
					else
						dejaDeplacer=0;
					fch.write(this.plateau[i][j].getPiece()+"\t"+i+"\t"+j+"\t"+dejaDeplacer+"\t");
				}
				else
					fch.write("xx\tx\tx\tx\t");
			}
		}
		bch.close();
		fch.close();
		




		}catch( IOException e)
		{
			System.err.println(e);
		}
	}
	
	public void Load( String NomFichier)
	{
		try
		{
			Echequier e=new Echequier(false);
			FileReader fch = new FileReader( NomFichier );
			BufferedReader bch = new BufferedReader( fch );
			String line = bch.readLine();
			while( line != null)
			{
				StringTokenizer st = new StringTokenizer( line, "\t");
				line = bch.readLine();
				
				char nom;
				Piece p;
				String s;
				int x;
				int y;
				boolean d;
				boolean estblanc;
				
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){

						s=st.nextToken();
						if(s.equals("xx")){
							e.plateau[i][j]=new Case(i,j,true);
							for(int q=0;q<3;q++){
								st.nextToken();
							}
						}
						else{
							char t = s.charAt(1);
							if(t=='N')
								estblanc=false;
							else
								estblanc=true;
							
							nom=s.charAt(0);
							
							s=st.nextToken();
							x=s.charAt(0)-48;
							
							s=st.nextToken();
							y=s.charAt(0)-48;
							
							s=st.nextToken();
							if(s.charAt(0)=='0')
								d=false;
							else 
								d=true;
							
							if(nom=='P'){
								e.plateau[i][j]=new Case(i, j, new Pion(estblanc,d),false);
							}
							else if(nom=='T'){
								e.plateau[i][j]=new Case(i, j, new Tour(estblanc,d),false);
							}
							else if(nom=='C'){
								e.plateau[i][j]=new Case(i, j, new Cavalier(estblanc,d),false);
							}
							else if(nom=='F'){
								e.plateau[i][j]=new Case(i, j, new Fou(estblanc,d),false);
							}
							else if(nom=='r'){
								e.plateau[i][j]=new Case(i, j, new Reine(estblanc,d),false);
							}
							else if(nom=='R'){
								e.plateau[i][j]=new Case(i, j, new Roi(estblanc,d),false);
							}
							
						}
						
					
					}
				}
				this.plateau=e.plateau;
			}
			bch.close();
			fch.close();
		} 
		catch( IOException e)
		{
			System.err.println(e);
		}
	} 

	public void jouer(){
		Scanner sc =new Scanner(System.in);
		
		System.out.println("voulez vous charger la derniere partie ? (tapez 1 pour oui, tapez 2 pour non)");
		int m= sc.nextInt();
		if(m==1){
			this.Load("sauvegarde.txt");
		}
	
		
		Joueur j1=new Joueur(true,"ivan");
		Joueur j2=new Joueur(false,"alex");
		
		System.out.println(this.toString());
	
		String c;
		int x1;
		int y1;
		int x2;
		int y2;	
		boolean tour = true;
		while(this.mat(j1,j2)==false && this.pat(j1,j2)==false){
			boolean choix = false;
		do{
			do{
				do{
					System.out.println("coordonnée depart ou tapez 'sauver' pour sauvegarder");
					c=sc.nextLine();
				}while(c.length()!=2 && !c.equals("sauver"));
				if(c.equals("sauver")){
					this.save("sauvegarde.txt");
					System.out.println("a ete sauver");
				}
				if(this.plateau[Math.abs(7-(c.charAt(1)-49))][c.charAt(0)-65].isEstVide())
					System.out.println("Pas de pièce à ces coordonnées");
				else
					choix=true;
			}while(((c.charAt(1)-49<0 || c.charAt(1)-49>7 || c.charAt(0)-65<0 || c.charAt(0)-65 >7) && !choix) ||this.plateau[Math.abs(7-(c.charAt(1)-49))][c.charAt(0)-65].isEstVide());
			
			y1=c.charAt(0)-65;
			x1=Math.abs(7-(c.charAt(1)-49));
			
			 if( !(this.plateau[x1][y1].getPiece().getestBlanc()==tour))
				System.out.println("Ce n'est pas votre tour");
			
		}while(!(this.plateau[x1][y1].getPiece().getestBlanc()==tour) ||this.plateau[x1][y1].isEstVide());
			
		
			System.out.println("coordonnée arrivée");
			
			do{
				do{
					c=sc.nextLine();
				}while(c.length()!=2);
			}while(c.charAt(1)-49<0 || c.charAt(1)-49>7 || c.charAt(0)-65<0 || c.charAt(0)-65 >7);
			
			y2=c.charAt(0)-65;
			x2=Math.abs(7-(c.charAt(1)-49));

			try{
				this.deplacement(x1,y1,x2,y2);
			}catch(Exception e){
				System.out.println("il n'y a pas de piece à  ces coordonnées");
			};
		
	
			System.out.println(this.toString());
			System.out.println("joueur blanc echec:"+this.verifEchecs(j1));
			System.out.println("joueur noir echec:"+this.verifEchecs(j2));
			System.out.println(tour);
			
			
			if(tour == true)
			{
				tour = false;
				System.out.println("Au tour des Noirs");
			}
			
			else{
				tour = true;
				System.out.println("Au tour des Blanc");
			}
		
		}
		if( this.mat(j1, j2) && this.verifEchecs(j1))
			System.out.println("ECHEC ET MAT : les noirs ont gagné");
		
		else if(this.mat(j1, j2) && this.verifEchecs(j2))
			System.out.println("ECHEC ET MAT : les blancs ont gagné");
		else if(this.pat(j1, j2))
			System.out.println("pat : match nul");
		
		sc.close();
	}
	
	

}
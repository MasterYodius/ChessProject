
public abstract class Piece {
	private String nom;
	private boolean estBlanc;
	
	
	public Piece(){
		this.nom="vegeto";
		this.estBlanc=false;
		
	}	
	public Piece(String n,boolean c){
		this.nom=n;
		this.estBlanc=c;
	}
	public Piece(Piece p){
		this.nom=new String(p.nom);
		this.estBlanc = p.estBlanc;
	}
	
	public String getNom(){
		return this.nom;
	}
	public boolean getestBlanc(){
		return this.estBlanc;
	}
	
	public void setNom(String n){
		this.nom = n;
	}
	public void setestBlanc(boolean c){
		this.estBlanc = c;
	}
	public String toString(){
		return this.getNom() +" "+this.getestBlanc() ;
	}
	//public abstract void deplacement();
}


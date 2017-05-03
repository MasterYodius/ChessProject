
public class Case {
	private Piece p;
	private int x;
	private int y;
	private boolean estVide;
	
	public Case(int l,int c,boolean v){
		this.p=null;
		this.x=l;
		this.y=c;
		this.estVide = v;
	}
	
	public Case(int ligne, int colonne,Piece piece, boolean v){
		this.p = piece;
		this.x = ligne;
		this.y = colonne;
		this.estVide = v;
	}
	
	public Piece getPiece(){
		return this.p;
	}
	public int getLigne(){
		return this.x;
	}
	public int getColonne(){
		return this.y;
	}
	
	public void setPiece(Piece piece){
		this.p = piece;
	}
	public void setPiece(){
		this.p=null;
	}
	public void setLigne(int ligne){
		this.x = ligne;
	}
	public void setColonne(int colonne){
		this.y = colonne;
	}

	public boolean isEstVide() {
		return this.estVide;
	}

	public void setestVide(boolean estVide) {
		this.estVide = estVide;
	}


}
	

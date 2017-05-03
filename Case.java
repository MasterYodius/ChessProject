
public class Case {
	private Piece p;
	private int x;
	private int y;
	
	public Case(){}
	
	public Case(Piece piece, int ligne, int colonne){
		this.p = piece;
		this.x = ligne;
		this.y = colonne;
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
	public void setLigne(int ligne){
		this.x = ligne;
	}
	public void setColonne(int colonne){
		this.y = colonne;
	}
	public String toString(){
		return /*this.getLigne()+" "+this.getColonne()+" "+ */" "+this.getPiece();
	}
}


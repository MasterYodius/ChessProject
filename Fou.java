
public class Fou extends Piece{
	public Fou(boolean c){
		super("F",c);
	}
	
	public String toString(){
		return super.toString();
	}

	public boolean deplacementValide(int x1,int y1, int x2, int y2) {
		if(Math.abs(x1-x2)==Math.abs(y1-y2) && x1!=x2 && y1!=y2 )
			return true;
		return false;
	}


}

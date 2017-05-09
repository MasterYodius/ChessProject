
public class Cavalier extends Piece{
	public Cavalier(boolean c){
		super("C",c);
	}
	
	public String toString(){
		return super.toString();
	}

	public boolean deplacementValide(int x1,int y1, int x2, int y2) {
		if((Math.abs(x1-x2)/Math.abs(y1-y2)==2 || ((double)Math.abs(x1-x2)/Math.abs(y1-y2)==.5)))
				return true;
		return false;
	}

}

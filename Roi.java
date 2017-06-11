
public class Roi extends Piece{

	
	public Roi(boolean c,boolean d){
		super("R",c,d);
	}

	
	public String toString(){
		return super.toString();
	}

	public boolean deplacementValide(int x1,int y1, int x2, int y2) {
		if(((x1==x2 && Math.abs(y1-y2)==1)|| (y1==y2 && Math.abs(x1-x2)==1) )||((Math.abs(x1-x2)==Math.abs(y1-y2))   && Math.abs(y1-y2)==1))
			return true;
		return false;
	}


}

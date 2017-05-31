
public class Roi extends Piece{
	private int xDep;
	private int yDep;
	
	public Roi(boolean c,int xD,int yD){
		super("R",c);
		this.xDep=xD;
		this.yDep=yD;
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

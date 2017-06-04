
public class Tour extends Piece{
	public Tour(boolean c,boolean d){
		super("T",c,d);
	}
	
	public String toString(){
		return super.toString();
	}

	public boolean deplacementValide(int x1,int y1, int x2, int y2) {
		if((x1==x2 && y1!=y2)|| (y1==y2 && x1!=x2 ) )
			return true;
		return false;
	}


}

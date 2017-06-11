
public class Cavalier extends Piece{
	public Cavalier(boolean c,boolean d){
		super("C",c,d);
	}
	
	public String toString(){
		return super.toString();
	}

	public boolean deplacementValide(int x1,int y1, int x2, int y2) {
		return (x1!=x2&& y1!=y2&&((((double)((double)Math.abs(x1-x2)/(double)Math.abs(y1-y2))==2 || ((double)(Math.abs(x1-x2)/Math.abs(y1-y2))==.5)))||((double)Math.abs(y1-y2)/(double)Math.abs(x1-x2))==2 || (double)Math.abs(y1-y2)/(double)Math.abs(x1-x2)==.5 )&& (Math.abs(x1-x2)<=2 && Math.abs(y1-y2)<=2));
		
	}

}

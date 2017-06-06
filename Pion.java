
public class Pion extends Piece{
	
	public Pion (boolean c,boolean d){
		super("P",c,d);
	}
	
	public String toString(){
		return super.toString();
	}

	public boolean deplacementValide(int x1,int y1, int x2, int y2) {
		if(this.isDejaDeplacer()==false && ((Math.abs(x1-x2)==2) && y1==y2 ||(Math.abs(x1-x2)==1))&& this.getestBlanc()==false &&(x1<x2))
			return true;
		else if(this.isDejaDeplacer()==false &&( (Math.abs(x1-x2)==2) && y1==y2 ||(Math.abs(x1-x2)==1))&& this.getestBlanc()==true &&(x1>x2))
			return true;
		
		else if((Math.abs(x1-x2)==1) && (this.getestBlanc()==false &&(x1<x2))) // deja bouger noir
			return true;
		
		else if((Math.abs(x1-x2)==1) && (this.getestBlanc()==true &&(x1>x2))) // deja bouger blanc
			return true;
	
		else if(x1!=x2 && y1!=y2 &&(Math.abs(x1-x2)==Math.abs(y1-y2))&&  (Math.abs(x1-x2)==1 || Math.abs(y1-y2)==1) && this.getestBlanc()==false && x1<x2  )
			return true;
		else if(x1!=x2 && y1!=y2 &&(Math.abs(x1-x2)==Math.abs(y1-y2))&&  (Math.abs(x1-x2)==1 || Math.abs(y1-y2)==1) && this.getestBlanc()==true && x1>x2 )
			return true;
		return false;
	}

	

}

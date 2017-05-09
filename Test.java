
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Echequier e1 = new Echequier();
		System.out.println(e1.toString());
		
		e1.deplacement(1,1,2,1);
		e1.deplacement(0,3,3,3);
		//e1.deplacement(0,0,1,0);
		//e1.deplacement(2,0,2,1);
		//e1.deplacement(7,0,6,0);
		//e1.deplacement(0,0,1,0);
		//e1.deplacement(7,0,7,1);
		
		System.out.println(e1.toString());
		
		
	}
}

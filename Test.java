
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Echequier e1 = new Echequier();
		System.out.println(e1.toString());
	System.out.println(e1.cheminPossible(1,1,2,2));
		e1.deplacement(1,1,2,2);
	//	e1.deplacement(0,3,3,0);
	//	e1.deplacement(0,3,7,3);
		//e1.deplacement(6,2,4,2);
	//	e1.deplacement(7,3,4,6);
		System.out.println(e1.toString());
		
		
		
		
	}
}

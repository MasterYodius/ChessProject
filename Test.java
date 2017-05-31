import java.util.Scanner;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Echequier e1 = new Echequier();
		
		
		Joueur j1=new Joueur(true,"ivan");
		Joueur j2=new Joueur(false,"alex");
		
		System.out.println(e1.toString());
		Scanner sc =new Scanner(System.in);
		
		int x1;
		int y1;
		int x2;
		int y2;		
		while(e1.mat(j1,j2)==false){
			System.out.println("coordonnée depart");
			x1= sc.nextInt();
			y1=sc.nextInt();
			System.out.println("coordonnée arrivé");
			x2=sc.nextInt();
			y2=sc.nextInt();
			
			try{
				e1.deplacement(x1,y1,x2,y2);
			}catch(Exception e){
				System.out.println("il n'y a pas de piece à ces coordonnées");
			};
		
	
			System.out.println(e1.toString());
			System.out.println("joueur blanc echec:"+e1.verifEchecs(j1));
			System.out.println("joueur noir echec:"+e1.verifEchecs(j2));
			
		}
		System.out.println("Echec et mat fin du jeu");
		/*e1.deplacement(6,0,4,0);
		e1.deplacement(4,0,3,0);
		e1.deplacement(3,0,2,0);
		e1.deplacement(2,0,1,1);
		e1.promotion(1, 1, 0, 0);
		System.out.println(e1.toString());*/
		sc.close();
	}
}

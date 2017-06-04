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
		while(e1.mat(j1,j2)==false && e1.pat(j1,j2)==false){
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
			
			System.out.println("mat:"+ e1.mat(j1, j2));
			System.out.println("pat:"+ e1.pat(j1, j2));
		}
	
		sc.close();
	}
}

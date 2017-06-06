import java.util.Scanner;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		Echequier e1=new Echequier(false);
		System.out.println("voulez vous charger la derniere partie ? (tapez 1 pour oui, tapez 2 pour non)");
		int m= sc.nextInt();
		if(m==1){

			e1.Load("toto.txt");
		}else{
		
		e1 = new Echequier(true);
		}
		
		Joueur j1=new Joueur(true,"ivan");
		Joueur j2=new Joueur(false,"alex");
		
		System.out.println(e1.toString());
	
		
		int x1;
		int y1;
		int x2;
		int y2;		
		while(e1.mat(j1,j2)==false && e1.pat(j1,j2)==false){
			System.out.println("coordonnée depart ou tapez 10 pour sauvegarder");
			x1= sc.nextInt();
			if(x1==10){
				e1.save("toto.txt");
				System.out.println("coordonnée depart");
				x1= sc.nextInt();
			}
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

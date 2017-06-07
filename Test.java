import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		Echequier e1=new Echequier(false);
		System.out.println("voulez vous charger la derniere partie ? (tapez 1 pour oui, tapez 2 pour non)");
		int m= sc.nextInt();
		if(m==1){
			e1.Load("sauvegarde.txt");
		}
		else{
			e1 = new Echequier(true);
		}
		
		Joueur j1=new Joueur(true,"ivan");
		Joueur j2=new Joueur(false,"alex");
		
		System.out.println(e1.toString());
	
		String c;
		int x1;
		int y1;
		int x2;
		int y2;		
		while(e1.mat(j1,j2)==false && e1.pat(j1,j2)==false){
			boolean choix = false;
			do{
				do{
					System.out.println("coordonnée depart ou tapez 'sauver' pour sauvegarder");
					c=sc.nextLine();
				}while(c.length()!=2 && !c.equals("sauver"));
				if(c.equals("sauver")){
					e1.save("sauvegarde.txt");
					System.out.println("a ete sauver");
				}
				else
					choix=true;
			}while((c.charAt(1)-49<0 || c.charAt(1)-49>7 || c.charAt(0)-65<0 || c.charAt(0)-65 >7) && !choix);
			
			y1=c.charAt(0)-65;
			x1=Math.abs(7-(c.charAt(1)-49));
			System.out.println(x1+" "+y1);
			
			System.out.println("coordonnée arrivé");
			
			do{
				do{
					c=sc.nextLine();
				}while(c.length()!=2);
			}while(c.charAt(1)-49<0 || c.charAt(1)-49>7 || c.charAt(0)-65<0 || c.charAt(0)-65 >7);
			
			y2=c.charAt(0)-65;
			x2=Math.abs(7-(c.charAt(1)-49));
			System.out.println(x2+" "+y2);
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

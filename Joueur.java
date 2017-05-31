
public class Joueur {
	private boolean couleurBlanc;
	private String nomJoueur;
	
	public Joueur(boolean c,String nom){
		this.couleurBlanc=c;
		this.nomJoueur=nom;
	}
	
	public boolean isCouleurBlanc() {
		return couleurBlanc;
	}
	public void setCouleurBlanc(boolean couleurBlanc) {
		this.couleurBlanc = couleurBlanc;
	}
	public String getNomJoueur() {
		return nomJoueur;
	}
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
}

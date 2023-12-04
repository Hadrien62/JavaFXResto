
public class Cuisinier extends Employe{
	private boolean travail;
	
	public Cuisinier(String nom, String pass, double salaire, int nbJour, boolean travail){
		super(nom, pass, "Cuisinier", salaire, nbJour);
		this.travail = travail;
	}
	
	public boolean getTravail() {
		return this.travail;
	}
	
	public void setTravail(boolean travail) {
		this.travail = travail;
	}
	
	public void preparerPlat(String plat) { //à changer avec la classe Plat
		
	}
}

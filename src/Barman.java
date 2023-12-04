
public class Barman extends Employe{
	private boolean prepare;
	
	public Barman(String nom, String pass,double salaire, int nbJour, boolean prepare) {
		super(nom, pass, "Barman", salaire, nbJour);
		this.prepare = prepare;
	}
	
	public void setPrepare(boolean prepare) {
		this.prepare = prepare;
	}
	
	public boolean getPrepare() {
		return this.prepare;
	}
	
	public void preparerBoisson(String boisson) { // à changer par la classe Produit
		
	}
}

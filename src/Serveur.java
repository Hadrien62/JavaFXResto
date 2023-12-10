import java.util.List;
public class Serveur extends Employe{
	private List<String> tableAttribuees; //à changer par la classe Table
	
	public Serveur(String nom, String pass, double salaire, int nbJour) {
		super(nom, pass, "Serveur", salaire, nbJour);
	}
	
	public List<String> getTable(){
		return this.tableAttribuees;
	}
	
	public void setTable(List<String> tablesAttribuees) {
		this.tableAttribuees = tablesAttribuees;
	}
}

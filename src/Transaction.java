import java.time.LocalDate;


public class Transaction extends Table{
	LocalDate Date_Transaction;
	
	public Transaction(int num_table,double addition) {
		super(num_table);
		this.Date_Transaction=LocalDate.now();
		this.addition=addition;
	}
	
	public void payment_entier() {
		
	}
	
	public void payment_split(int nbr_personne) {
		double par_personne=addition/nbr_personne;
	}
	
	public int Get_num_table() {
		return this.num_table;
	}
	
	public LocalDate Get_Date() {
		return this.Date_Transaction;
	}
	
	public double Get_montant() {
		return this.addition;
	}
	
	public void Set_num_table(int Nv_num_table) {
		this.num_table=Nv_num_table;
	}
	
	public void Set_montant(double Nv_montant) {
		this.addition=Nv_montant;
	}
}

public class Employe {
    private String username;
    private String password;
    private String role;
    private double salaire;
    private int nombreDeSoir;

    public Employe(String username, String password, String role, double salaire, int nombreDeSoir) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.salaire = salaire;
        this.nombreDeSoir = nombreDeSoir;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public double getSalaire() {
        return salaire;
    }
    public int getNombreDeSoir() {
        return nombreDeSoir;
    }
}
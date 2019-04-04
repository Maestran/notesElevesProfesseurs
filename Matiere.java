package notesElevesProfesseurs;

public class Matière {
    private String nom;
    public Matière(){};
    public Matière(String n){
        this.nom = n;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}

package notesElevesProfesseurs;

public class Matiere {
    private String nom;
    public Matiere(){};
    public Matiere(String n){
        this.nom = n;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}

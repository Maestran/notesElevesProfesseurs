package notesElevesProfesseurs;

public class Personne {
    private String nom = "";
    private String prenom = "";

    public Personne(){};

    @Override
    public String toString() {
        return "("+this.prenom+", "+this.nom+")";
    }

    public Personne(String n, String p){
        this.prenom = p;
        this.nom = n;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}

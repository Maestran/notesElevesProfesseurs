package notesElevesProfesseurs;
/*
    Classe pour les dates avec l'année, le mois et le jour
 */
public class Date {
    private int annee;
    private int mois;
    private int jour;

    public Date(){};

    public Date(int anneee, int mois, int jour){
        this.annee = anneee;
        this.mois = mois;
        this.jour = jour;
    }

    public int getAnnee() {
        return annee;
    }

    public int getJour() {
        return jour;
    }

    public int getMois() {
        return mois;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }
}


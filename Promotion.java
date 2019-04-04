package notesElevesProfesseurs;

import java.util.ArrayList;

public class Promotion {

        
    private String nom;
    private ArrayList<Eleve> eleves= new ArrayList<>();
    public Promotion(String nom)
    {
       this.nom = nom;
    }
    
    public void ajouterEleve(Eleve e) {
        eleves.add(e);
        e.setPromotion(this);
    }

    
    Eleve RechercherEleve(int identifiant)
    {
        for(Eleve e : eleves)
        {
           if(e.getId()==identifiant)
            return e;
        }
        return null;
    }
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the eleves
     */
    public ArrayList<Eleve> getEleves() {
        return eleves;
    }
    

    
}

package notesElevesProfesseurs;

import java.util.ArrayList;
import java.util.HashSet;

public class Matiere {
    private String nom;
    public static HashSet<Matiere> listeMatieres = new HashSet<>();
    
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
    
    public static Matiere trouverMatiere(String nomMatiere)
    {
        for(Matiere m : listeMatieres)
        {
           if( m.getNom().equals(nomMatiere))
               return m;
        }
        return null;
    }


}
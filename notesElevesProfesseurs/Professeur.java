package notesElevesProfesseurs;

import java.util.ArrayList;
import java.util.HashSet;

public class Professeur extends Personne 
{

    private static HashSet<Professeur> listeProfesseurs = new HashSet<>();
    
    public Professeur() {}
    public Professeur(String prenom, String nom) 
    {
        setPrenom(prenom);
        setNom(nom);
    }

     Eleve Rechercher(Promotion promo, int identifiantEleve)
     {
       for( Eleve e :  promo.getEleves()) { if(e.getId()==identifiantEleve) return e;}                   
       return null;
     }
     
     public void setNote(Promotion promo, int identifiantEleve, float note, int indexNote)
     {
         Eleve e = Rechercher(promo, identifiantEleve);
         if(e==null)throw new IllegalStateException();
         System.out.println("Modification de la note de l'élève: " + e  );
         if( indexNote >  e.getEvaluations().size()-1 && indexNote>0) // Si l'évaluation existe
             e.getEvaluations().get(indexNote).setNote(note);
         else  // Si la note n'existe pas, on la créee
             e.getEvaluations().add(new Evaluation(note,null, e, null));
             
         System.out.println("Echec, note non trouvée à l'index :" +indexNote );
     }
     
     /**
     * @return the listeProfesseurs
     */
    public static HashSet<Professeur> getListeProfesseurs() {
        return listeProfesseurs;
    }
     
    public static Professeur trouverProfesseur(String nom, String prenom)
    {
        System.out.println("Recherche : " + prenom + " " + nom);
        HashSet<Professeur> profs = getListeProfesseurs();
        for(Professeur p : profs )
        {
            if(p.getNom().equals(nom) && p.getPrenom().equals(prenom))
            return p;
        }
        return null;
    }
}

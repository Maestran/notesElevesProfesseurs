package notesElevesProfesseurs;

public class Professeur extends Personne 
{
     Eleve RechercherEleve(Promotion promo, int identifiant)
     {
       for( Eleve e :  promo.getEleves()) { if(e.getId()==identifiant) return e;}                   
       return null;
     }
     
     
     void ModifierNoteEleve(Eleve e, float note, Matiere matiere)
     {
         System.out.println("Modification de la note de l'élève: " + e  );
         for( Evaluation eval  : e.getEvaluations())
         {
             if(eval.getMat()== matiere)
             {
               eval.setNote(note);
               System.out.println("Note modifiée  ! ");
               return;
             }
         }
         System.out.println("Echec, Matière non trouvée :" + matiere.getNom());
              
     }
}

package notesElevesProfesseurs;

public class Professeur extends Personne 
{
     Eleve Rechercher(Promotion promo, int identifiantEleve)
     {
       for( Eleve e :  promo.getEleves()) { if(e.getId()==identifiantEleve) return e;}                   
       return null;
     }
     
     
     void setNote(Promotion promo, int identifiantEleve, float note, int indexNote)
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
}

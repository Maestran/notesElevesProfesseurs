package noteselevesprofesseurs.graphique;


import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import notesElevesProfesseurs.*;

public class graphiqueTest {

    public static void main(String[] args) {
     
          try {
            CSV_Loader.start("Data\\élèves.csv", "Data\\Résultats élèves.csv");
            
            
            for(Promotion a : Promotion.getListePromos())
            {
                camembertAppreciation b = new camembertAppreciation(a);
            }
            
            camembertNombreEleveParPromo c = new camembertNombreEleveParPromo(Promotion.getListePromos());
            
            
           
            diagrammeBatonNoteMatiere d = new diagrammeBatonNoteMatiere(Promotion.rechercherElevePartout(2).getEvaluations().get(1).getMat());
            
            diagrammeBatonResulatEleve res = new diagrammeBatonResulatEleve(Promotion.rechercherElevePartout(1));
            diagrammeBatonMoyennePromotion prom = new diagrammeBatonMoyennePromotion(Promotion.getListePromos());
            diagrammeBatonStatPromotion prom2 = new diagrammeBatonStatPromotion(Promotion.getListePromos());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}           
    
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteselevesprofesseurs.graphique;


/**
 *
 * @author Tristan
 */


import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import notesElevesProfesseurs.*;

public class graphique {

    public static void main(String[] args) {
     
          try {
            CSV_Loader.start("Data\\eleves.csv", "Data\\Resultats_eleves.csv");
            
            /*
            for(Promotion a : Promotion.getListePromos())
            {
                camembertAppreciation b = new camembertAppreciation(a);
            }
            
            camembertNombreEleveParPromo c = new camembertNombreEleveParPromo(Promotion.getListePromos());
            
            */
           
            //diagrammeBatonNoteMatiere a = new diagrammeBatonNoteMatiere(Promotion.rechercherElevePartout(2).getEvaluations().get(0).getMat());
            
            diagrammeBatonResulatEleve res = new diagrammeBatonResulatEleve(Promotion.rechercherElevePartout(1));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}           
    
    

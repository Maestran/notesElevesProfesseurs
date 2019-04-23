
package notesElevesProfesseurs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
       
        try {
            CSV_Loader.chargerFichierEleves("Data\\élèves.csv");   
         
            CSV_Loader.chargerEvaluations("Data\\Résultats élèves.csv");
            
               for(Promotion p : Promotion.getListePromos())
                p.listerEleves(true);
     
            Menu m = new Menu();
            m.afficherAccueil();
            
            //System.out.println(e2.getCorrecteurs());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}

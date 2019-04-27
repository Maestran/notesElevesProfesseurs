
package notesElevesProfesseurs;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
       
        try {
            CSV_Loader.start("Data\\élèves.csv", "Data\\Résultats élèves.csv");

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

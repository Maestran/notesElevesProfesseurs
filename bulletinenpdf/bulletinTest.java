/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteselevesprofesseurs.bulletinenpdf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import notesElevesProfesseurs.*;


/*
Cette classe est une classe de test pour la V3
*/
public class bulletinTest {

    public static void main(String[] args) { 
       
 
        try {
            CSV_Loader.chargerFichierEleves("Data\\eleves.csv");   
         
            CSV_Loader.chargerEvaluations("Data\\Résultats_eleves.csv");
            
            Eleve a = Promotion.rechercherElevePartout(1);
            Bulletin bul = new Bulletin(a);
            bul.creerBulletin();
                
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
             
    }
}
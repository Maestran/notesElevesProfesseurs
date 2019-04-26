/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletinenpdf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import notesElevesProfesseurs.CSV_Loader;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Menu;
import notesElevesProfesseurs.Promotion;

/**
 *
 * @author Tristan
 */
public class Main {

    public static void main(String[] args) {
       
 
        try {
            CSV_Loader.chargerFichierEleves("Data\\élèves.csv");   
         
            CSV_Loader.chargerEvaluations("Data\\Résultats élèves.csv");
            
            Eleve a = Promotion.rechercherElevePartout(1);
            Bulletin bul = new Bulletin(a);
            bul.creerBulletin();
            
            for(Promotion p : Promotion.getListePromos())
            p.listerEleves(true);
     
            Menu m = new Menu();
            m.afficherAccueil();
            
            //System.out.println(e2.getCorrecteurs());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
             
    }
}
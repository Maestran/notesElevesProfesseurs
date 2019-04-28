/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs.GUI;

import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
import notesElevesProfesseurs.Promotion;
import notesElevesProfesseurs.TriEleves;

/**
 *
 * @author franc
 */
public class Globals {
    
    // Ce fichier représente les variables globales
    public static  Promotion promoActuelle = null;
    public static Eleve eleveSelectionne = null;
    public static boolean triCroissant = true;
    public static TriEleves modeTriParDefaut = TriEleves.identifiant;
    public static Evaluation evaluationSelectionnee = null;
}

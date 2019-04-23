/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Collections;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Professeur;
import notesElevesProfesseurs.Promotion;

/**
 *
 * @author franc
 */
public class TestEleves 
{
    // Question 10
    public static void main(String args[])
    {
        // Création des élèves
        Eleve lea = new Eleve("Lea","Maratre",2000,6,20);
        Eleve francis = new Eleve("François","Gillioen",1998,3,28);
        Eleve tristan = new Eleve("Tristan","Gorlin",1998,2,2);
        Eleve nico = new Eleve("Guibert","Nicolas",1998,4,2);
        
        
        // Rangement des élèves dans leurs promotions
        
        Promotion promo = new Promotion("2021");
        Promotion promo2 = new Promotion("2023");
        
        promo.ajouterEleve(francis);
        promo.ajouterEleve(tristan);
        promo.ajouterEleve(nico);
        promo2.ajouterEleve(lea);

        // Création des correcteurs et matières
        Professeur p = new Professeur("Patrick","Teller");
        Professeur p2 = new Professeur("Camille","Kerouani");
        Professeur p3 = new Professeur("Historien","");
        Professeur p4 = new Professeur("Victor","Hugo");
        
        Matiere math = new Matiere("maths");
        Matiere francais = new Matiere("français");
        Matiere histoire = new Matiere("histoire");
        Matiere geo = new Matiere("geographie");
        
        
        // Mettre des notes aux élèves et attribution des correcteurs
        Evaluation eval = new Evaluation(9,math,francis,p);
        Evaluation eval2 = new Evaluation(11,math,francis,p);
        francis.add(eval);
        francis.add(eval2);
        
        Evaluation eval3 = new Evaluation(16,math,tristan,p);
        Evaluation eval4 = new Evaluation(13,francais,tristan,p4);
        tristan.add(eval3);
        tristan.add(eval4);
        Evaluation eval5 = new Evaluation(16,math,tristan,p);
        Evaluation eval6 = new Evaluation(13,histoire,tristan,p3);
        Evaluation eval7 = new Evaluation(13,histoire,tristan,p3);
        Evaluation eval8 = new Evaluation(10,geo,tristan,p2);
        Evaluation eval9 = new Evaluation(14,geo,tristan,p2);
        tristan.add(eval5);
        tristan.add(eval6);
        tristan.add(eval7);
        tristan.add(eval8);
        tristan.add(eval9);
        
        Evaluation eval10 = new Evaluation(20,math,nico,p);
        Evaluation eval11 = new Evaluation(19,histoire,nico,p3);
        nico.add(eval10);
        nico.add(eval11);
        
        // Enregistrement des promotions par l'école
        Promotion.getListePromos().add(promo);
        Promotion.getListePromos().add(promo2);

        
       //  Afficher un élève avec son nom, sa promotion, ses correcteurs, ses notes, leur moyenne et leur médiane
       Eleve nicolas = promo.getEleves().get(2); 
        System.out.println(nicolas);
  
       //  Afficher tous les élèves d'une promotion
       promo.listerEleves(false);
       promo2.listerEleves(false);
       
       //  Rechercher un élève par son identifiant puis l'afficher (pas d’affichage dans la recherche!)
       int searchId = 4;
       Eleve eleveTrouve = Promotion.rechercherElevePartout(searchId);
       if(eleveTrouve!=null) System.out.println(eleveTrouve); else System.out.println("Elève non trouvé");
      
       //  Classer les élèves par ordre croissant puis décroissant de leur moyenne et de leur médiane
       // Afficher tous les élèves d'une promotion selon les classements effectués.
       for(Promotion promotion : Promotion.getListePromos())
       {
           System.out.println("------------------- Promotion " + promotion.getNom() + " -------------------" );
           System.out.println("Tri croissant moyenne");
          promotion.triMoyenne(true);
         promotion.listerEleves(true);
                 
           System.out.println("Tri décroissant moyenne");
            promotion.triMoyenne(false);
            promotion.listerEleves(true);
                      
          System.out.println("Tri croissant mediane");
          promotion.triMediane(true);
          promotion.listerEleves(true);
                    
           System.out.println("Tri décroissant mediane");
           promotion.triMediane(false);
           promotion.listerEleves(true);
       }
        System.out.println("Terminé!");
       
       
       
       
    }
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author franc
 */
public class CSV_Loader 
{    
    //Charge les élèves dans leurs promotions respectives, indiquées dans le fichier en format CSV
    public static void chargerFichierEleves(String chemin) throws FileNotFoundException
    {
        if(!new File(chemin).exists())
        {
            System.out.println("Le fichier demandé n'existe pas à "+ chemin);
        }
        else 
        {
            Scanner scanner = new Scanner(new File(chemin));
            boolean firstLineDone = false;
            while(scanner.hasNext())
            {
                if(!firstLineDone)
                {
                    // La première ligne est purement décorative donc on la saute
                   firstLineDone = true;   
                   scanner.nextLine();
                }
                else
                {
                       String[] lineStrings = scanner.nextLine().split(";");
                    Eleve e = new Eleve();
                    e.setNom(lineStrings[1]);
                    e.setPrenom(lineStrings[2]);
                    
                     if(lineStrings[4] != null)
                    {
                        String[] dateString = lineStrings[4].split("/");
                        e.setDateNaissance(new Date(Integer.parseInt(dateString[2]),Integer.parseInt(dateString[1]), Integer.parseInt(dateString[0])));
                    }
                     
                    Promotion recherchePromo = Promotion.trouverPromotion(lineStrings[3]);
                    if( recherchePromo !=null)
                        Promotion.trouverPromotion(lineStrings[3]).ajouterEleve(e);
                    else 
                    {
                        Promotion nouvellePromo = new Promotion(lineStrings[3]);
                        nouvellePromo.ajouterEleve(e);
                        Promotion.getListePromos().add(nouvellePromo);
                    }
                   
                }
            }
            System.out.println("\nTerminé!");
        }
    }
    
    final static int COL_IDENTIFIANT = 0;
    final static int COL_NOTES = 1;    
    final static int COL_MATIERE = 2;
    final static int COL_CORRECTEUR = 3;
    final static int COL_TYPE = 4;
    
    
    // Nécéssite au préalable d'avoir chargé les élèves
    public static void chargerEvaluations(String chemin) throws FileNotFoundException
    {
         if(!new File(chemin).exists())
        {
            System.out.println("Le fichier demandé n'existe pas");
        }
        else 
        {
            Scanner scanner = new Scanner(new File(chemin));
            ArrayList<Evaluation> evals = new ArrayList<>();
            Professeur nouveauProf = null;
            boolean firstLineChecked= false;
            while(scanner.hasNext())
            {
                // Idée : Possibilité de mettre en place facilement une gestion des élèves peu importe la position de la colonne via la lecture de la premiere ligne
                if(!firstLineChecked)
                {
                    firstLineChecked=true;
                    scanner.nextLine();
                    continue;
                }
                nouveauProf = null;
                String line = scanner.nextLine();
                System.out.println("Traitement : " + line);
                String[] lineStrings = line.split(";");
                    
                if(Matiere.trouverMatiere(lineStrings[COL_MATIERE]) == null && !"".equals(lineStrings[COL_MATIERE].trim())) // Si la matière cherchée n'est pas trouvée, on la créee
                {
                    Matiere.listeMatieres.add(new Matiere(lineStrings[COL_MATIERE]));
                }
                
                if(lineStrings[COL_CORRECTEUR] != null && !"".equals(lineStrings[COL_CORRECTEUR].trim()) )
                {
                    String[] prenomNom = lineStrings[COL_CORRECTEUR].split(" ");
                     nouveauProf = new Professeur(prenomNom[0],prenomNom[1]);
                    if(Professeur.trouverProfesseur(prenomNom[1], prenomNom[0]) == null)
                    Professeur.getListeProfesseurs().add(nouveauProf);
                }

            
                float note = Float.parseFloat(lineStrings[COL_NOTES]);
                Matiere mat = Matiere.trouverMatiere(lineStrings[COL_MATIERE]);
                Eleve eleveNote = Promotion.rechercherElevePartout( Integer.parseInt(lineStrings[COL_IDENTIFIANT]));
                Evaluation e = new Evaluation(note,mat,eleveNote ,nouveauProf);
                
                if(lineStrings[COL_TYPE]!=null && !"".equals(lineStrings[COL_TYPE].trim())) // Type de note (optionnel)
                {
                    e.setEvalType(lineStrings[COL_TYPE]);
                }
                evals.add(e);
            }
            attribuerEvaluationsAuxEleves(evals);
            System.out.println("Terminé!");
        }
    }
    
    private static void attribuerEvaluationsAuxEleves(ArrayList<Evaluation> evals)
    {
        for(Promotion p : Promotion.getListePromos())
        {
            for(Eleve e: p.getEleves())
            {
                for(Evaluation eval : evals)
                {
                    if(eval.getEleve() == e && !e.getEvaluations().contains(eval))
                        e.getEvaluations().add(eval);
                }
            }
        }
    }
    
    
    
    
}

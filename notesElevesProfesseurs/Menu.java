
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs;

import java.io.IOException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author franc
 */
public final class Menu {
    Promotion promoActuelle = Promotion.getListePromos().get(0);
    TriEleves triParDefaut = TriEleves.moyenne;
    boolean ordreCroissantParDefaut = false; // Si à false, l'ordre affiché est décroissant au lancement du programme
    Eleve eleveSelectionne = null;
    Evaluation evaluationSelectionnee = null;
    
    public Menu()
    {
        
    }
    
    public void quitter()
    {
        System.out.println("Application terminée!");
        System.exit(0);
    }
    
    
    public void afficherAccueil()
    {
       
        System.out.println(System.lineSeparator()+"   _____             _    _                              _            \n" +
"  / ____|           | |  (_)                            (_)           \n" +
" | |  __   ___  ___ | |_  _   ___   _ __   _ __    __ _  _  _ __  ___ \n" +
" | | |_ | / _ \\/ __|| __|| | / _ \\ | '_ \\ | '_ \\  / _` || || '__|/ _ \\\n" +
" | |__| ||  __/\\__ \\| |_ | || (_) || | | || | | || (_| || || |  |  __/\n" +
"  \\_____| \\___||___/ \\__||_| \\___/ |_| |_||_| |_| \\__,_||_||_|   \\___|\n" +
" |  __ \\ ( )     | |                                                  \n" +
" | |  | ||/  ___ | |  ___ __   __ ___  ___                            \n" +
" | |  | |   / _ \\| | / _ \\\\ \\ / // _ \\/ __|                           \n" +
" | |__| |  |  __/| ||  __/ \\ V /|  __/\\__ \\                           \n" +
" |_____/    \\___||_| \\___|  \\_/  \\___||___/   ");
        System.out.println();
        System.out.println();
        System.out.println();
       System.out.println("Que souhaitez-vous faire ? \n ► Chercher un élève en particulier (1) \n ► Lister les élèves d'une promotion (2) \n ► Lister les professeurs (3) \n ► Afficher l'aide (4) \n ► Quitter (5)");
       int choixMenu = secureIntInput();
       
       switch(choixMenu)
       {
           case 1 : chercherEleveParticulier(); break;
           case 2 : consulterEleves(triParDefaut, ordreCroissantParDefaut); break;
           case 3 : consulterProfs(); break;
           case 4 : afficherAide(); break;
           case 5 : quitter(); break;
       }
       if(choixMenu>4 || choixMenu < 1) afficherAccueil();
    }
    
    private void choisirModeDeTri()
    {
        System.out.println("1 ) identifiant"); 
        System.out.println("2 ) mediane"); 
        System.out.println("3 ) moyenne"); 
        System.out.println("4 ) nom"); 
        System.out.println("5 ) prenom"); 
        
     int choix = -1;
    do{
             choix = secureIntInput();
    }while(choix > 5 && choix < 1);
    
    switch(choix)
    {
        case 1: triParDefaut = TriEleves.identifiant ;break;
        case 2: triParDefaut = TriEleves.mediane ;break;
        case 3: triParDefaut = TriEleves.moyenne ;break;
        case 4: triParDefaut = TriEleves.nom ;break;
        case 5: triParDefaut = TriEleves.prenom ;break;
        default : choisirModeDeTri();
    }
    
    choix = -1;
    do{
               System.out.println("Dans quel ordre souhaitez vous trier ?"+System.lineSeparator()+" ► Croissant (1) "+System.lineSeparator()+" ► Décroissant (2)");
               choix =  secureIntInput();
    }while(choix !=2 && choix != 1);
    
    boolean choixCroissance;
    if(choix == 2)
        choixCroissance = false;
    else 
        choixCroissance = true;
    ordreCroissantParDefaut = choixCroissance;
    consulterEleves(triParDefaut, choixCroissance);
    
    }
    
    Scanner choiceScanner ;
    
    //Saisie sécurisée d'un entier
    int secureIntInput()
    {
                choiceScanner = new Scanner(System.in);
        int choice = -1;
            try
            {
                choice = choiceScanner.nextInt();
            }
            catch( InputMismatchException ex)
            {
                System.out.println("(!) Veuillez entrer un nombre");;
                secureIntInput(); 
            }
         return choice;
    }
    
    void chercherEleveParticulier()
    {
        System.out.println("------------------ Recherche d'un élève -----------------");
        System.out.println("Entrez l'identifiant de l'élève ou -1 pour revenir au Menu");
                int identifiant = secureIntInput();
                if(identifiant == -1) afficherAccueil();
                Eleve e =  Promotion.rechercherElevePartout(identifiant);
                if(e==null) chercherEleveParticulier();
                else 
                {
                    System.out.println("Trouvé !"+ System.lineSeparator());
                    System.out.println(e);
                }
                eleveSelectionne = e;
                proposerOptionsEleveSeletionne();
    }
    
    private void proposerOptionsEleveSeletionne()
    {
        if(eleveSelectionne!=null)
        {
          System.out.println("Nom : "+ eleveSelectionne.getNom().toUpperCase() + " Prenom : " + eleveSelectionne.getPrenom());

          System.out.println(String.format("Que souhaitez-vous faire ? %1$s%1$s ► Editer l'élève (1) %1$s ► Modifier ses notes (2) %1$s ► Supprimer l'élève (3) %1$s ► Retour (4)",System.lineSeparator()));   
          int choixPostAffichage = secureIntInput();
        switch(choixPostAffichage)
        {
            case 1 : // changer de promo
                editerEleve(eleveSelectionne);
                break;
            case 2 : 
                proposerModificationsNotes(eleveSelectionne);
                break;
            case 3 :
                supprimerEleve(eleveSelectionne);
                break;
            case 4 :
                afficherAccueil();
                break;
            default: proposerOptionsEleveSeletionne() ;
        }
        }
        else 
        {
                        System.out.println("Aucun élève n'est selectionné, retour au menu");
                        afficherAccueil();
        }

    }
    
    private void proposerOptionsConsultationEleves()
    {
        System.out.println(String.format("Que souhaitez-vous faire ? %1$s ► Changer de promotion (1) ► Changer le mode de Tri (2) ► Ajouter un nouvel élève (3) ► Retour (4)",System.lineSeparator()));   
        int choixPostAffichage = secureIntInput();
        switch(choixPostAffichage)
        {
            case 1 : // changer de promo
                promoActuelle = choisirPromo();
                consulterEleves(triParDefaut, ordreCroissantParDefaut);
                break;
            case 2 : 
                choisirModeDeTri();
                break;
            case 3 :
                creerEleve(promoActuelle);
            case 4 :
                afficherAccueil();
                break;
            default: proposerOptionsConsultationEleves() ;
                    
        }
    }

    public void consulterEleves(TriEleves modeDeTri, boolean croissant)
    {
    
        promoActuelle = choisirPromo();
        
        if(modeDeTri==TriEleves.moyenne)
            promoActuelle.triMoyenne(croissant);
        else if (modeDeTri==TriEleves.mediane)
            promoActuelle.triMediane(croissant);
        else if (modeDeTri==TriEleves.identifiant)
            promoActuelle.triId(croissant);
        else if (modeDeTri==TriEleves.nom)
            promoActuelle.triNom(croissant);
        else if (modeDeTri==TriEleves.prenom)
            promoActuelle.triPrenom(croissant);
        
        promoActuelle.listerEleves(croissant);
        proposerOptionsConsultationEleves();
        }

    private Promotion choisirPromo() {
        System.out.println("Choississez une promotion :"+System.lineSeparator()+System.lineSeparator());
        for(int i = 0;  i < Promotion.getListePromos().size();i++)
        {
            System.out.println( (i+1)+ " ) " +Promotion.getListePromos().get(i).getNom()+" | "+ Promotion.getListePromos().get(i).getEleves().size() + " élèves");
        }
        
        Promotion p = null;
        do{
            System.out.println("Veuillez entrer le numéro d'une promotion affichée");
          p = Promotion.getListePromos().get(secureIntInput()-1);
        }while(p == null);
        return p;
    }
    
    public void consulterProfs()
    {
        HashSet<Professeur> profs =  Professeur.getListeProfesseurs();
        System.out.println("--------- Affichage des professeurs ---------");
        System.out.println(System.lineSeparator() + " " + profs.size() + " professeurs trouvés");
        for(Professeur p : profs)
        {
            System.out.println(p);
        }
        System.out.println(System.lineSeparator()+"► Retour (Touche Entrée)");
        try {
            System.in.read();
            afficherAccueil();
        } catch (IOException ex) {
            consulterProfs();
        }
    }
    
    public void afficherAide()
    {
        
    }

    private void editerEleve(Eleve eleveSelectionne) {
        System.out.println("---------------  Edition de l'élève n°" + eleveSelectionne.getId() +" | "+  eleveSelectionne.getNom() + " " +eleveSelectionne.getPrenom()+ " ------------------");
        System.out.println("Que souhaitez vous éditer ?");
        System.out.println("►\tNom (1)");
        System.out.println("►\tPrenom (2)");
        System.out.println("►\tDate de naissance (3)");
        System.out.println("►\tPromotion (4)");
        System.out.println("►\tAnnuler (5)");
        
        int choix = secureIntInput();
        Scanner textScanner = new Scanner(System.in);
        switch (choix) {
            case 1:
                System.out.println("Entrez le nouveau  nom de l'élève : ");
                String nom = textScanner.nextLine();
                eleveSelectionne.setNom(nom);
                CSV_Loader.majEleve(eleveSelectionne, CSV_Loader.ELEVES_PATH);
                System.out.println("Nom changé !");
                editerEleve(eleveSelectionne);
                
               break;
            case 2:
                System.out.println("Entrez le nouveau prenom de l'élève : ");
                String prenom = textScanner.nextLine();
                eleveSelectionne.setPrenom(prenom);
                CSV_Loader.majEleve(eleveSelectionne, CSV_Loader.ELEVES_PATH);
                System.out.println("Prenom changé !");
                editerEleve(eleveSelectionne);
                break;
            case 3:
                String nomComplet = eleveSelectionne.getPrenom() + " " + eleveSelectionne.getNom();
                 System.out.println("Etape 1 : Entrez le jour de naissance de "+ nomComplet);
                 int day = secureIntInput();
                 System.out.println("Etape 2 : Entrez le mois de naissance de " + nomComplet);
                 int month = secureIntInput();
                 System.out.println("Etape 3 : Entrez l'année de naissance de " + nomComplet);
                 int year = secureIntInput();
                 Date d = new Date(day,month,year);
                 eleveSelectionne.setDateNaissance(d);
                 CSV_Loader.majEleve(eleveSelectionne, CSV_Loader.ELEVES_PATH);
                 System.out.println("Date de naissance changée !");
                 editerEleve(eleveSelectionne);
                break;
            case 4:
                System.out.println("La promotion pour cet élève est " + eleveSelectionne.getPromotion().getNom());
                if(promoActuelle!=null)
                    promoActuelle.getEleves().remove(eleveSelectionne);
                Promotion p = choisirPromo();
                eleveSelectionne.setPromotion(p);
                promoActuelle = p;
                promoActuelle.ajouterEleve(eleveSelectionne);
                CSV_Loader.majEleve(eleveSelectionne, CSV_Loader.ELEVES_PATH);
                editerEleve(eleveSelectionne);
                System.out.println("La promotion de l'élève est désormais la promotion : " + p.getNom() + "( "+ p.getEleves().size() +" élèves )");
                break;
            case 5:
                chercherEleveParticulier();break;
            default:
                System.out.println("Choix incorrect");
                editerEleve(eleveSelectionne);
        }
    }

    private void supprimerEleve(Eleve eleveSelectionne) 
    {
        System.out.println("Suppression en cours de " + eleveSelectionne.getNom() +  " " + eleveSelectionne.getPrenom() + " ...");
        promoActuelle.getEleves().remove(eleveSelectionne);
        CSV_Loader.supprimerEleveDansFichier(eleveSelectionne, CSV_Loader.ELEVES_PATH);
        System.out.println("Terminé !");
        
        this.eleveSelectionne = null;
        
        // On reprend la recherche
        chercherEleveParticulier();
    }
    
    private void AjouterEvaluations(Eleve eleveSelectionne)
    {
        System.out.println("--------------- Ajout d'évaluations pour l'élève " + eleveSelectionne.getNom()  + " " + eleveSelectionne.getPrenom() + "-----------------");
    }

    private Evaluation choisirEvaluation(Eleve e)
    {
          System.out.println("Choississez une Evaluation :"+System.lineSeparator()+System.lineSeparator());
        for(int i = 0;  i < e.getEvaluations().size();i++)
        {
            System.out.println(" Evaluation n°" + i +") " + e.getEvaluations().get(i));
        }
        int choixEvaluation = secureIntInput();
        if(choixEvaluation > e.getEvaluations().size() || choixEvaluation < 0) choisirEvaluation(e);
        Evaluation eval = e.getEvaluations().get(choixEvaluation);
        if(eval == null)
        {
          System.out.println("(!) Evaluation non trouvée");
          choisirEvaluation(e);
        }
        return eval;
    }
    
    private void creerEleve(Promotion promoActuelle) 
    {
        System.out.println("----------------Création d'un élève pour la promotion '"+promoActuelle.getNom()+"'-----------------" );
        Scanner s = new Scanner(System.in);
        System.out.println("Etape 1 : Entrez le nom de l'élève");
        String nom = s.nextLine();
        System.out.println("Etape 2 : Entrez le prenom de l'élève");
        String prenom = s.nextLine();
        System.out.println("Etape 3a : Entrez le jour de naissance de l'élève");
        int day = secureIntInput();
        System.out.println("Etape 3b : Entrez le mois de naissance de l'élève");
        int month = secureIntInput();
        System.out.println("Etape 3c : Entrez l'année de naissance de l'élève");
        int year = secureIntInput();
        Date d = new Date(day,month,year);
        Eleve e = new Eleve(prenom, nom, d);
        
        promoActuelle.ajouterEleve(e);
        CSV_Loader.ajouterEleveDansFichier(e, CSV_Loader.ELEVES_PATH);
        
        //On reprend l'affichage des fonctions
        consulterEleves(triParDefaut, ordreCroissantParDefaut);
    }

    private void proposerModificationsNotes(Eleve eleve) 
    {
                System.out.println("---------------  Edition de l'élève n°" + eleve.getId() +" | "+  eleve.getNom() + " " +eleve.getPrenom()+ " ------------------");
        
        if(eleve.getEvaluations()==null) 
        {
            System.out.println("L'élève ne possède pas d'évaluations");
            return;
        }
        for(Evaluation eval : eleve.getEvaluations().toArray(new Evaluation[eleve.getEvaluations().size()])) //Obligatoire pour ne pas avoir d'erreurs de modifications concurrentes
        {
            System.out.println(eval);
            System.out.println("-----------------------------------------------------");
        }
        
        System.out.println("Que souhaitez vous faire avec vos évaluations? ");
        System.out.println("►\t Modifier une évaluation(1)");
        System.out.println("►\t Supprimer une évaluation (2)");
        System.out.println("►\t Ajouter une évaluation (3)");
        System.out.println("►\t Retour (4)");
        
        int choixEnsembleNotes = secureIntInput();
        
        switch(choixEnsembleNotes)
        {
            
            case 1 :  // MODIFIER UNE EVALUATION
                System.out.println("Modification d'une eval en particulier");
            proposerModificationEvalParticuliere(evaluationSelectionnee, eleve);
            break;
            case 2: // SUPPRIMER UNE EVALUATION
                evaluationSelectionnee = choisirEvaluation(eleve);
                CSV_Loader.supprimerEvaluationDansFichier(evaluationSelectionnee, CSV_Loader.EVALUATIONS_PATH);
                eleve.getEvaluations().remove(evaluationSelectionnee);
                this.eleveSelectionne = null;
                proposerModificationsNotes(eleve);
                break;
            case 3 : // AJOUTER UNE EVALUATION
                System.out.println("ETAPE 1 : Entrez la note");
                int note = secureIntInput();
                
                Scanner s = new Scanner(System.in);
                System.out.println("ETAPE 2 : Entrez le nom de la matière");
                String nomMatiere = s.nextLine();
                Matiere matiere = Matiere.trouverMatiere(nomMatiere,eleve.getPromotion().getNom());
                if(matiere==null){
                    System.out.println("Matière non trouvée, création...");
                    matiere = new Matiere(nomMatiere,eleve.getPromotion().getNom());
                    Matiere.listeMatieres.add(matiere);
                    System.out.println("OK");
                    
                }
                
                
                System.out.println("ETAPE 3 : Entrez le nom prenom du correcteur");
                System.out.println(System.lineSeparator()+"Voici la liste des professeurs");
                for(Professeur p : Professeur.getListeProfesseurs())
                    System.out.println(p);
                String nomProf = s.nextLine();
                String[] nomPrenom = nomProf.split(" ");
                Professeur prof = Professeur.trouverProfesseur(nomPrenom[0], nomPrenom[1]);
                if(prof == null)
                {
                    System.out.println("Pas de prof trouvé, création...");
                 prof = new Professeur(nomPrenom[0], nomPrenom[1]);
                 Professeur.getListeProfesseurs().add(prof);
                    System.out.println("OK");
                }
             
                System.out.println("ETAPE 4 : Entrez le type d'évaluation (oral, CE, partiel...)");
                String type = s.nextLine();
               
                Evaluation nouvelleEval = new Evaluation(note, matiere, eleve,prof);   
                nouvelleEval.setEvalType(type);
                eleve.getEvaluations().add(nouvelleEval);
                CSV_Loader.ajouterEvaluationDansFichier(nouvelleEval, CSV_Loader.EVALUATIONS_PATH);
                proposerModificationsNotes(eleve);
                break;
            case 4 :  proposerOptionsEleveSeletionne(); break;
                
                
        }
     
        
        

    }

    private void proposerModificationEvalParticuliere(Evaluation evaluationSelectionnee, Eleve e) 
    {
            evaluationSelectionnee = choisirEvaluation(e);
            System.out.println("Evaluation : ");
            System.out.println(evaluationSelectionnee);
            System.out.println("Que souhaitez vous modifier ?");
            System.out.println("►\tLa note (1)");
            System.out.println("►\tLe correcteur de la note (2)");
            System.out.println("►\tLe type de note (3)");
            System.out.println("►\tLa matière de la note (4)");
            System.out.println("►\tRetour (5)");
            Scanner textScanner = new Scanner(System.in);
            int choixModif = secureIntInput();
            switch(choixModif)
            {
                case 1: // MODIFIER NOTE
                    System.out.println(String.format("Entrez la nouvelle note (note actuelle : %f/20)",evaluationSelectionnee.getNote()));
                     float nouvelleNote = 10;
                    try
                    {
                        nouvelleNote  = textScanner.nextFloat();
                    }
                    catch(Exception ex)
                    {
                        System.out.println("(!) Erreur, veuillez entrer un nombre à virgule flottante ");
                        proposerModificationEvalParticuliere(evaluationSelectionnee, e);
                    }                    
                    for(Evaluation eval : e.getEvaluations())
                    {
                        if(eval == evaluationSelectionnee)
                            eval.setNote(nouvelleNote);
                    }
                    CSV_Loader.majEvaluations(evaluationSelectionnee, CSV_Loader.EVALUATIONS_PATH);
                    System.out.println("Réussite!");
                    proposerModificationEvalParticuliere(evaluationSelectionnee, e);
                    break;
                case 2: // MODIFIER CORRECTEUR
                    System.out.println(String.format("Entrez le correcteur (correcteur actuel : %s)",evaluationSelectionnee.getProf().getPrenom() + " "+ evaluationSelectionnee.getProf().getNom()));
                      System.out.println(System.lineSeparator()+"Voici la liste des professeurs");
                        for(Professeur p : Professeur.getListeProfesseurs())
                            System.out.println(p);
                        String nomProf = textScanner.nextLine();
                        String[] nomPrenom = nomProf.split(" ");
                        Professeur prof = Professeur.trouverProfesseur(nomPrenom[0], nomPrenom[1]);
                        if(prof == null) // SI LE PROF N'EXISTE PAS, ON L'AJOUTE
                        {
                            System.out.println("Pas de prof trouvé, création...");
                         prof = new Professeur(nomPrenom[0], nomPrenom[1]);
                         Professeur.getListeProfesseurs().add(prof);
                            System.out.println("OK");
                        }
                    for(Evaluation eval : eleveSelectionne.getEvaluations())
                        if(eval == evaluationSelectionnee)
                        {
                          eval.setProf(prof);
                        }
                     
                    break;
                case 3: // MODIFIER Type de note
                    System.out.println(String.format("Entrez le type de note (type actuel :  %s)",evaluationSelectionnee.getEvalType()));

                    String nouveauType = textScanner.nextLine();
                    for(Evaluation eval : eleveSelectionne.getEvaluations())
                    {
                        if(eval == evaluationSelectionnee)
                        {
                          eval.setEvalType(nouveauType);
                          break;
                        }
                    }
                    break;
                case 4: // MODIFIER MATIERE
                    for(Matiere m : Matiere.listeMatieres)
                        System.out.println(m.getNom());  
                    System.out.println(String.format("Entrez le nom de la matière de la note (Matiere actuelle : %s)",evaluationSelectionnee.getMat().getNom()));
                    String nomMatiere = textScanner.nextLine();

                     Matiere matiere = Matiere.trouverMatiere(nomMatiere,e.getPromotion().getNom());
                    if(matiere==null){
                        System.out.println("Matière non trouvée, création...");
                        matiere = new Matiere(nomMatiere,e.getPromotion().getNom());
                        Matiere.listeMatieres.add(matiere);
                        System.out.println("OK");
                    }
                    proposerModificationEvalParticuliere(evaluationSelectionnee, e);
                    break;
                case 5:
                    System.out.println("Modification des notes choisi");
                    proposerModificationsNotes(e); break;
                default: proposerModificationEvalParticuliere(evaluationSelectionnee,e);
            }
           CSV_Loader.majEvaluations(evaluationSelectionnee, CSV_Loader.EVALUATIONS_PATH);
           proposerModificationEvalParticuliere(evaluationSelectionnee, e);
    }
}
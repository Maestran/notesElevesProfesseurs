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
    Promotion promoActuelle = null;
    TriEleves triParDefaut = TriEleves.moyenne;
    boolean ordreCroissantParDefaut = false; // Si à false, l'ordre affiché est décroissant au lancement du programme
    Eleve eleveSelectionne = null;
    
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
       System.out.println("Que souhaitez-vous faire ? \n ► Chercher un élève en particulier (1) \n ► Lister les élèves d'une promotion (2) \n ► Lister les professeurs (3) \n ► Afficher l'aide (4) \n ►  Quitter (5)");
       int choixMenu = secureIntInput();
       
       switch(choixMenu)
       {
           case 1 : chercherEleveConsole(); break;
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
    }while(choix !=0 && choix != 1);
    
    boolean choixCroissance;
    if(choix == 0)
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
    
    void chercherEleveConsole()
    {
        System.out.println("------------------ Recherche d'un élève -----------------");
        System.out.println("Entrez l'identifiant de l'élève");
                int identifiant = secureIntInput();
                Eleve e =  Promotion.rechercherElevePartout(identifiant);
                if(e==null) chercherEleveConsole();
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
                            System.out.println(String.format("Que souhaitez-vous faire ? %1$s%1$s ► Editer l'élève (1) %1$s ► Supprimer l'élève (2) %1$s ► Retour (3)",System.lineSeparator()));   
          int choixPostAffichage = secureIntInput();
        switch(choixPostAffichage)
        {
            case 1 : // changer de promo
                editerEleve(eleveSelectionne);
                break;
            case 2 : 
                supprimerEleve(eleveSelectionne);
                break;
            case 3 :
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
        System.out.println(String.format("Que souhaitez-vous faire ? %1$s ► Changer de promotion (1) ► Changer le mode de Tri (2) ► Retour (3)",System.lineSeparator()));   
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
                afficherAccueil();
                break;
            default: proposerOptionsConsultationEleves() ;
                    
        }
    }

    public void consulterEleves(TriEleves modeDeTri, boolean croissant)
    {
    
       if(promoActuelle == null)
        promoActuelle = choisirPromo();
        
        if(modeDeTri==TriEleves.moyenne)
            promoActuelle.triMoyenne(croissant);
        else if (modeDeTri==TriEleves.mediane)
            promoActuelle.triMediane(croissant);
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void supprimerEleve(Eleve eleveSelectionne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

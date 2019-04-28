

package notesElevesProfesseurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Promotion {


    private String nom;
    private ArrayList<Eleve> eleves= new ArrayList<>();
    private static ArrayList<Promotion> listePromos = new ArrayList<>();
    public Promotion(String nom)
    {
       this.nom = nom;
    }
    
        public static Eleve rechercherElevePartout(int identifiant) {
        
        Eleve e;
         for(Promotion promoActuelle : Promotion.getListePromos() )
        {
           e = promoActuelle.rechercherEleve(identifiant);
           if(e!=null) return e;
        }
         return null;
    }

        
    
    public void ajouterEleve(Eleve e) {
        eleves.add(e);
        e.setPromotion(this);
    }

    
    Eleve RechercherEleve(int identifiant)
    {
        for(Eleve e : eleves)
        {
           if(e.getId()==identifiant)
            return e;
        }
        return null;
    }
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the eleves
     */
    public ArrayList<Eleve> getEleves() {
        return eleves;
    }
    
    public void listerEleves(boolean detailler)
    {
        System.out.println("Affichage des élèves pour la promotion : " + getNom());   
        System.out.println("----------------------------");
        for(Eleve e : getEleves())
       {
           if(detailler)
               System.out.println(e);
           else
               System.out.println( "ID : "+ e.getId()+ "|"+ e.getNom()+ "|" + e.getPrenom() );
           System.out.println("---------------------------------");
       }
    }

    /**
     * @return the listePromos
     */
    public static ArrayList<Promotion> getListePromos() {
        return listePromos;
    }

    /**
     * @param aListePromos the listePromos to set
     */
    public static void setListePromos(ArrayList<Promotion> aListePromos) {
        listePromos = aListePromos;
    }

    public Eleve rechercherEleve(int searchId) 
    {
        for(Eleve e : getEleves())
            if(e.getId()==searchId) return e;
        return null;
    }
    
    public class MoyenneComparator implements Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) {
            return (int)(e1.calculMoyenne() - e2.calculMoyenne());
        }
    }
    
    public class MedianeComparator implements Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) {
            return (int)(e1.calculMoyenne() - e2.calculMoyenne());
        }
    }

    public class idComparator implements  Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) { return (int)(e1.getId() - e2.getId());}
    }

    public class nomComparator implements  Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) { return e1.getNom().compareTo(e2.getNom());}
    }

    public class prenomComparator implements  Comparator<Eleve>
    {
        @Override
        public int compare(Eleve e1, Eleve e2) { return e1.getPrenom().compareTo(e2.getPrenom());}
    }

    // Classer les élèves par le prénom
    public void triPrenom(boolean croissant)
    {
        Collections.sort(getEleves(),new prenomComparator());
        if(!croissant)
            Collections.reverse(getEleves());
    }

    // Classer les élèves par le nom
    public void triNom(boolean croissant)
    {
        Collections.sort(getEleves(),new nomComparator());
        if(!croissant)
            Collections.reverse(getEleves());
    }

    // Classer les élèves par la id
    public void triId(boolean croissant)
    {
        Collections.sort(getEleves(),new idComparator());
        if(!croissant)
            Collections.reverse(getEleves());
    }
    
    // Classer les élèves par la médiane
    public void triMediane(boolean croissant)
    {
        Collections.sort(getEleves(),new MedianeComparator());
        if(!croissant)
            Collections.reverse(getEleves());
    }

    // Classer les élèves par la moyenne
    public void triMoyenne(boolean croissant)
    {   
        Collections.sort(getEleves(),new MoyenneComparator());
           if(!croissant)
            Collections.reverse(getEleves());
    }

    
    public static Promotion trouverPromotion(String nom)
    {
        nom = nom.toLowerCase();
        for(Promotion p : listePromos)
            if(nom.equals(p.getNom().toLowerCase()))
                return p;
        return null;
    }
    
         public float obtenirMoyennePromotion()
    {
        float moy = 0;
        for(int i = 0; i < this.getEleves().size();i++)
        {
            if(!Float.isNaN(this.getEleves().get(i).calculMoyenne()))
            {
                moy += this.getEleves().get(i).calculMoyenne();
            }
            
        }
        
        return (float)(moy/this.getEleves().size());
    }
        
    public float obtenirMinimumPromotion()
    {
        float value = 21;
        for(Eleve a : this.getEleves())
        {
            if(a.calculMoyenne() < value && !Float.isNaN(a.calculMoyenne()) )
            {
                value = a.calculMoyenne();
            }
            
        }
        
        if(value == 21) value = 0;
        
        return value;
    }
    
            
    public float obtenirMaximumPromotion()
    {
        float value = 0;
        for(Eleve a : this.getEleves())
        {
            if(a.calculMoyenne() > value && !Float.isNaN(a.calculMoyenne()))
            {
                value = a.calculMoyenne();
            }
        }
        
        return value;
    }
}
        
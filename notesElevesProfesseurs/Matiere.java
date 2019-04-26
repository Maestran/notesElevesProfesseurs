package notesElevesProfesseurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Matiere {
    private String nom;
    public static HashSet<Matiere> listeMatieres = new HashSet<>();
    
    public float moyenne = 0;
    public float mediane = 0;
    public float noteMin = 20;
    public float noteMax = 0;
    
    public List<Float> allNote = new ArrayList();
    
    
    public Matiere(){};
    public Matiere(String n){
        this.nom = n;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    
    public static Matiere trouverMatiere(String nomMatiere)
    {
        for(Matiere m : listeMatieres)
        {
           if( m.getNom().equals(nomMatiere))
               return m;
        }
        return null;
    }
    
     public void updateData(float note)
    {
        float sum = 0;
        int indiceMediane = 0;
        int i = 0;
        
        allNote.add(note);
        Collections.sort(allNote);
        
        if(note > noteMax) noteMax = note;
        if(note < noteMin) noteMin = note;
        
        
        for(i = 0; i < allNote.size() / 2; i++)
        {
            sum += (float) allNote.get(i);
        }
        
        if(allNote.size() % 2 == 0)  // Si on a un nombre pair de valeur, la médiane est la moyenne des deux notes a droite et a gauche
        {
            mediane = (float) (((float)allNote.get(i-1) + (float)allNote.get(i)) / 2.0);
        }
        else
        {
            mediane = (float)allNote.get(i);
        }
        
        for(i = allNote.size() / 2; i < allNote.size() ; i++)
        {
            sum += (float) allNote.get(i);
        }
        
        moyenne = sum / (float) allNote.size();
    }


}

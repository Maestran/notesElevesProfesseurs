package notesElevesProfesseurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Eleve extends Personne implements Iterable<Evaluation> {
    private static int registre = 1;
    final int NB_EVALUATION = 10;
    private int id;
    private Date naissance = new Date();
    private List<Evaluation> evaluations;
    private Promotion promotion;
    

    public Eleve() {    
        this.id = this.registre;
        this.evaluations = new ArrayList<Evaluation>(NB_EVALUATION);
        this.registre++;
    }


    public Eleve(String prenom, String nom, int annee, int mois, int jour){
        this.setPrenom(prenom);
        this.setNom(nom);
        this.naissance = new Date(annee,mois,jour);

        this.id = this.registre;
        this.evaluations = new ArrayList<>(NB_EVALUATION);
        this.registre++;
    }
    
        public Eleve(String prenom, String nom, int annee, int mois, int jour, Promotion promo){
            this( prenom,  nom,  annee,  mois,  jour);
            this.promotion = promo;
    }


    @Override
    public Iterator<Evaluation> iterator() {
        return this.evaluations.iterator();
    }

    public void add(Evaluation e) {
        if (evaluations.size() < NB_EVALUATION) {
            this.evaluations.add(e);
        } else {
            System.out.println("vous ne pouvez ajouter de notes");
        }
    }

    public void calculMoyenne() {
        float result = 0;

        for (int i = 0; i < evaluations.size(); i++) {
            result += evaluations.get(i).getNote();
        }
        System.out.println("Votre moyenne est de : " + result / evaluations.size());

    }

    public void calculMedianne() {
        this.triNote();
        float result = 0;
          if (evaluations.size() % 2 == 0) {
            result = evaluations.get((int) (evaluations.size() / 2)).getNote() + evaluations.get((int) (evaluations.size() / 2) - 1).getNote();
            System.out.println("Medianne = " + result / 2);
        } else {
            System.out.println("Medianne = " + evaluations.get((int) (evaluations.size() / 2)).getNote());
        }
    }

    public void triNote() {
        Collections.sort(evaluations, new ComparatoNote());

    }

    public String getCorrecteurs() {
        return "f";
    }

    public int getId() {
        return id;
    }

    
    @Override
    public String toString()
    {
        return "( ID : " + this.getId()+ "|"+this.getNom()+", "+this.getPrenom()+", Promotion : "+ this.getPromotion().getNom()+")";
    }

    /**
     * @return the evaluations
     */
    public List<Evaluation> getEvaluations() {
        return evaluations;

    }
    
    public String affnotes() {
        String s = "Affichage notes\n";
        for (int i = 0; i < evaluations.size(); i++) {
            s += evaluations.get(i).toString();
            s += "\n";
        }
        return s;

    }

    /**
     * @return the promotion
     */
    public Promotion getPromotion() {
        return promotion;
    }
    
    public void setPromotion(Promotion p)
    {
        promotion = p;
    }
}

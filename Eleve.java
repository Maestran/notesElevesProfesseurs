package notesElevesProfesseurs;

import java.util.*;

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

    public String calculMoyenne() {
        float result = 0;

        for (int i = 0; i < evaluations.size(); i++) {
            result += evaluations.get(i).getNote();
        }
        return "Moyenne =" + result / evaluations.size();

    }

    public String calculMedianne() {
        this.triNote();
        float result = 0;
          if (evaluations.size() % 2 == 0) {
            result = evaluations.get((int) (evaluations.size() / 2)).getNote() + evaluations.get((int) (evaluations.size() / 2) - 1).getNote();
            return "Mediane = " + result / 2;
        } else {
            return "Mediane = " + evaluations.get((int) (evaluations.size() / 2)).getNote();
        }
    }

    public void triNote() {
        Collections.sort(evaluations, new ComparatoNote());

    }

    public Set<Professeur> getCorrecteurs()

    {
        HashSet<Professeur> h = new HashSet<Professeur>();
        for (int i = 0; i < evaluations.size(); i++){
            h.add(evaluations.get(i).getProf());
        }
        return h;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString()
    {
        String s = super.toString()+"id: " + this.getId()+"\n" +  this.getPromotion().getNom()+"\n";
        s+= this.affNotes()+"\n";
        s+= this.calculMoyenne()+"\n";
        s+= this.calculMedianne()+"\n";
        s+= "Correcteur(s): "+this.getCorrecteurs()+"\n";
        return s;
    }

    /**
     * @return the evaluations
     */
    public List<Evaluation> getEvaluations() {
        return evaluations;

    }
    
    public String affNotes() {
        String s = "notes: ";
        for (int i = 0; i < evaluations.size(); i++) {
            s += evaluations.get(i).getMat().getNom()+" "+evaluations.get(i).getNote()+" ";
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

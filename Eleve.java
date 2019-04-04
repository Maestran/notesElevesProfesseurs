package notesElevesProfesseurs;

import java.util.*;

public class Eleve extends Personne implements Iterable<Evaluation> {
    final int NB_EVALUATION = 10;
    private static int registre = 1;
    private int id;
    private Date naissance = new Date();
    private List<Evaluation> evaluations;
    private Promotion promotion;
    
    
    @Override
    public Iterator<Evaluation> iterator() {
        return this.getEvaluations().iterator();
    }

    public void add(Evaluation e){
        if (getEvaluations().size()<NB_EVALUATION){
            this.getEvaluations().add(e);
        } else {
            System.out.println("vous ne pouvez ajouter de notes");
        }
    }

    public Eleve(){
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

    public void calculMoyenne(){
        float result = 0;
            for (int i = 0; i < getEvaluations().size(); i++){
                result += getEvaluations().get(i).getNote();
            }
        System.out.println("Votre moyenne est de : "+result/getEvaluations().size());
    }

    public void calculMedianne(){
       this.triNote();
        float result = 0;
       if (getEvaluations().size()%2 == 0){
           result = getEvaluations().get((int)(getEvaluations().size()/2)+1).getNote() + getEvaluations().get((int)(getEvaluations().size()/2)-1).getNote();
           System.out.println("Medianne = "+result/2 );
       } else {
           System.out.println("Medianne = "+getEvaluations().get((int)(getEvaluations().size()/2)+1).getNote());
       }
    }

    public void triNote(){
        Collections.sort(getEvaluations(), new ComparatoNote());
    }

    public String getCorrecteurs(){
        return "f";
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString()
    {
        return "( ID : " + this.getId()+ "|"+this.getNom()+", "+this.getPrenom()+")";
    }

    /**
     * @return the evaluations
     */
    public List<Evaluation> getEvaluations() {
        return evaluations;
    }
}

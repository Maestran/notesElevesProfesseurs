package notesElevesProfesseurs;

import java.util.*;

public class Eleve extends Personne implements Iterable<Evaluation>, Comparable<Eleve>  {


    private static int registre = 1;
    final int NB_EVALUATION = 10;
    private int id;
    private Date dateNaissance;
    private ArrayList<Evaluation> evaluations;
    private Promotion promotion;
    
    public Eleve() {    
        this.id = Eleve.registre;
        this.evaluations = new ArrayList<>(NB_EVALUATION);
        Eleve.registre++; 
    }


    public Eleve(String prenom, String nom, int anneeNaissance, int moisNaissance, int jourNaissance){
        // Le this() permet d'appeler le premier constructeur, celui sans arguments
        this();
        this.setPrenom(prenom);
        this.setNom(nom);
        this.dateNaissance = new Date(anneeNaissance,moisNaissance,jourNaissance);
    }
    
        public Eleve(String prenom, String nom, Date dateNaissance){
        // Le this() permet d'appeler le premier constructeur, celui sans arguments
        this();
        this.setPrenom(prenom);
        this.setNom(nom);
        this.dateNaissance = dateNaissance;

    }
    
        public Eleve(String prenom, String nom, int annee, int mois, int jour, Promotion promo){
            this( prenom,  nom,  annee,  mois,  jour);
            this.promotion = promo;
    }

            /**
     * @return the dateNaissance
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public Iterator<Evaluation> iterator() {
        return this.evaluations.iterator();
    }

    public void add(Evaluation e) {
        if (evaluations.size() < NB_EVALUATION) 
        {
            this.evaluations.add(e);
        } else {
            System.out.println("vous ne pouvez plus ajouter de notes (limite : " + NB_EVALUATION + " notes)");
        }
    }

    public float calculMoyenne() {
        float result = 0;

        for (int i = 0; i < evaluations.size(); i++) {
            result += evaluations.get(i).getNote();
        }
        return  result / evaluations.size();

    }

    public float calculMedianne() {
        this.triNote();
        float result = 0;
          if (evaluations.size() % 2 == 0) {
            result = evaluations.get((int) (evaluations.size() / 2)).getNote() + evaluations.get((int) (evaluations.size() / 2) - 1).getNote();
            return  result / 2;
        } else {
            return  evaluations.get((int) (evaluations.size() / 2)).getNote();
        }
    }

    public void triNote() {
        Collections.sort(evaluations, new ComparatoNote());
    }

    public Set<Professeur> getCorrecteurs()
    {
        HashSet<Professeur> h = new HashSet<>();
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
        String s = super.toString()+"id: " + this.getId()+"\nPromotion : " +  this.getPromotion().getNom()+"\n";
        if(getEvaluations()!=null && getEvaluations().size()>0)
        {
        s+= this.affNotes()+"\n";
        s+= "Moyenne : " + this.calculMoyenne()+"\n";
        s+= "Mediane : " + this.calculMedianne()+"\n";
        s+= "Date de naissance " + this.dateNaissance+"\n";
        s+= "Correcteur(s): "+this.getCorrecteurs()+"\n";
        }
        return s;
    }

    /**
     * @return the evaluations
     */
    public ArrayList<Evaluation> getEvaluations() {
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

    @Override
    public int compareTo(Eleve o) {
        float moy = this.calculMoyenne() - o.calculMoyenne();
        if(moy!=0) return (int) moy;
        return (int) ((int) this.calculMedianne() - o.calculMedianne());
    } 
    
  
}

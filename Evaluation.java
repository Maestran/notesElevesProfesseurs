package notesElevesProfesseurs;

import java.util.Comparator;
import java.util.HashSet;

public class Evaluation implements Comparable<Evaluation>{
    private float note;
    private Matière mat = new Matière();
    private Eleve eleve = new Eleve();
    private Professeur prof = new Professeur();

    public Evaluation(){};

    public Evaluation(float n, Matière m, Eleve e, Professeur p){
        this.note = n;
        this.mat = m;
        this.eleve = e;
        this.prof = p;
    }

    @Override
    public String toString() {
        return "(("+eleve.toString()+" "+prof.toString()+" "+mat.getNom()+" "+note+")";
    }

    public Eleve getEleve() {
        return eleve;
    }

    public float getNote() {
        return note;
    }

    public Matière getMat() {
        return mat;
    }

    public Professeur getProf() {
        return prof;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public void setMat(Matière mat) {
        this.mat = mat;
    }

    public void setProf(Professeur prof) {
        this.prof = prof;
    }

    @Override
    public int compareTo(Evaluation o) {
        return (int) (this.getNote() - o.getNote());
    }
}

class ComparatoNote implements Comparator<Evaluation> {
    @Override
    public int compare(Evaluation o1, Evaluation o2) {
        return (int) (o1.getNote() - o2.getNote());
    }
}

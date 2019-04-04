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

    public Eleve() {
        this.id = this.registre;
        this.evaluations = new ArrayList<Evaluation>(NB_EVALUATION);
        this.registre++;
    }

    public Eleve(String p, String n, int a, int m, int j) {
        this.setPrenom(p);
        this.setNom(n);
        this.naissance = new Date(a, m, j);
        this.id = this.registre;
        this.evaluations = new ArrayList<Evaluation>(NB_EVALUATION);
        this.registre++;
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

    public String affnotes() {
        String s = "Affichage notes\n";
        for (int i = 0; i < evaluations.size(); i++) {
            s += evaluations.get(i).toString();
            s += "\n";
        }
        return s;
    }
}

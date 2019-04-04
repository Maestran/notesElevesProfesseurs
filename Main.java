package notesElevesProfesseurs;

public class Main {

    public static void main(String[] args) {
        Eleve e2 = new Eleve();
        Professeur p = new Professeur();
        p.setNom("nn");
        p.setPrenom("dd");
        Matière math = new Matière("maths");
        Evaluation eval = new Evaluation(12,math,e2,p);
        e2.add(eval);
        Evaluation eval2 = new Evaluation(14,math,e2,p);
        e2.add(eval2);
        System.out.println(e2.getId());
        e2.calculMedianne();
        //System.out.println(e2.getCorrecteurs());
    }
}

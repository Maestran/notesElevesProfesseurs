/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs.GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Professeur;

/**
 *
 * @author franc
 */
public class GenerateurEvaluationsOperations
{
    public void ajouterDetectionClicLigne(JTable evalsTable, JButton buttonSupp, JButton buttonModif)
    {
          evalsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(evalsTable.getSelectedRow()>=0)
                {
                     System.out.println("Ligne cliquée changée !");
                buttonSupp.setEnabled(true);
                buttonModif.setEnabled(true);
                float note = (float)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 0);
                String matString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 1);
                String profString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 2);
                String typeString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 3);
                
                if(Globals.eleveSelectionne != null)
                for(Evaluation eval : Globals.eleveSelectionne.getEvaluations())
                {
                    if(eval.getNote()== note)
                    {
                        if( eval.getEvalType() == typeString)
                        {
                            if(eval.getMat().getNom()== matString)
                            {
                                String nomPrenom = eval.getProf().getPrenom()+ " "+ eval.getProf().getNom();
                                if(nomPrenom.equals(profString))
                                {
                                    System.out.println(">>>>>>> Evaluation sélectionnée !");
                                    Globals.evaluationSelectionnee = eval;
                                    GenerateurEvaluations.evalEnCours = eval;
                                     break;
                                }
                            }
                        }
                    } 
                    System.out.println();
                }
                else System.out.println("(!) Erreur  : Aucun élève n'est sélectionné");
                }
               
            }
        });
    }
    
    
    public void afficherEvaluationsEleve(Eleve e, JTable evaluationsTable, JLabel mainLabel)
    {
        
        System.out.println("Début d'affichage des évaluations");
        DefaultTableModel model = new DefaultTableModel();
        
        Object[] columns = {"Note","Matière","Correcteur","Type"};
        model.setColumnIdentifiers(columns);
        Object[] rows = new Object[evaluationsTable.getColumnCount()]; // par défaut 4 colonnes   
        System.out.println(System.lineSeparator()+rows.length +" évaluations trouvées");
        for(Evaluation eval : e.getEvaluations())
        {
                    rows[0] = eval.getNote();                                                                    // NOTE
                    rows[1] = eval.getMat().getNom();                                                            // MATIERE
                    rows[2] = eval.getProf().getPrenom() + " " + eval.getProf().getNom();                      // CORRECTEUR
                    rows[3] = eval.getEvalType();                                                              // TYPE
                            model.addRow(rows);
                            System.out.println("Ajout de " + eval);
        }
        evaluationsTable.setModel(model);
        evaluationsTable.repaint();
        mainLabel.setText("Liste des évaluations pour l'élève : "+ e.getNom().toUpperCase() + " " + e.getPrenom());
        System.out.println("Terminé!"); 
    }

    void activerRemplissageChampsEval(JTable evalsTable, JTextField champNote, JTextField champCorrecteur, JTextField champMatiere, JTextField champType) {
  evalsTable.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e)
           {
                remplirChamps(evalsTable.getSelectedRow(), evalsTable,champNote,champCorrecteur,champMatiere,champType);
           }
       });    }
    
       
    public void remplirChamps(int rowNumber, JTable evalsTable, JTextField champNote, JTextField champCorrecteur, JTextField champMatiere, JTextField champType)
    {
         float note = (float)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 0);
        String matString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 1);
        String correctString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 2);
        String typeString = (String)evalsTable.getModel().getValueAt(evalsTable.getSelectedRow(), 3);

         Matiere mat =Matiere.trouverMatiere(matString,Globals.promoActuelle.getNom());
        if(mat==null)
        {
             mat = new Matiere(matString,Globals.promoActuelle.getNom());
            Matiere.listeMatieres.add(mat);
        }
        
        String[] nomPrenom = correctString.split(" ");
        Professeur prof = Professeur.trouverProfesseur(nomPrenom[0], nomPrenom[1]);
        if(prof==null)
        {
          prof = new Professeur(nomPrenom[0],nomPrenom[1]);
          Professeur.getListeProfesseurs().add(prof);
        }
        Evaluation eval = new Evaluation(note, mat,Globals.eleveSelectionne, prof);
        eval.setEvalType(typeString);
        champNote.setText(String.format("%.1f", eval.getNote()));
        champCorrecteur.setText(eval.getProf().getPrenom() + " " +eval.getProf().getNom());
        champMatiere.setText(eval.getMat().getNom());
        champType.setText(eval.getEvalType());
        
    }
          
}

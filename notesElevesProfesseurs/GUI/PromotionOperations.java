/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Promotion;

/**
 *
 * @author franc
 */
public class PromotionOperations 
{
    
     public void afficherElevesPromo(Promotion p, JTable elevesTable)
    {
        
        System.out.println("Début d'affichage des élèves");
        DefaultTableModel model = new DefaultTableModel();
        Object[] columns = {"Identifiant","Nom","Prenom","Promotion","Nombre d'évaluations","Nombre de correcteurs"};
        model.setColumnIdentifiers(columns);
        Object[] rows = new Object[elevesTable.getColumnCount()]; // par défaut 6 colonnes   
        System.out.println(System.lineSeparator()+rows.length +" élèves");
        for(Eleve e : p.getEleves())
        {
                    rows[0] = e.getId();                          // IDENTIFIANT
                    rows[1] = e.getNom().toUpperCase();           // NOM
                    rows[2] = e.getPrenom();                      // PRENOM
                    rows[3] = e.getPromotion().getNom();          // PROMOTION
                    rows[4] = e.getEvaluations().size();          // NOMBRE EVALS
                    rows[5] = e.getCorrecteurs().size();          // NOMBRE CORRECTEURS
                            model.addRow(rows);
                            System.out.println("Ajout de " + e);
        }
        elevesTable.setModel(model);
        elevesTable.repaint();
        System.out.println("Terminé!");
    }
     
     public void activerLaBarreDeRecherche(JTextField barreDeRecherche, JTable tableAChercher)
     {
         barreDeRecherche.setEnabled(true);
         barreDeRecherche.getDocument().addDocumentListener(new DocumentListener() {
             @Override
             public void insertUpdate(DocumentEvent e) {
         rechercheEleve( barreDeRecherche, tableAChercher);
             }

             @Override
             public void removeUpdate(DocumentEvent e) {
         rechercheEleve( barreDeRecherche,tableAChercher);
             }

             @Override
             public void changedUpdate(DocumentEvent e) {
         rechercheEleve( barreDeRecherche,tableAChercher);
             }
         });
     }
     
     private void rechercheEleve(JTextField barreDeRecherche ,JTable tableAChercher )
     {
         try {
       int id = Integer.parseInt(barreDeRecherche.getText());
              Eleve eleve = Globals.promoActuelle.rechercherEleve(id);
        DefaultTableModel model = ((DefaultTableModel) tableAChercher.getModel());
        int rowCount = model.getRowCount();
            for(int i = rowCount -1 ;  i>=0; i--) // On enlève toutes les lignes en partant de la fin
            {
                model.removeRow(i);
            }
        if(eleve!=null)
            ((DefaultTableModel) tableAChercher.getModel()).addRow(new Object[]{eleve.getId(),eleve.getNom(),eleve.getPrenom(),eleve.getPromotion().getNom(),eleve.getEvaluations().size(),eleve.getCorrecteurs().size()});
            
         } catch (Exception e) 
         {
             System.out.println("Erreur conversion entier");
         }
       
     }
     
     public void genererComboboxPromotions(JComboBox box,JTable elevesTable)
     {
         box.removeAllItems();
         for(Promotion p : Promotion.getListePromos())
         {
             box.addItem(p.getNom());
         }
         System.out.println("Items ajoutés à la combobox");
         assignerEventComboBoxPromotions(box, elevesTable);
         
     }
     
     private void assignerEventComboBoxPromotions(final JComboBox box, JTable elevesTable)
     {
          box.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Globals.promoActuelle = Promotion.trouverPromotion((String)box.getSelectedItem());
                    afficherElevesPromo(Globals.promoActuelle, elevesTable);
                }
            });
     }
    
}

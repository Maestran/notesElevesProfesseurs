/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs.GUI; 

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Promotion;
import notesElevesProfesseurs.TriEleves;

/**
 *
 * @author franc
 */
public class PromotionOperations 
{
    //Modèle sauvegardé pour la recherche de données
    public void viderTable(JTable table)
    {
                DefaultTableModel model = ((DefaultTableModel) table.getModel());
                int rowCount = model.getRowCount();
            for(int i = rowCount -1 ;  i>=0; i--) // On enlève toutes les lignes en partant de la fin
            {
                model.removeRow(i);
                System.out.println("Suppression ligne "+ i);
            }
    }
    
     public void afficherElevesPromo(Promotion promo, JTable elevesTable)
    {
        viderTable(elevesTable);
        System.out.println("Début d'affichage des élèves");
        DefaultTableModel model = new DefaultTableModel();
        Object[] columns = {"Identifiant","Nom","Prenom","Promotion","Nombre d'évaluations","Nombre de correcteurs"};
        model.setColumnIdentifiers(columns);
        Object[] rows = new Object[elevesTable.getColumnCount()]; // par défaut 6 colonnes   
        System.out.println(System.lineSeparator()+rows.length +" parametres");
        System.out.println(promo.getEleves().size() + " élèves");
        for(Eleve e : promo.getEleves())
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
             viderTable(tableAChercher);
        if(eleve!=null)
            ((DefaultTableModel) tableAChercher.getModel()).addRow(new Object[]{eleve.getId(),eleve.getNom(),eleve.getPrenom(),eleve.getPromotion().getNom(),eleve.getEvaluations().size(),eleve.getCorrecteurs().size()});
         } catch (Exception e) 
         {
             System.out.println("Erreur conversion entier");
                         remontrerLaTable(tableAChercher);
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

    private void remontrerLaTable(JTable table) 
    {
         System.out.println(">>>>Rechargé");
         afficherElevesPromo(Globals.promoActuelle, table);
    }

    void genererComboboxTri(JComboBox triCbobox, JTable elevesTable) {
          triCbobox.removeAllItems();
         triCbobox.addItem(TriEleves.identifiant);
         triCbobox.addItem(TriEleves.mediane);
         triCbobox.addItem(TriEleves.moyenne);
         triCbobox.addItem(TriEleves.nom);
         triCbobox.addItem(TriEleves.prenom);

         System.out.println("Items de Tri ajoutés à la combobox");
         assignerEventComboBoxTri(triCbobox, elevesTable);
    }

    public void trierAfficherPromotionActuelle(JTable table)
    {
           switch(Globals.modeTriParDefaut)
                    {
                        case identifiant:
                            Globals.promoActuelle.triId(Globals.triCroissant);
                            break;
                        case mediane:
                            Globals.promoActuelle.triMediane(Globals.triCroissant);
                            break;
                        case moyenne:
                            Globals.promoActuelle.triMoyenne(Globals.triCroissant);
                            break;
                        case nom:
                            Globals.promoActuelle.triNom(Globals.triCroissant);
                            break;
                        case prenom:
                            Globals.promoActuelle.triPrenom(Globals.triCroissant);
                            break;
                    }
                    afficherElevesPromo(Globals.promoActuelle, table);
    }
    
    private void assignerEventComboBoxTri(JComboBox triCbobox, JTable elevesTable)
    {
 triCbobox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Tri changé");
                    Globals.modeTriParDefaut = (TriEleves)triCbobox.getSelectedItem();
                    trierAfficherPromotionActuelle(elevesTable);
                }
            });    }

    //Permet d'activer ou désactiver automatiquement le bouton modifier si une ligne de la jtable est sélectionnée ou non
    void relierBoutonModifierTable(JButton modifEleveB, JTable elevesTable) 
    {
        elevesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("Séléction changée dans la table");
                if(!modifEleveB.isEnabled())
                modifEleveB.setEnabled(true);
            }
        });
    }

    //Lorsqu'un double clic est effectué sur une ligne, les détails de l'élève vont s'afficher (non modifiables)
    void montrerInfosEleveSurDoubleClick(JTable elevesTable) 
    {
        System.out.println("Ajout du Listener de double clic");
        elevesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event)
            {
                Point point = event.getPoint(); // Coordonnées x et y de là où on a cliqué
                int row = elevesTable.rowAtPoint(point);
                if(event.getClickCount() == 2 && elevesTable.getSelectedRow() != - 1 && row != -1) // On vérifie que l'on a bien cliqué sur une ligne et pas dans un autre endroit de la table
                {
                    System.out.println("Double click ligne");
                    Globals.eleveSelectionne = Globals.promoActuelle.rechercherEleve((Integer)elevesTable.getModel().getValueAt(elevesTable.getSelectedRow(), 0));
                    EleveInfos eleveInfos = new EleveInfos(Globals.eleveSelectionne);
                    eleveInfos.setVisible(true);
                }
            }
            
        });
    }
    
}

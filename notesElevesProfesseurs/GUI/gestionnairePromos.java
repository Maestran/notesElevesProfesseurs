/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs.GUI;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import notesElevesProfesseurs.CSV_Loader;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Promotion;

/**
 *
 * @author franc
 */
public class gestionnairePromos extends javax.swing.JFrame {

    /**
     * Creates new form promotionFrame
     */
    public gestionnairePromos() {
        initComponents();

        if(Globals.promoActuelle == null)
            Globals.promoActuelle = Promotion.getListePromos().get(0);
        
        if(Globals.promoActuelle!=null)
        {
           PromotionOperations operations = new PromotionOperations();
           operations.afficherElevesPromo(Globals.promoActuelle,elevesTable);
           operations.genererComboboxPromotions(promotionCombobox, elevesTable);
           operations.activerLaBarreDeRecherche(barreDeRecherche, elevesTable);
           operations.genererComboboxTri(triCombobox, elevesTable);
        }
        int total = 0;
        for(Promotion p : Promotion.getListePromos())
            total+=p.getEleves().size();
                
        setTitle("Liste des élèves (" + total + " au total ) ");
    }

   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        elevesTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        promotionCombobox = new javax.swing.JComboBox<>();
        barreDeRecherche = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        retourB = new javax.swing.JButton();
        creerEleve = new javax.swing.JButton();
        triCombobox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        toggleSensTriB = new javax.swing.JToggleButton();
        supprEleveB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Liste des élèves");

        elevesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Identifiant", "Nom", "Prénom", "Promotion", "Nombre évaluations", "Nombre correcteurs"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Short.class, java.lang.Short.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEdiatble(int row, int column){return false;}
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        elevesTable.setToolTipText("");
        elevesTable.setRowHeight(30);
        jScrollPane1.setViewportView(elevesTable);

        jLabel1.setText("Mode de Tri");

        promotionCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        promotionCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promotionComboboxActionPerformed(evt);
            }
        });

        barreDeRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barreDeRechercheActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Rechercher un identifiant");

        retourB.setText("Retour");
        retourB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //retourBActionPerformed(evt);
                majEleveActionPerformed(evt); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Bouton retour de TEST!!!!!!!!!!!!!!!
            }
        });

        creerEleve.setText("Ajouter un nouvel élève");
        creerEleve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerEleveActionPerformed(evt);
            }
        });

        triCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Promotion Sélectionnée  ");

        toggleSensTriB.setText("Ordre : croissant ");
        toggleSensTriB.setToolTipText("");
        toggleSensTriB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleSensTriBActionPerformed(evt);
            }
        });

        supprEleveB.setText("Supprimer un élève");
        supprEleveB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprEleveBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(supprEleveB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(creerEleve, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(triCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(promotionCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toggleSensTriB)
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barreDeRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(retourB, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(barreDeRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(retourB)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(promotionCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(triCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toggleSensTriB))))
                .addGap(18, 18, 18)
                .addComponent(creerEleve)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(supprEleveB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        getAccessibleContext().setAccessibleName("Fenêtre");
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void promotionComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promotionComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_promotionComboboxActionPerformed

    private void barreDeRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barreDeRechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barreDeRechercheActionPerformed

    private void retourBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourBActionPerformed
        // TODO add your handling code here:
        if(!MenuPrincipal.menu.isVisible())
        {
        MenuPrincipal.menu.setVisible(true);
        }
        this.dispose();
     
    }//GEN-LAST:event_retourBActionPerformed

    private void creerEleveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerEleveActionPerformed
            GenerateurEleve generateurEleve = new GenerateurEleve();
            generateurEleve.promoTF.setText(Globals.promoActuelle.getNom());
            generateurEleve.setVisible(true);
            dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_creerEleveActionPerformed

    private void toggleSensTriBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleSensTriBActionPerformed
        // isSelected() permet de voir si le bouton est enfoncé
        if( toggleSensTriB.isSelected() ) 
        {
           toggleSensTriB.setText("Ordre : décroissant");
           Globals.triCroissant=false;
        }
        else 
        {
       toggleSensTriB.setText("Ordre : croissant");
        Globals.triCroissant=true;
        }
       PromotionOperations operations = new PromotionOperations();
       operations.trierAfficherPromotionActuelle(elevesTable);
    }//GEN-LAST:event_toggleSensTriBActionPerformed

    private void supprEleveBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprEleveBActionPerformed
        // TODO add your handling code here:
         int id = (Integer)elevesTable.getValueAt(elevesTable.getSelectedRow(), 0);
         Eleve e = Globals.promoActuelle.rechercherEleve(id);
         Globals.promoActuelle.getEleves().remove(e);
         CSV_Loader.supprimerEleveDansFichier(e, CSV_Loader.ELEVES_PATH);
        ((DefaultTableModel)elevesTable.getModel()).removeRow(elevesTable.getSelectedRow());
    }//GEN-LAST:event_supprEleveBActionPerformed

    private void montrerEleveBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprEleveBActionPerformed
        // TODO add your handling code here:
        int id = (Integer)elevesTable.getValueAt(elevesTable.getSelectedRow(), 0);
        Eleve e = Globals.promoActuelle.rechercherEleve(id);
        eleveFrame eleveWindow = new eleveFrame(e);
        eleveWindow.setVisible(true);
        dispose();
    }//GEN-LAST:event_supprEleveBActionPerformed

    private void majEleveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerEleveActionPerformed
        int id = (Integer)elevesTable.getValueAt(elevesTable.getSelectedRow(), 0);
        Eleve e = Globals.promoActuelle.rechercherEleve(id);
        majEleve upEleve = new majEleve(e);
        upEleve.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_creerEleveActionPerformed


    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gestionnairePromos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gestionnairePromos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gestionnairePromos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gestionnairePromos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gestionnairePromos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barreDeRecherche;
    private javax.swing.JButton creerEleve;
    private javax.swing.JTable elevesTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> promotionCombobox;
    private javax.swing.JButton retourB;
    private javax.swing.JButton supprEleveB;
    private javax.swing.JToggleButton toggleSensTriB;
    private javax.swing.JComboBox<String> triCombobox;
    // End of variables declaration//GEN-END:variables
}

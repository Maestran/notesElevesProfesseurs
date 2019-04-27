/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notesElevesProfesseurs.GUI;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import notesElevesProfesseurs.CSV_Loader;
import notesElevesProfesseurs.Date;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Evaluation;
import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Professeur;
import notesElevesProfesseurs.Promotion;




/**
 *
 * @author franc
 */
public class GenerateurEvaluations extends javax.swing.JFrame 
{
    
    // Sert de marque page pour savoir où modifier dans le fichier CSV l'évaluation
    public static Evaluation evalEnCours = null;
    /**
     * Creates new form GenerateurEvaluations
     */
    public GenerateurEvaluations() 
    {
        initComponents();
    }

    GenerateurEvaluations(GenerateurEleve genEleve) {
        initComponents();
    }
    
    
    public void verifActivationBouton()
    {
        if(!matTF.getText().isEmpty() && !noteTF.getText().isEmpty()  && !typeTF.getText().isEmpty()  && !correcteurTF.getText().isEmpty() )
            ajouterEvalB.setEnabled(true);
        else 
            ajouterEvalB.setEnabled(false);
    }
    
    
   
    public void init(Eleve e)
    {
        Globals.eleveSelectionne=  e;
        GenerateurEvaluationsOperations operations = new GenerateurEvaluationsOperations();
        operations.afficherEvaluationsEleve(e, evalsTable, mainLabel);
        operations.ajouterDetectionClicLigne(evalsTable, suppEvalB, modifEvalB);
        operations.activerRemplissageChampsEval(evalsTable,noteTF,correcteurTF,matTF,typeTF);
        
        DocumentListener tfListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                verifActivationBouton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verifActivationBouton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verifActivationBouton();
            }
        };
        
        matTF.getDocument().addDocumentListener(tfListener);
        correcteurTF.getDocument().addDocumentListener(tfListener);
        typeTF.getDocument().addDocumentListener(tfListener);
        noteTF.getDocument().addDocumentListener(tfListener);
        
     
       
        addWindowListener(new WindowAdapter() {
                        @Override
            public void windowClosing(WindowEvent e)
            {
                majTexteBouton(Globals.eleveSelectionne);
            }});
    }
 
    
     public void majTexteBouton(Eleve eleveSelectionne) 
    {
        System.out.println("Maj Texte Bouton...");
        Frame[] frames = Frame.getFrames();
        for(Frame f : frames)
        {
            System.out.println("Fenetre : " +f.getTitle() + "Type : " + f.getClass());
            if(f.getClass() == ModifEleve.class || f.getClass() == GenerateurEleve.class)
            {
                System.out.println("INSTANCE TROUVEE !"); 
                if(f.getClass() == ModifEleve.class)
             ((ModifEleve) f).ouvrirGenEvalsB.setText("Ajouter des évaluations ( Nombre actuel : "+ eleveSelectionne.getEvaluations().size() +") ");   
                else 
             ((GenerateurEleve) f).ouvrirGenEvalsB.setText("Ajouter des évaluations ( Nombre actuel : "+ eleveSelectionne.getEvaluations().size() +") ");   
            }
        }
        System.out.println("FIN");
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
        evalsTable = new javax.swing.JTable();
        ajouterEvalB = new javax.swing.JButton();
        suppEvalB = new javax.swing.JButton();
        mainLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        noteTF = new javax.swing.JTextField();
        typeTF = new javax.swing.JTextField();
        correcteurTF = new javax.swing.JTextField();
        matTF = new javax.swing.JTextField();
        modifEvalB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        evalsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Note", "Matière", "Correcteur", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Float.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        evalsTable.setRowHeight(30);
        jScrollPane1.setViewportView(evalsTable);

        ajouterEvalB.setText("Ajouter une évaluation");
        ajouterEvalB.setEnabled(false);
        ajouterEvalB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterEvalBActionPerformed(evt);
            }
        });

        suppEvalB.setText("Supprimer une évaluation");
        suppEvalB.setEnabled(false);
        suppEvalB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppEvalBActionPerformed(evt);
            }
        });

        mainLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        mainLabel.setText("Liste des évaluations pour l'élève : ?");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Matière");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Correcteur");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Note");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Type");

        noteTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noteTFActionPerformed(evt);
            }
        });

        modifEvalB.setText("Modifier");
        modifEvalB.setEnabled(false);
        modifEvalB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifEvalBClick(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(suppEvalB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ajouterEvalB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modifEvalB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(noteTF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(matTF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(typeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(correcteurTF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addComponent(mainLabel)
                    .addContainerGap(541, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(448, 448, 448)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(suppEvalB, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modifEvalB, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ajouterEvalB, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noteTF, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(correcteurTF, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(typeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(matTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(56, 56, 56))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mainLabel)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(224, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void suppEvalBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppEvalBActionPerformed
       if(Globals.evaluationSelectionnee == null)
       {
           System.out.println("Impossible de supprimer l'évaluation car aucune n'est selectionnée");
                      return;
       }
        
        suppEvalB.setEnabled(false);
        ((DefaultTableModel) evalsTable.getModel()).removeRow(evalsTable.getSelectedRow());
        evalsTable.clearSelection();
        for(Evaluation eval : Globals.eleveSelectionne.getEvaluations().toArray(new Evaluation[Globals.eleveSelectionne.getEvaluations().size()]))
            if(eval == Globals.evaluationSelectionnee)
                Globals.eleveSelectionne.getEvaluations().remove(eval);
                
    CSV_Loader.supprimerEvaluationDansFichier(Globals.evaluationSelectionnee, CSV_Loader.EVALUATIONS_PATH);
                // A TERMINER (ACTUALISATION EN TEMPS REEL)
    }//GEN-LAST:event_suppEvalBActionPerformed

    private void ajouterEvalBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterEvalBActionPerformed
        // TODO add your handling code here:
        Matiere mat =Matiere.trouverMatiere(matTF.getText());
        if(mat==null)
        {
             mat = new Matiere(matTF.getText());
            Matiere.listeMatieres.add(mat);
        }
        
        String[] nomPrenom = correcteurTF.getText().split(" ");
        Professeur prof = Professeur.trouverProfesseur(nomPrenom[0], nomPrenom[1]);
        if(prof==null)
        {
          prof = new Professeur(nomPrenom[0],nomPrenom[1]);
          Professeur.getListeProfesseurs().add(prof);
        }
        
        Evaluation eval = new Evaluation(Float.parseFloat(noteTF.getText().replace(',', '.')), mat,Globals.eleveSelectionne, prof);
        Globals.eleveSelectionne.getEvaluations().add(eval);
        eval.setEvalType(typeTF.getText());
        ((DefaultTableModel) evalsTable.getModel()).addRow(new Object[]{eval.getNote(),eval.getMat().getNom(),eval.getProf().getPrenom(),eval.getEvalType()});
        CSV_Loader.ajouterEvaluationDansFichier(eval,CSV_Loader.EVALUATIONS_PATH);
        System.out.println("Evaluation ajoutée dans la JTABLE !");        
        Globals.evaluationSelectionnee = eval;
    }//GEN-LAST:event_ajouterEvalBActionPerformed

    private void modifEvalBClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifEvalBClick
        // TODO add your handling code here:
        if(Globals.evaluationSelectionnee == null)
            System.out.println("(!) Impossible de mettre à jour l'évaluation car NullReferenceException");
        Globals.eleveSelectionne.getEvaluations().remove(Globals.evaluationSelectionnee);
        majRapideEvaluation();
        CSV_Loader.majEvaluations(evalEnCours, Globals.evaluationSelectionnee, CSV_Loader.EVALUATIONS_PATH);
        Globals.eleveSelectionne.add(Globals.evaluationSelectionnee);
         GenerateurEvaluationsOperations operations = new GenerateurEvaluationsOperations();
         operations.afficherEvaluationsEleve(Globals.eleveSelectionne, evalsTable, mainLabel);
    }//GEN-LAST:event_modifEvalBClick

    private void noteTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noteTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noteTFActionPerformed

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
            java.util.logging.Logger.getLogger(GenerateurEvaluations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateurEvaluations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateurEvaluations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateurEvaluations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateurEvaluations().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouterEvalB;
    private javax.swing.JTextField correcteurTF;
    private javax.swing.JTable evalsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JTextField matTF;
    private javax.swing.JButton modifEvalB;
    private javax.swing.JTextField noteTF;
    private javax.swing.JButton suppEvalB;
    private javax.swing.JTextField typeTF;
    // End of variables declaration//GEN-END:variables

    private void majRapideEvaluation() 
    {
        Evaluation eval = new Evaluation();
        String[] prenomNom =  correcteurTF.getText().split(" ");
        Professeur p = Professeur.trouverProfesseur(prenomNom[1], prenomNom[0]);
        if(p == null) p = new Professeur(prenomNom[1], prenomNom[0]);
        float note = Float.parseFloat(noteTF.getText().replace(',', '.'));
        Matiere mat = Matiere.trouverMatiere(matTF.getText());
        if(mat == null) mat = new Matiere(matTF.getText());
        String typeNote  =  typeTF.getText();
        eval.setEleve(Globals.eleveSelectionne);
        eval.setEvalType(typeNote);
        eval.setMat(mat);
        eval.setNote(note);
        eval.setProf(p);
        Globals.evaluationSelectionnee = eval;
    }
}

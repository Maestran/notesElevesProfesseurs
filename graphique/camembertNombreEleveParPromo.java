/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteselevesprofesseurs.graphique;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import notesElevesProfesseurs.Eleve;
import notesElevesProfesseurs.Promotion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Tristan
 */
public class camembertNombreEleveParPromo extends JFrame {
     
     private JPanel pan;
    
    public camembertNombreEleveParPromo(ArrayList<Promotion> listePromos)
    {    
        
        addWindowListener(new WindowAdapter(){
           
            @Override
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });
        pan = new JPanel(new BorderLayout());
        setContentPane(pan);
        setSize(800,450);
        
     
        DefaultPieDataset pie = new DefaultPieDataset();
        
        for(Promotion a : listePromos)
        {
            pie.setValue("Promotion " + a.getNom() + " (" + a.getEleves().size() + ")", a.getEleves().size());
        }
        
        JFreeChart chart = ChartFactory.createPieChart("Nombre d'élèves par promotion : ",
        pie, true, true, true);
        
        ChartPanel cPan = new ChartPanel(chart);
    
        pan.add(cPan);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}

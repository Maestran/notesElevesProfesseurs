/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteselevesprofesseurs.graphique;

import notesElevesProfesseurs.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


/**
 *
 * @author Tristan
 */

public class camembertAppreciation extends JFrame {
    
    private JPanel pan;
    int[] appreciation = new int[5];
    
    public camembertAppreciation(Promotion prom)
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
        
        for(Eleve a : prom.getEleves())
        {
            if(a.calculMoyenne() < 10) appreciation[0]++;
            if(a.calculMoyenne() < 12) appreciation[1]++;
            if(a.calculMoyenne() < 14) appreciation[2]++;
            if(a.calculMoyenne() < 16) appreciation[3]++;
            if(a.calculMoyenne() < 18) appreciation[4]++;
        }
      
        DefaultPieDataset pie = new DefaultPieDataset();
        
        
        pie.setValue("Non validé (" + appreciation[0] + ")" , appreciation[0]);
        pie.setValue("Validé (" + appreciation[1] + ")" , appreciation[1]);
        pie.setValue("Assez bien (" + appreciation[2] + ")" , appreciation[2]);
        pie.setValue("Bien (" + appreciation[3] + ")" , appreciation[3]);
        pie.setValue("Très bien (" + appreciation[4] + ")" , appreciation[4]);
        
        JFreeChart chart = ChartFactory.createPieChart("Résultat dans la promotion " + prom.getNom(),
        pie, true, true, true);
        
  
        
        ChartPanel cPan = new ChartPanel(chart);
    
        pan.add(cPan);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}

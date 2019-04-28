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
import notesElevesProfesseurs.Matiere;
import notesElevesProfesseurs.Promotion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Tristan
 */
public class diagrammeBatonMoyennePromotion extends JFrame {
    
     private JPanel pan;
    
    
    diagrammeBatonMoyennePromotion(ArrayList<Promotion> listePromos)
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
        setSize(1000,700);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(Promotion p : listePromos)
        {
            dataset.addValue(p.obtenirMoyennePromotion(), "Note moyenne", "Promo "+p.getNom());
        }
        
        
        JFreeChart res = ChartFactory.createBarChart("Moyenne des promotions : ", "",
        "Note Obtenue", dataset, PlotOrientation.VERTICAL, true, true, false);
        
        CategoryPlot plot = (CategoryPlot) res.getPlot();

        org.jfree.chart.axis.ValueAxis rageAxis = plot.getRangeAxis();
        rageAxis.setRange(new Range(0, 20));
        
         ChartPanel cPan = new ChartPanel(res);
    
        pan.add(cPan);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}

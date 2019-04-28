/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteselevesprofesseurs.graphique;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javafx.scene.chart.ValueAxis;
import javax.swing.JFrame;
import javax.swing.JPanel;
import notesElevesProfesseurs.*;
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
public class diagrammeBatonNoteMatiere extends JFrame {
    
    
    private JPanel pan;
    int[] val = new int[21];
    
    diagrammeBatonNoteMatiere(Matiere m)
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
        
        
        for(int i = 0; i < m.allNote.size(); i++)
        {
            val[ Math.round(m.allNote.get(i)) ]++;
        }
        
        for(int i = 0; i <= 20; i++)
        {
            dataset.addValue(val[i],"", ""+i);
        }
        
        JFreeChart res = ChartFactory.createBarChart("Note pour la matière : " + m.getNom(), "Note Obtenue",
        "Nombre d'élève", dataset, PlotOrientation.VERTICAL, true, true, false);
        
        CategoryPlot plot = (CategoryPlot) res.getPlot();

        org.jfree.chart.axis.ValueAxis rageAxis = plot.getRangeAxis();
        rageAxis.setRange(new Range(0, 10));
        
         ChartPanel cPan = new ChartPanel(res);
    
        pan.add(cPan);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
}
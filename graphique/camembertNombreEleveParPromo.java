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
 * Cette classe va créer un camembert avec le nombre d'élève par promotion
 */
public class camembertNombreEleveParPromo extends JFrame {
     
     private JPanel pan;
    
    public camembertNombreEleveParPromo(ArrayList<Promotion> listePromos)// On doit appeler la classe avec toutes les promotions en paramètre, et elle crée automatiquement une fenetre avec le camembert
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

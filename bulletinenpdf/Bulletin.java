/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletinenpdf;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Tristan
 */
public class Bulletin {
    
    private Eleve eleve;
    public static String DEST;
    
    Bulletin()
    {
                
    }
    
    Bulletin(Eleve a)
    {
        eleve = a;
        DEST = "resultats/" + eleve.getPromotion().getNom() + "/" + eleve.getId() + ".pdf";
    }
    
    public void creerBulletin() throws IOException
    {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        this.createPdf(DEST);
    }
       
    
    public void createPdf(String DEST)throws IOException {
    
    PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
    Document document = new Document(pdf);

    creationStructureBulletin(document);
    document.close();
    
    }
    
    private void creationStructureBulletin(Document document)
    {
        document.add(new Paragraph(eleve.getPrenom() + "\n"));
        document.add(new Paragraph(eleve.getNom()+ "\n"));
        document.add(new Paragraph("Promotion " + eleve.getPromotion().getNom()+ "\n\n\n"));
        
        Paragraph para = new Paragraph("BULLETIN DE NOTE");
        para.setFontSize(18);
        para.setTextAlignment(TextAlignment.CENTER);
        
        document.add(para);
        
        Table table = new Table(7);
        
        Cell cell = new Cell(1, 7);
        cell.setTextAlignment(TextAlignment.CENTER);
        cell.add("Résultats");
        cell.setFontSize(15);
        table.addCell(cell);

        table.addCell("Matière");
        table.addCell("Note");
        table.addCell("Moyenne Promo");
        table.addCell("Mediane Promo");
        table.addCell("Minimum Promo");
        table.addCell("Maximum Promo");
        table.addCell("Correcteur");
       
        ajoutNoteMatière(table);
        document.add(table);
        ajoutLigneFinal(document);
    }
    
    private void ajoutNoteMatière(Table table)
    {
        for(Evaluation a : eleve.getEvaluations())
        {
            table.addCell(a.getMat().getNom() + " " + a.getEvalType());
            table.addCell("" + a.getNote());
            table.addCell("" + a.getMat().moyenne);
            table.addCell("" + a.getMat().mediane);
            table.addCell("" + a.getMat().noteMin);
            table.addCell("" + a.getMat().noteMax);
            table.addCell(a.getProf().getNom());
        }
    }
    
    private void ajoutLigneFinal(Document doc)
    {
        float value = eleve.calculMoyenne();
        doc.add(new Paragraph("\n\n"));
        String appreciation;
        if(value < 10)
        {
            appreciation = "Non validé";
        }
        else if(value < 12)
        {
            appreciation =  "Validé";
        }
        else if(value < 14)
        {
            appreciation = "Validé mention Assez bien";
        }
        else if(value < 16)
        {
            appreciation = "Validé mention bien";
        }
        else
        {
            appreciation = "Validé mention très bien";
        }
        
        doc.add(new Paragraph("Moyenne générale : " + value + "  " + appreciation));
        
        doc.add(new Paragraph("Moyenne de la Promotion : " + eleve.getPromotion().obtenirMoyennePromotion() + "\n"));
        doc.add(new Paragraph("Minimum de la Promotion : " + eleve.getPromotion().obtenirMinimumPromotion() + "\n"));
        doc.add(new Paragraph("Maximum de la Promotion : " + eleve.getPromotion().obtenirMaximumPromotion() + "\n"));
        
    }
}

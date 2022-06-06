package com.example.exoga.utils;

import com.example.exoga.entities.Categorie;
import com.example.exoga.entities.Produit;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ProduitPDFExporter {
    List<Produit> listProduits;

    public ProduitPDFExporter(List<Produit> listProduits) {
        this.listProduits = listProduits;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Produit ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Nom", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantité", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Disponibilté", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date de création", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date de modification", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Catégorie", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Produit produit : listProduits) {
            table.addCell(String.valueOf(produit.getIdProduit()));
            table.addCell(produit.getNom());
            table.addCell(String.valueOf(produit.getQt()));
            table.addCell(String.valueOf(produit.isDisponible()));
            table.addCell(produit.getDateCreation());
            table.addCell(produit.getDateModif());
            table.addCell(produit.getCategorie().getNom());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Liste des Produits", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 1.5f, 3.0f, 3.0f, 3.0f, 3.5f});
        table.setSpacingBefore(8);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}

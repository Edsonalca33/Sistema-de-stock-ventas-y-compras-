/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenerarRep;

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import notaCredito.ReportesAvanzadosDTO;

/**
 *
 * @author User
 */
public class GenerarReporte {

    public void generandoReporte(List<ReportesAvanzadosDTO> reportes) {
          try {
            // Crear un nuevo Document con un Rectangle para establecer márgenes
            Document document = new Document(new Rectangle(612, 792), 50, 50, 50, 50);
            // Crear un nuevo PdfWriter
            PdfWriter.getInstance(document, new FileOutputStream("reporte.pdf"));
            // Abrir el documento
            document.open();
            // Agregar título al documento
            Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
            Paragraph title = new Paragraph("Reporte de Reportes Avanzados", fontTitle);
            title.setAlignment(Element.ALIGN_LEFT);
            document.add(title);
            // Crear tabla con la información de los ReportesAvanzadosDTO
            PdfPTable table = new PdfPTable(2); // 2 columnas
            table.setWidthPercentage(100);
            // Definir borde de las celdas
            PdfPCell cell = new PdfPCell();
            cell.setBorderColor(new BaseColor(0, 0, 0));
            cell.setPadding(5);
            // Definir títulos de las columnas
            Font fontHeaders = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            PdfPCell cellId = new PdfPCell(new Paragraph("ID", fontHeaders));
            cellId.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellId.setBorder(cell.getBorder());
            table.addCell(cellId);
            PdfPCell cellDinero = new PdfPCell(new Paragraph("Dinero", fontHeaders));
            cellDinero.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellDinero.setBorder(cell.getBorder());
            table.addCell(cellDinero);
            // Agregar filas de datos a la tabla
            for (ReportesAvanzadosDTO reporte : reportes) {
                PdfPCell cell1 = new PdfPCell(new Paragraph(String.valueOf(reporte.getId())));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBorder(cell.getBorder());
                table.addCell(cell1);
                PdfPCell cell2 = new PdfPCell(new Paragraph(reporte.getDinero()));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setBorder(cell.getBorder());
                table.addCell(cell2);
            }
            // Agregar tabla al documento
            document.add(table);
            // Cerrar el documento
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

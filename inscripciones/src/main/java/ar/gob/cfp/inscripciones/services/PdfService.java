package ar.gob.cfp.inscripciones.services;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.ObjetoNoEncontradoCfpException;

@Service
public class PdfService {
    
    protected Font fontTitulo;
    protected Font fontSubtitulo;
    protected Font fontRegular;
    protected Font fontRegularBold;
    
    public PdfService() {
        fontTitulo = FontFactory.getFont("Helvetica", 18F, Font.BOLD);
        fontSubtitulo = FontFactory.getFont("Helvetica", 14F, Font.BOLD);
        fontRegular = FontFactory.getFont("Helvetica", 10F, Font.NORMAL);
        fontRegularBold = FontFactory.getFont("Helvetica", 10F, Font.BOLD);
    }
    

    public byte[] getComprobanteInscripcion(Integer idInscripcion) throws CfpException {
        Document document = null;
        try {
            //llamo obtener inscripcion
            
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            document = new Document(PageSize.A4, 45, 45, 25, 40);
            PdfWriter writer = PdfWriter.getInstance(document, outStream);
            
            document.open();
            document.addCreator("CFP");
            document.addAuthor("CFP");
            document.addTitle("Comprobante de Inscripcion");
            
            Paragraph titulo = new Paragraph("Comprobante Inscripcion");
            titulo.setFont(fontTitulo);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setSpacingBefore(20);
            titulo.setSpacingAfter(10);
            document.add(titulo);
            
           
            
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);
            
            PdfPCell cell = new PdfPCell(new Paragraph("CURSO PROGRAMACION", fontSubtitulo));
            cell.setBackgroundColor(Color.WHITE);
            cell.setBorderWidth(1f);
            cell.setBorderColor(Color.GRAY);
            cell.setPadding(20.0f);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            tabla.addCell(cell);
            
            tabla.addCell(createPdfPCell("Nombre","Nahuel",fontRegular,Color.WHITE));
            tabla.addCell(createPdfPCell("Apellido","Roman",fontRegular,Color.WHITE));
            
            document.add(tabla);
            
            document.close();
            byte[] retorno = outStream.toByteArray();
            outStream.flush();
            return retorno;
            
            
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new ObjetoNoEncontradoCfpException("No hay inscripcion para el id solicitado");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CfpException("Ocurrio un error procesando comprobante. " + e.getMessage());
        }
    }
    
    
    protected PdfPCell createPdfPCell(String label,String text, Font font, Color color) throws DocumentException {
        
        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(100);
        float[] distribucion = {30f,70f};
        tabla.setWidths(distribucion);
        
        PdfPCell cell = new PdfPCell(new Paragraph(label, fontRegularBold));
        cell.setBorder(0);
        cell.setBackgroundColor(color);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.addCell(cell);
        
        cell = new PdfPCell(new Paragraph(text, font));
        cell.setBorder(0);
        cell.setBackgroundColor(color);
        cell.setPadding(2.0f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tabla.addCell(cell);
        
        PdfPCell cellTabla = new PdfPCell(tabla);
        cellTabla.setBorderWidth(1f);
        cellTabla.setPaddingTop(5.0f);
        cellTabla.setPaddingBottom(5.0f);
        cellTabla.setBorderColor(Color.GRAY);
        return cellTabla;
    }

}

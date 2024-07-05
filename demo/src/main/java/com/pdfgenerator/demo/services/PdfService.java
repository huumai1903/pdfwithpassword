package com.pdfgenerator.demo.services;

import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public byte[] createPdfWithPassword(String userPassword, String ownerPassword) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Thiết lập mã hóa
        PdfWriter writer = new PdfWriter(byteArrayOutputStream, new WriterProperties()
                .setStandardEncryption(userPassword.getBytes(), ownerPassword.getBytes(),
                        EncryptionConstants.ALLOW_PRINTING,
                        EncryptionConstants.ENCRYPTION_AES_128));

        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Thêm nội dung vào PDF
        document.add(new Paragraph("Hello, this is a secured PDF document!"));

        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}

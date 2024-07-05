package com.pdfgenerator.demo.controller;

import com.pdfgenerator.demo.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf(@RequestParam String userPassword, @RequestParam String ownerPassword) throws IOException {
        byte[] pdfContent = pdfService.createPdfWithPassword(userPassword, ownerPassword);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=secured-example.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }
}

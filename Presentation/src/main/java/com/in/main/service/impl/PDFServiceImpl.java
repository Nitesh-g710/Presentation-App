package com.in.main.service.impl;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.in.main.dto.RatingDto;
import com.in.main.service.PDFService;

import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PDFServiceImpl implements PDFService {

	@Override
	public byte[] generateRatingPDF(RatingDto rating) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Add content to PDF
            document.add(new Paragraph("Rating Summary"));
            document.add(new Paragraph("User: " + rating.getUser().getName()));
            document.add(new Paragraph("Presentations ID: " + rating.getPresentation().getPid()));
            document.add(new Paragraph("Communication: " + rating.getCommunication()));
            document.add(new Paragraph("Confidence: " + rating.getConfidence()));
            document.add(new Paragraph("Content: " + rating.getContent()));
            document.add(new Paragraph("Interaction: " + rating.getInteraction()));
            document.add(new Paragraph("Liveliness: " + rating.getLiveliness()));
            document.add(new Paragraph("Usage of Props: " + rating.getUseageProps()));
            document.add(new Paragraph("Total Score: " + rating.getTotalScore()));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
	}

}

package com.project.desktopsearchengine.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDF implements FileHandler {

	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc ;
	private COSDocument cosDoc ;
	private String Text ;
	private File file;

	public PDF() {

	}
	public HashMap<String, Integer> readFileAndGetWords(String filePath)
	{
		this.pdfStripper = null;
		this.pdDoc = null;
		this.cosDoc = null;

		file = new File(filePath);
		try {
			parser = new PDFParser(new RandomAccessFile(file,"r"));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			pdDoc.getNumberOfPages();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(pdDoc.getNumberOfPages());
			Text = pdfStripper.getText(pdDoc);
			
		} catch (FileNotFoundException ex) {
			Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
		} 
		
		return null;
	}
}

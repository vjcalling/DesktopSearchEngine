package com.project.desktopsearchengine.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.project.desktopsearchengine.utilities.Filter;
import com.project.desktopsearchengine.utilities.Utilities;

public class PDF extends FileHandler {

	private PDFParser parser;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc ;
	private COSDocument cosDoc ;
	private String Text ;
	public static final String DELIMITER = " ";
	private File file;

	public PDF() {

	}
	public HashMap<String, Integer> readFileAndGetWords(String filePath)
	{
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
		this.pdfStripper = null;
		this.pdDoc = null;
		this.cosDoc = null;
		int i=1;
		String lines[];
		Utilities util = new Utilities();
		Filter filter = new Filter();
		LinkedList<String> filteredWords;
		String[] wordsInLine = null;

		file = new File(filePath);
		try {
			parser = new PDFParser(new RandomAccessFile(file,"r"));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);

			//getting words page by page to handle OOM in case of huge PDFs
			while(i <= (pdDoc.getNumberOfPages())){
				filteredWords = new LinkedList<String>();
				pdfStripper.setStartPage(i);
				pdfStripper.setEndPage(i);
				Text = pdfStripper.getText(pdDoc);
				lines = Text.split("\n");

				for(String line : lines){
					
					wordsInLine = line.split(DELIMITER);
					filter.filterWords(wordsInLine,filteredWords);
					util.populateWordCountHashMap(filteredWords, wordCount);
				}
				i++;
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
		} 

		return wordCount;
	}
}

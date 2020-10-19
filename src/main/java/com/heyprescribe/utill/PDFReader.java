package com.heyprescribe.utill;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.heyprescribe.base.BasePage;

public class PDFReader extends BasePage {

	public String readPDFInURL(String appUrl) throws IOException {
		URL url = new URL(appUrl);
		InputStream input = url.openStream();
		BufferedInputStream fileToParse = new BufferedInputStream(input);
		PDDocument document = null;
		String output = null;

		try {
			document = PDDocument.load(fileToParse);
			output = new PDFTextStripper().getText(document);

		} finally {
			if (document != null) {
				document.close();
			}
			fileToParse.close();
			// is.close();
		}
		return output;
	}

}

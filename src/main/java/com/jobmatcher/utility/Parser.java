package com.jobmatcher.utility;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;

/**
 * Created by gevlad on 19-Jan-17.
 */
public class Parser {

    public static String parse(String fileName){

        String st = null;

        try {
            PDDocument document = null;
            document = PDDocument.load(new File(fileName));
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                st = Tstripper.getText(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return st;
    }
}



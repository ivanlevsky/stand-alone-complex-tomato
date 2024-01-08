package com.java.common.filesystem;

import com.java.common.GlobalParams;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;

public class WordUtils {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream(new File(GlobalParams.wordReport+"demo2.docx"));
        FileOutputStream out = new FileOutputStream(new File(GlobalParams.wordReport+"demo3.docx"));

        XWPFDocument read_document= new XWPFDocument(in);
        XWPFDocument write_document = new XWPFDocument();

        for (XWPFParagraph para: read_document.getParagraphs()) {
//            System.out.println(para.getText());
            XWPFParagraph wpara = write_document.createParagraph();
            for(XWPFRun ire: para.getRuns()){
//                System.out.println(ire);
                XWPFRun run=wpara.createRun();
                run.setText(ire.text());
                run.setFontSize(ire.getFontSize());
                run.setBold(ire.isBold());
                run.setUnderline(ire.getUnderline());
                run.setItalic(ire.isItalic());
                run.setColor(ire.getColor());
                run.setFontFamily(ire.getFontFamily());
            }
        }


        write_document.write(out);
    }
    private static String getWordHighlightText(String wordFilePath) {
			String highlightText = "";
			FileInputStream fis = null;
			XWPFDocument doc = null; 
			try {
				fis = new FileInputStream(wordFilePath);
				doc = new XWPFDocument(fis); 
				for (XWPFParagraph paragraph : doc.getParagraphs()) {  
				    for (XWPFRun run : paragraph.getRuns()) {  
				        if (run.isHighlighted()) {
				        	highlightText += run.getText(0) + System.lineSeparator();
				        }
				    }  
				}  
				fis.close();  
				doc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();  
					doc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return highlightText;
	}  
}

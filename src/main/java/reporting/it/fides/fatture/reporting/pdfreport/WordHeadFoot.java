package it.fides.fatture.reporting.pdfreport;

import java.util.*;
import java.io.*;
import java.math.*;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.model.*;
import org.apache.poi.xwpf.usermodel.*;   
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

public class WordHeadFoot {

	public static void main(String[] args) throws InvalidFormatException, FileNotFoundException, IOException {
		
		writeFootandHead();
	
	}
	
	
	public static void writeFootandHead() throws InvalidFormatException, FileNotFoundException, IOException {
		      //Blank Document
		      XWPFDocument document= new XWPFDocument(); 
		      
		      // create header-footer
		      XWPFHeaderFooterPolicy headerFooterPolicy = document.createHeaderFooterPolicy();
//		      XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
//		      if (headerFooterPolicy == null) headerFooterPolicy = document.createHeaderFooterPolicy();

		      // create header start
		      XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

		      XWPFParagraph paragraphHeader = header.createParagraph();
		      paragraphHeader.setAlignment(ParagraphAlignment.LEFT);

		      XWPFRun runHeader = paragraphHeader.createRun();  
		      String imgLogoFile= "src/images/logo_Fides.png";
		      runHeader.addPicture(new FileInputStream(imgLogoFile), XWPFDocument.PICTURE_TYPE_PNG, imgLogoFile, Units.toEMU(102), Units.toEMU(42));
		      
		      //create footer start
		      XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
		      
		      XWPFParagraph paragraphFooter = footer.createParagraph();
		      paragraphFooter.setAlignment(ParagraphAlignment.LEFT);
		      
		      //create table
		      XWPFTable tableFooter = footer.createTable(1, 3);
		      //set cell width
		      CTTblWidth width = tableFooter.getCTTbl().addNewTblPr().addNewTblW();
		      width.setType(STTblWidth.DXA);
		      width.setW(BigInteger.valueOf(10000));
		      //bordless table
		      tableFooter.getCTTbl().getTblPr().unsetTblBorders();
		      
		      String[] prima = {"Fides Consulting S.r.l", "Via Motta Casa dei Miri, 21", "Gragnano (Na) - 80054", "C.F. e P.IVA 05131731217", "Cap. Sociale 90.000,00", "Rea Napoli 735203"};
		      String[] seconda  = {"Sede Amministrativa", "Centro Direzionale Is. E7" , "80143 - Napoli (Italy)", "Phone +39 081 195 02 985 ", "Fax +39 081 195 02 948 ", "www.fides.it | info@fides.it"};
		      String[] terza = {"Unità  Locale", "Viale Monza, 1", "20125 - Milano", "Phone +39 02 49 76 39 67", "Fax +39 02 49 76 39 85"};
		      
		      //create first row
		      XWPFTableRow tableFooterRowOne = tableFooter.getRow(0);
		      XWPFRun firstCellParHeader = tableFooterRowOne.getCell(0).addParagraph().createRun();
		      firstCellParHeader.removeBreak();
		      XWPFRun firstCellParBody = tableFooterRowOne.getCell(0).addParagraph().createRun();

		      firstCellParHeader.setColor("1874CD");
	    	  firstCellParHeader.setText(prima[0]);
	    	  firstCellParHeader.setFontFamily("Open Sans");
	    	  firstCellParHeader.setFontSize(9);
	    	  firstCellParBody.setFontFamily("Open Sans");
	    	  firstCellParBody.setFontSize(8);
	    	  
		      for(int i = 1; i < prima.length; i++) {		    	  
		    	  firstCellParBody.setColor("000000");
		    	  firstCellParBody.setText(prima[i]);
		    	  firstCellParBody.addBreak();  
		      }
		      
		      XWPFRun SecondCellParHeader = tableFooterRowOne.getCell(1).addParagraph().createRun();
		      XWPFRun SecondCellParBody = tableFooterRowOne.getCell(1).addParagraph().createRun();
		      
		      SecondCellParHeader.setColor("1874CD");
		      SecondCellParHeader.setText(seconda[0]);
		      SecondCellParHeader.setFontFamily("Open Sans");
		      SecondCellParHeader.setFontSize(9);
	    	  SecondCellParBody.setColor("000000");
	    	  SecondCellParBody.setFontFamily("Open Sans");
	    	  SecondCellParBody.setFontSize(8);
	    	  
		      for(int i = 1; i < seconda.length; i++) {
		    	  SecondCellParBody.setText(seconda[i]); 
		    	  SecondCellParBody.addBreak();
		      }
		      
		      XWPFRun ThirdCellParHeader = tableFooterRowOne.getCell(2).addParagraph().createRun();
		      XWPFRun ThirdCellParBody = tableFooterRowOne.getCell(2).addParagraph().createRun();
		      
		      ThirdCellParHeader.setColor("1874CD");
		      ThirdCellParHeader.setText(terza[0]);
		      ThirdCellParHeader.setFontFamily("Arial");
		      ThirdCellParHeader.setFontSize(9);
		      
	    	  ThirdCellParBody.setColor("000000");
	    	  ThirdCellParBody.setFontFamily("Open Sans");
		      ThirdCellParBody.setFontSize(8);
		      for(int i = 1; i < terza.length; i++) {
		    	  ThirdCellParBody.setText(terza[i]);
		    	  ThirdCellParBody.addBreak();
		      }
		      
		      //add image
		      XWPFRun addNewCell = tableFooter.getRow(0).addNewTableCell().addParagraph().createRun();
		      String imgAccredia= "src/images/accredia.jpg";
		      XWPFPicture accrediaCell = addNewCell.addPicture(new FileInputStream(imgAccredia), XWPFDocument.PICTURE_TYPE_JPEG, imgAccredia, Units.toEMU(61), Units.toEMU(78));
		      String imgKiwa= "src/images/kiwa.jpg";
		      XWPFPicture kiwaCell = addNewCell.addPicture(new FileInputStream(imgKiwa), XWPFDocument.PICTURE_TYPE_JPEG, imgAccredia, Units.toEMU(61), Units.toEMU(96));

		      
		      //Write the Document in file system
		      FileOutputStream out = new FileOutputStream(new File("CreateDoc.docx"));
		      document.write(out);
		      out.close();
		      System.out.println("createdocument.docx written successully");
		      document.close();
		      //document.close();
		}
	
}


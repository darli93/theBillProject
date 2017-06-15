package it.fides.fatture.reporting.pdfreport;

//import java.util.*;
import java.io.*;
import java.math.*;

//import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.*;
//import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.model.*;
import org.apache.poi.xwpf.usermodel.*;   
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

public class WordHeadFoot {
	
	private static XWPFDocument document = new XWPFDocument(); 
	public static XWPFDocument getDocument() {
		return document;
	}

	public static void setDocument(XWPFDocument document) {
		WordHeadFoot.document = document;
	}

	public static XWPFHeaderFooterPolicy getHeaderFooterPolicy() {
		return headerFooterPolicy;
	}

	public static void setHeaderFooterPolicy(XWPFHeaderFooterPolicy headerFooterPolicy) {
		WordHeadFoot.headerFooterPolicy = headerFooterPolicy;
	}

	private static XWPFHeaderFooterPolicy headerFooterPolicy = document.createHeaderFooterPolicy();

	public static void main(String[] args) throws InvalidFormatException, FileNotFoundException, IOException {
		
		XWPFHeader header = WordHeader();
		XWPFFooter footer = WordFooter();
		
		FileOutputStream out = new FileOutputStream(new File("CreateDoc.docx"));
	    document.write(out);
	    out.close();
	    System.out.println("createdocument.docx written successully");
	    document.close();
		
	}
	
	public static XWPFHeader WordHeader() throws InvalidFormatException, FileNotFoundException, IOException {
		
		XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
	    XWPFParagraph paragraphHeader = header.createParagraph();
	    paragraphHeader.setAlignment(ParagraphAlignment.LEFT);
	    XWPFRun runHeader = paragraphHeader.createRun();  
	    String imgLogoFile= "src/images/logo_Fides.png";
	    runHeader.addPicture(new FileInputStream(imgLogoFile), XWPFDocument.PICTURE_TYPE_PNG, "Fides Digital", Units.toEMU(102), Units.toEMU(42));
		
	    return header;
	    
	}
	
	public static XWPFFooter WordFooter() throws InvalidFormatException, FileNotFoundException, IOException {
		
		XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
		XWPFParagraph paragraphFooter = footer.createParagraph();
	    paragraphFooter.setAlignment(ParagraphAlignment.LEFT);
	    
	    XWPFTable tableFooter = footer.createTable(1, 3);
	      //set cell width
	    CTTblWidth width = tableFooter.getCTTbl().addNewTblPr().addNewTblW();
	    width.setType(STTblWidth.DXA);
	    width.setW(BigInteger.valueOf(10000));
	      //bordless table
	    tableFooter.getCTTbl().getTblPr().unsetTblBorders();
	    tableFooter.setStyleID("MyStyle");
	      
	    String[] prima = {"Fides Consulting S.r.l", "Via Motta Casa dei Miri, 21", "Gragnano (Na) - 80054", "C.F. e P.IVA 05131731217", "Cap. Sociale 90.000,00", "Rea Napoli 735203"};				
	    String[] seconda  = {"Sede Amministrativa", "Centro Direzionale Is. E7" , "80143 - Napoli (Italy)", "Phone +39 081 195 02 985 ", "Fax +39 081 195 02 948 ", "www.fides.it | info@fides.it"};
	    String[] terza = {"Unit� Locale", "Viale Monza, 1", "20125 - Milano", "Phone +39 02 49 76 39 67", "Fax +39 02 49 76 39 85"};
		
	    XWPFTableRow tableFooterRowOne = tableFooter.getRow(0);
	    XWPFParagraph firstCellHeader = tableFooterRowOne.getCell(0).addParagraph();
	    firstCellHeader.setSpacingAfter(0);
	    XWPFRun firstCellParHeader = firstCellHeader.createRun();
	    XWPFParagraph firstCellBody = tableFooterRowOne.getCell(0).addParagraph();
	    XWPFRun firstCellParBody = firstCellBody.createRun();
	      
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
	      
	    XWPFParagraph SecondCellHeader = tableFooterRowOne.getCell(1).addParagraph();
	    SecondCellHeader.setSpacingAfter(0);
	    XWPFRun SecondCellParHeader = SecondCellHeader.createRun();
	    XWPFParagraph SecondCellBody = tableFooterRowOne.getCell(1).addParagraph();
	    XWPFRun SecondCellParBody = SecondCellBody.createRun();
	      
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
	      
	    XWPFParagraph ThirdCellHeader = tableFooterRowOne.getCell(2).addParagraph();
	    ThirdCellHeader.setSpacingAfter(0);
	    XWPFRun ThirdCellParHeader = ThirdCellHeader.createRun();
	    XWPFParagraph ThirdCellBody = tableFooterRowOne.getCell(2).addParagraph();
	    XWPFRun ThirdCellParBody = ThirdCellBody.createRun();
	      
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
	    XWPFParagraph addNewTableCellPar = tableFooter.getRow(0).addNewTableCell().addParagraph();
	    XWPFRun addNewCellRun = addNewTableCellPar.createRun();

	    addNewCellRun.addPicture(new FileInputStream("src/images/accredia.jpg"), XWPFDocument.PICTURE_TYPE_JPEG, "Accredia", Units.toEMU(61), Units.toEMU(78));
	    addNewCellRun.addPicture(new FileInputStream("src/images/kiwa.jpg"), XWPFDocument.PICTURE_TYPE_JPEG, "kiwa", Units.toEMU(61), Units.toEMU(96));
	      
		return footer;
		
	}

}
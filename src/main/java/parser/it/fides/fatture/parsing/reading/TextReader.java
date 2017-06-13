package it.fides.fatture.parsing.reading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.fides.fatture.parsing.datacontainers.Header;
import it.fides.fatture.parsing.datacontainers.TableData;
import it.fides.fatture.parsing.datacontainers.TheBill;
import it.fides.fatture.parsing.datacontainers.Body;
import it.fides.fatture.parsing.datacontainers.Footer;

public class TextReader {

private static Scanner input;
public static String nextLine = input.nextLine();

public static void main(String[] args) throws FileNotFoundException {
	
	read("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");
	
}
	
	public static TheBill read(String filePath) throws FileNotFoundException {
		
		TheBill fattura = new TheBill();
		Header head = new Header();	
		Body corpo = new Body();
		Footer foot = new Footer();
	    TableData tabella = new TableData();
		ArrayList<TableData> tableList = new ArrayList<TableData>();
		File file = new File(filePath);//("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");
		input = new Scanner(file);
		int iHead = 0;
		Boolean readyToReadBody = false;
		int firstBodyPart = 0;
		Boolean readyToReadTheTable = true;
		int lineNumber = 0;
		
		while(input.hasNext()) {
			//System.out.println(lineNumber);
			lineNumber++;
			
			//inizio della lettura e del reporting del head
			if(iHead < 9) {
				
				switch (iHead) {
			
				case 4:
					head.setFirstLine(nextLine);
					System.out.println(nextLine);
					break;
				case 5:
					head.setSecondLine(nextLine);
					System.out.println(nextLine);
					break;
				case 6:
					head.setThirdLine(nextLine);
					System.out.println(nextLine);
					break;
				case 7:
					head.setForthLine(nextLine);
					System.out.println(nextLine);
					break;
				case 8:
					head.setFifthLine(nextLine);
					System.out.println(nextLine);
					readyToReadBody = true;
					//fattura.setHead(head);
				default:
				break;
				}
			
			iHead++;
			
			} 
			
			if(readyToReadBody) {
				
				switch (firstBodyPart) {
				
				case 3:

					String wordtoFind = "DEL";
					Pattern word = Pattern.compile(wordtoFind);
					Matcher match = word.matcher(nextLine);
					    
				    while(match.find()){
					    	
					    String first = nextLine.substring(12, match.start());
					    String second = nextLine.substring(match.start() + 3);    
					    System.out.println(first.trim());
					    corpo.setBillFirstId(first);
					    System.out.println(second.trim());
					    corpo.setBillDateOfIssue(second);
						        
				    }

					break;
					
				case 4:
						
					String billDueDate = input.nextLine().substring(8);
					corpo.setBillDueDate(billDueDate.trim());
					System.out.println(billDueDate.trim());
					
					break;
					
				case 5: 
					
					corpo.setBillSecondId(nextLine);
					System.out.println(nextLine);
					readyToReadBody = false;
					
				default:
				break;
					
				}
				
				firstBodyPart++;

			}
			
			if(lineNumber > 19 && readyToReadTheTable == true){

				String qta = nextLine.substring(0, 8).trim();
				String description = nextLine.substring(8, 41).trim();
				String prezzo = nextLine.substring(41, 54).trim();
				String Imponibile = nextLine.substring(66, 80).trim();
				if(qta.contains(",")) {
					
					String newQta = qta.replace(",", ".");
					newQta = newQta.trim();
					double quantita = Double.parseDouble(newQta);
					System.out.println("QTA: " + quantita);
					tabella.setQuantita((int)quantita);
					System.out.println("Descrizione: " + description);
					tabella.setDescrizione(description);
					String newPrice = prezzo.replace(".", "");
					newPrice = prezzo.replace(',', '.');
					newPrice = newPrice.trim();
					double finalPrice = Double.parseDouble(newPrice);
					tabella.setPrice(finalPrice);
					System.out.println("Prezzo: " + finalPrice);
					String paid = Imponibile.replace(".", "");
					paid = paid.replace(',', '.');
					paid = paid.trim();
					double finalPay = Double.parseDouble(paid);
					System.out.println("Imponibile: " + finalPay);
					tabella.setImponibile(finalPay);
					
				}
				
			}
			
		     if(nextLine.trim().contains("IMPONIBILE   IVA")) {
		          //Hack to check the next line that is empty
		        
		    	 readyToReadTheTable = false;
		        	 
		        	 //Imponibile
		        	 String taxableIncomeString = input.next();
		             taxableIncomeString = taxableIncomeString.replace(".", "");
		             taxableIncomeString = taxableIncomeString.replace(",", ".");
		             Double taxableIncomeDouble = Double.parseDouble(taxableIncomeString);
		             System.out.println(taxableIncomeDouble);
		             //%iva
		             String vatRateString = input.next();
		             Double vatRateDouble = Double.parseDouble(vatRateString);
		             System.out.println(vatRateDouble);
		             
		     }
		      
		     if(nextLine.contains("MOD.PAG.")) {
		        
		          System.out.println(nextLine);
		          foot.setBankId(nextLine);
		          System.out.println(input.nextLine());
		          foot.setMethodOfPayment(nextLine);
		        
		     }
		     
		}
		
		return fattura;

	}
	
}
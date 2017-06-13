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
	
	static TheBill fattura = new TheBill();
	static Header head = new Header();	
	static Body corpo = new Body();
	static Footer foot = new Footer();
	static TableData tabella = new TableData();
	private static int indice = 0;
	private static int lineNumber = 0;
	private static Boolean readyToReadTheTable = true;
	private static ArrayList<TableData> tableList = new ArrayList<TableData>();
	private static int firstBodyPart = 0;
	private static Scanner input;

public static void main(String[] args) throws FileNotFoundException {
	
	read("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");
	
}
	
	public static TheBill read(String filePath) throws FileNotFoundException {
		
		File file = new File(filePath);//("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");
		input = new Scanner(file);
		
		while(input.hasNext()) {
			
			lineNumber++;
			String nextLine = input.nextLine();
			
			HeadPart(nextLine.trim());	
			FirstBodyPart(nextLine);				
			tableList.add(TableBodyPart(nextLine));
		    LastBodyPart(nextLine);
		    FootPart(nextLine);
		
		}
		
		fattura.setHead(head);
		fattura.setCorpo(corpo);
		fattura.setFine(foot);
		
		return fattura;

	}
	
	public static void HeadPart(String testa) {

		if(indice < 9) {
		//System.out.println("sono qui "+ indice);
			switch (indice) {
		
			case 4:
				head.setFirstLine(testa);
				System.out.println(testa);
			break;
			case 5:
				head.setSecondLine(testa);
				System.out.println(testa);
			break;
			case 6:
				head.setThirdLine(testa);
				System.out.println(testa);
			break;
			case 7:
				head.setForthLine(testa);
				System.out.println(testa);
			break;
			case 8:
				head.setFifthLine(testa);
				System.out.println(testa);
				fattura.setHead(head);
			break;
			default:
				break;
		
			}
	
		indice++;
		
		}
		
	} 
	
	public static void FirstBodyPart(String nextLine) {
		
		if(indice == 9) {
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
				indice++;
			break;
			default:
				break;
			
			}
		
		firstBodyPart++;
		
		}
	
	}
	
	public static TableData TableBodyPart(String nextLine) {
		
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
		
		return tabella;
		
	}
	
	public static void LastBodyPart(String nextLine) {
		
		//Imponibile
		if(nextLine.trim().contains("IMPONIBILE   IVA")) {
	         
	     readyToReadTheTable = false;
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
	
	}
	
	public static void FootPart(String nextLine){
		
		if(nextLine.contains("MOD.PAG.")) {
	        
		   System.out.println(nextLine);
	       foot.setBankId(nextLine);
	       System.out.println(input.nextLine());
	       foot.setMethodOfPayment(nextLine);
		
		}
	
	}
	
}
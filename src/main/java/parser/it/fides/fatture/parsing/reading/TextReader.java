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
	static int indice = 0;
	private static int lineNumber = 0;
	private static Boolean readyToReadTheTable = true;
	private static ArrayList<TableData> tableList = new ArrayList<TableData>();
	private static int firstBodyPart = 0;
	private static Scanner input;
	
//	public static void main(String[] args) throws FileNotFoundException {
//		File file = new File("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");//("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");
//		input = new Scanner(file);
//		
//		while(input.hasNext()) {
//			
//			lineNumber++;
//			String nextLine = input.nextLine();
//			HeadPart(nextLine.trim());	
//			FirstBodyPart(nextLine);
//			if(lineNumber > 19 && readyToReadTheTable == true){
//				String qta = nextLine.substring(0, 8).trim();
//				String description = nextLine.substring(8, 41).trim();
//				String prezzo = nextLine.substring(41, 54).trim();
//				String sc = nextLine.substring(54,60).trim();
//				String Imponibile = nextLine.substring(66, 80).trim();
//				
//				if(qta.contains(",")) {
//				
//					String newQta = qta.replace(",", ".");
//					newQta = newQta.trim();
//					double quantita = Double.parseDouble(newQta);
//					System.out.println("QTA: " + quantita);
//					tabella.setQuantita((int)quantita);
//					System.out.println("Descrizione: " + description);
//					tabella.setDescrizione(description);
//					String newPrice = prezzo.replace(".", "");
//					newPrice = prezzo.replace(',', '.');
//					newPrice = newPrice.trim();
//					double finalPrice = Double.parseDouble(newPrice);
//					tabella.setPrice(finalPrice);
//					System.out.println("Prezzo: " + finalPrice);
//					String paid = Imponibile.replace(".", "");
//					paid = paid.replace(',', '.');
//					paid = paid.trim();
//					tabella.setSc(sc);
//					double finalPay = Double.parseDouble(paid);
//					System.out.println("Imponibile: " + finalPay);
//					tabella.setImponibile(finalPay);
//					tableList.add(tabella);
//			
//				}
//			
//			}
//			
//		    LastBodyPart(nextLine);
//		    FootPart(nextLine);
//
//		}
//		
//		corpo.setList(tableList);
//		fattura.setHead(head);
//		fattura.setCorpo(corpo);
//		fattura.setFine(foot);
//		System.out.println(corpo.getList().size());
//		System.out.println(lineNumber);
//		
//	}
	
	public TheBill read(String filePath) throws FileNotFoundException {
		
		File file = new File(filePath);//("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");
		input = new Scanner(file);
		
		while(input.hasNext()) {
			
			lineNumber++;
			String nextLine = input.nextLine();
			HeadPart(nextLine.trim());	
			FirstBodyPart(nextLine);				
			if(lineNumber > 19 && readyToReadTheTable == true) {
				
				TableData tabella = new TableData();
				String qta = nextLine.substring(0, 8).trim();
				String description = nextLine.substring(8, 41).trim();
				String prezzo = nextLine.substring(41, 54).trim();
				String sc = nextLine.substring(54,60).trim();
				String Imponibile = nextLine.substring(66, 80).trim();
				
				if(!nextLine.trim().isEmpty() && !nextLine.contains("IMPONIBILE")) {
					
					String newQta = qta.replace(",", ".");
					newQta = newQta.trim();
					double quantita = Double.parseDouble(newQta);
//					System.out.println("QTA: " + quantita);
					tabella.setQuantita((int)quantita);
//					System.out.println("Descrizione: " + description);
					tabella.setDescrizione(description);
					String newPrice = prezzo.replace(".", "");
					newPrice = prezzo.replace(',', '.');
					newPrice = newPrice.trim();
					double finalPrice = Double.parseDouble(newPrice);
					tabella.setPrice(finalPrice);
//					System.out.println("Prezzo: " + finalPrice);
					String paid = Imponibile.replace(".", "");
					paid = paid.replace(',', '.');
					paid = paid.trim();
					tabella.setSc(sc);
					double finalPay = Double.parseDouble(paid);
//					System.out.println("Imponibile: " + finalPay);
					tabella.setImponibile(finalPay); 
					//System.out.println(tabella.getDescrizione());
					tableList.add(tabella);
					
				}
				
			}
			
		    LastBodyPart(nextLine);
		    FootPart(nextLine);

		}
		
		//System.out.println(tableList.get(0).getDescrizione());
		System.out.println(tableList.size());
		
		corpo.setList(tableList);
		fattura.setHead(head);
		fattura.setCorpo(corpo);
		fattura.setFine(foot);
		//System.out.println(corpo.getList().size());
		
		return fattura;
		
	}
	
	public static void HeadPart(String testa) {

		if(indice < 9) {

			switch (indice) {
		
			case 4:
				head.setFirstLine(testa);
				//system.out.println(testa);
			break;
			case 5:
				head.setSecondLine(testa);
				//system.out.println(testa);
			break;
			case 6:
				head.setThirdLine(testa);
				//system.out.println(testa);
			break;
			case 7:
				head.setForthLine(testa);
				//system.out.println(testa);
			break;
			case 8:
				head.setFifthLine(testa);
				//system.out.println(testa);
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
					//system.out.println(first.trim());
					corpo.setBillFirstId(first);
					//system.out.println(second.trim());
					corpo.setBillDateOfIssue(second);
				        
				}

			break;
			case 4:
				
				String billDueDate = input.nextLine().substring(8);
				corpo.setBillDueDate(billDueDate.trim());
				//system.out.println(billDueDate.trim());
			
			break;
			
			case 5: 
			
				corpo.setBillSecondId(nextLine);
				//system.out.println(nextLine);
				indice++;
			break;
			default:
				break;
			
			}
		
		firstBodyPart++;
		
		}
	
	}
		
	public static void LastBodyPart(String nextLine) {

		if(nextLine.trim().contains("IMPONIBILE   IVA")) {
	         
			readyToReadTheTable = false;
			String taxableIncomeString = input.next();
			taxableIncomeString = taxableIncomeString.replace(".", "");
			taxableIncomeString = taxableIncomeString.replace(",", ".");
			Double taxableIncomeDouble = Double.parseDouble(taxableIncomeString);
			corpo.setTaxableIncome(taxableIncomeDouble);
			//system.out.println(taxableIncomeDouble);
			String vatRateString = input.next();
			Double vatRateDouble = Double.parseDouble(vatRateString);
			corpo.setVatRate(vatRateDouble);
			//system.out.println(vatRateDouble);
		
		}
	
	}
	
	public static void FootPart(String nextLine){
		
		if(nextLine.contains("MOD.PAG.")) {
	        
		   //system.out.println(nextLine);
	       foot.setBankId(nextLine);
	       //system.out.println(input.nextLine());
	       foot.setMethodOfPayment(input.nextLine());
	       System.out.println(foot.getMethodOfPayment());
		
		}
	
	}
	
}
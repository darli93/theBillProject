package it.fides.fatture.parsing.reading;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

import it.fides.fatture.parsing.datacontainers.Header;
import it.fides.fatture.parsing.datacontainers.Body;
import it.fides.fatture.parsing.datacontainers.Footer;

public class TextReader {
	
static Header head = new Header();	
static Body corpo = new Body();
static Footer foot = new Footer();

private static Scanner input;

public static void main(String[] args) throws FileNotFoundException {
	//System.out.println("bello");
	read();
		
}
	
	public static void read() throws FileNotFoundException {
		
		File file = new File("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");
		input = new Scanner(file);
		int iHead = 0;
		Boolean readyToReadBody = false;
		int firstBodyPart = 0;
		int lineNumber = 0;
		
		while(input.hasNext()) {
 System.out.println(lineNumber);
 lineNumber++;
			String nextLine = input.nextLine();
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
	
				default:
				break;
			}
			
			iHead++;
			
			
			//System.out.println(iHead);
			
			} 
			//System.out.println(firstBodyPart);
			if(readyToReadBody) {
				
				switch (firstBodyPart) {
				
				case 3:

						String wordtoFind = "DEL";
						Pattern word = Pattern.compile(wordtoFind);
					    Matcher match = word.matcher(nextLine);
					    String thisLine = nextLine;
					    
					    while(match.find()){
					    	
						    String first = thisLine.substring(12, match.start());
						    String second = thisLine.substring(match.start() + 3);
							
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
					
				default:
					break;
					
				}
				
				firstBodyPart++;

			}
			
		     if(nextLine.trim().contains("IMPONIBILE   IVA")) {
		          //Hack to check the next line that is empty
		         if(input.nextLine().trim().isEmpty()) {
		       	
		           System.out.println(input.nextLine());
		  
		        }

		     }
		      
		     if(nextLine.contains("MOD.PAG.")) {
		        
		        	System.out.println(nextLine);
		          foot.setBankId(nextLine);
		          System.out.println(input.nextLine());
		          foot.setMethodOfPayment(nextLine);
		        
		        }
	
		}
		
	}
	
}

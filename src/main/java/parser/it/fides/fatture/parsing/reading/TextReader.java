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

public class TextReader {
	
static Header head = new Header();	
static Body corpo = new Body();
private static Scanner input;

public static void main(String[] args) throws FileNotFoundException {
	//System.out.println("bello");
	read();
		
}
	
	public static void read() throws FileNotFoundException {
		
		File file = new File("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");
		input = new Scanner(file);
		int iHead = 0;
		int firstBodyPart = 0;
		
		while(input.hasNext()) {
			
			//String next = input.next();
			String nextLine = input.nextLine();
			//inizio della lettura e del reporting del head
			if(iHead <8) {
				
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
				default:
				break;
			}
			
			iHead++;
			//System.out.println(iHead);
			
			} else {
				
				//analisi del corpo della fattura
				switch (firstBodyPart) {
				case 0:
					
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

				default:
					break;
				}
				
				
				
			}
			
		}
	
	}
	
}

package it.fides.fatture.parsing.reading;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import it.fides.fatture.parsing.datacontainers.Header;
import it.fides.fatture.parsing.datacontainers.TheBill;

public class TextReaderTest {

	@Test
	public void testRead() throws FileNotFoundException {
		
		TheBill fattura = new TheBill();
		
		Header head = new Header();
  
		head.setFirstLine("SPETT.LE");
        head.setSecondLine("ACCENTURE TECHNOLOGY SOLUTIONS SRL");
        head.setThirdLine("VIA QUADRIO,17");
        head.setForthLine("20154 - MILANO - MI");
        head.setFifthLine("PIVA 03646450969");
		fattura.setHead(head);

		assertEquals(fattura.getHead().getSecondLine(), TheBill.readBill().getHead().getSecondLine());
		
	}

}

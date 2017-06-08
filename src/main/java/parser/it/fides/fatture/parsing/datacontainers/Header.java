package it.fides.fatture.parsing.datacontainers;

public class Header {
	
	private String firstLine = new String();
	private String secondLine = new String();
	private String thirdLine = new String();
	private String forthLine = new String();
	
	public String getFirstLine() {
		
		return firstLine;
	
	}
	
	public void setFirstLine(String firstLine) {
		
		this.firstLine = firstLine;
	
	}
	
	public String getSecondLine() {
	
		return secondLine;
	
	}
	
	public void setSecondLine(String secondLine) {
	
		this.secondLine = secondLine;
	
	}
	
	public String getThirdLine() {
	
		return thirdLine;
	
	}
	
	public void setThirdLine(String thirdLine) {
	
		this.thirdLine = thirdLine;
	
	}
	
	public String getForthLine() {
	
		return forthLine;
	
	}
	
	public void setForthLine(String forthLine) {
	
		this.forthLine = forthLine;
	
	}
}

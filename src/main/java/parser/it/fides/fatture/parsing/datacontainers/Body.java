package it.fides.fatture.parsing.datacontainers;

import java.util.ArrayList;

public class Body {
	
	private String billFirstId = new String();
	private String billDateOfIssue = new String();
	private String billDueDate = new String();
	private String billSecondId = new String();
	private ArrayList<TableData> list = new ArrayList<TableData>();
	private double taxableIncome;
	private double vatRate;
	
	public String getBillFirstId() {
		
		return billFirstId;
	
	}
	
	public void setBillFirstId(String billFirstId) {
	
		this.billFirstId = billFirstId;
	
	}
	
	public String getBillDateOfIssue() {
	
		return billDateOfIssue;
	
	}
	
	public void setBillDateOfIssue(String billDateOfIssue) {
	
		this.billDateOfIssue = billDateOfIssue;
	
	}
	
	public String getBillDueDate() {
	
		return billDueDate;
	
	}
	
	public void setBillDueDate(String billDueDate) {
	
		this.billDueDate = billDueDate;
	
	}
	
	public String getBillSecondId() {
	
		return billSecondId;
	
	}
	
	public void setBillSecondId(String billSecondId) {
	
		this.billSecondId = billSecondId;
	
	}

	public ArrayList<TableData> getList() {
	
		return list;
	
	}

	public void setList(ArrayList<TableData> list) {
	
		this.list = list;
	
	}
	
	public double getTaxableIncome() {
		return taxableIncome;
	}


	public void setTaxableIncome(double taxableIncome) {
		this.taxableIncome = taxableIncome;
	}


	public double getVatRate() {
		return vatRate;
	}


	public void setVatRate(double vatRate) {
		this.vatRate = vatRate;
	}


}
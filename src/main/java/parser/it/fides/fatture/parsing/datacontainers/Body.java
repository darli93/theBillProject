package it.fides.fatture.parsing.datacontainers;

public class Body {
	private String billFirstId = new String();
	private String billDateOfIssue = new String();
	private String billDueDate = new String();
	private String billSecondId = new String();
	
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

}

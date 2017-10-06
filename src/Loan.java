import java.util.Date;

abstract class Loan {
	protected Double amount;
	protected Date startDate;
	protected Date endDate;
	protected Integer tenureInYears;
	public String status;
	private int loanID;
	private static int loanIssueNumber = 1;

	public Loan(Double amount, Integer tenureInYears) {
		this.amount = amount;
		this.tenureInYears = tenureInYears;
		startDate = new Date();
		endDate = new Date();
		endDate.setYear((int) (startDate.getYear() + tenureInYears));
		status = "active";
		loanID = loanIssueNumber++;
	}
	
	abstract public String getLoanName();

	abstract public double getDueAmount();

	public String toString() {
		return "Loan Name: " + getLoanName() +
				" Loan ID: " + getLoanID() +
				" Loan Amount: " + getLoanAmount() +
				" Loan Start Date: " + getStartDate() +
				" End Date: " + getEndDate() +
				" Due Amount: " + getDueAmount();
		
	}

	abstract public double getLoanRate();

	public double getLoanAmount() {
		return amount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public int getLoanID() {
		return loanID;
	}
}

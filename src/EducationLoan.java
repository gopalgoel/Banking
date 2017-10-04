class EducationLoan extends Loan {
	public static String loanName = "\"Bright Future Education Loan\"";
	public static double rate = 10.0;
	private double amountDue;

	public EducationLoan(Double amount, Integer tenureInYears) {
		super(amount, tenureInYears);
		amountDue = amount + amount * tenureInYears * (rate) / 100.0;
	}

	public void showLoanDetails() {
		System.out.print("Loan Name: ");
		System.out.println(getLoanName());
		System.out.print("Loan ID: ");
		System.out.println(getLoanID());
		System.out.print("Loan Amount: ");
		System.out.println(getLoanAmount());
		System.out.print("Loan Start Date: ");
		System.out.println(getStartDate());
		System.out.print("End Date: ");
		System.out.println(getEndDate());
		System.out.print("Due Amount: ");
		System.out.println(getDueAmount());
	}

	public double getLoanRate() {
		return rate;
	}

	public String getLoanName() {
		return loanName;
	}

	public double getDueAmount() {
		return amountDue;
	}
}

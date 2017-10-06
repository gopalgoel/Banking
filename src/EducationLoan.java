class EducationLoan extends Loan {
	public static String loanName = "\"Bright Future Education Loan\"";
	public static double rate = 10.0;
	private double amountDue;

	public EducationLoan(Double amount, Integer tenureInYears) {
		super(amount, tenureInYears);
		amountDue = amount + amount * tenureInYears * (rate) / 100.0;
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

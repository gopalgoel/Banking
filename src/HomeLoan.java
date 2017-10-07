class HomeLoan extends Loan {
	public static String loanName = "Secure life Home loans";
	public static double rate = 15.0;
	private double amountDue;

	public HomeLoan(Double amount, Integer tenureInYears) {
		super(amount, tenureInYears);
		amountDue = amount + amount * tenureInYears * (rate) / 100;
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

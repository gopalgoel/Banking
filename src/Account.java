class Account {
	private Double balance = 0.0;
	private Double profit = 0.0;
	private Integer accountNumber;
	private String status;
	private static int nextAccountNumber = 16000;
	public static double rateOfInterest = 4.00;

	Account(Double balance) {
		this.balance = balance;
		accountNumber = nextAccountNumber++;
		if (balance >= 0)
			status = "active";
	}

	public void showAccountDetails() {
		System.out.print("Account Number: ");
		System.out.println(this.getAccountNumber());
		System.out.print("Current Balance: ");
		System.out.println(this.getBalance());
		System.out.print("Account Status: ");
		System.out.println(this.getStatus());
	}
	
	public void deposit() {
		Double amount;
		Integer input = 1;
		do {
			System.out.println("How much money you want to deposit?");
			amount = StdInReader.doubleInput();
			if (amount > 0) {
				double temp = balance;
				balance += amount;
				Bank.setTotalMoneyDeposited(Bank.getTotalMoneyDeposited() + amount);
				if (temp < 1000) {
					System.out.println(
							"Rs. 100 is deducted as your account balance was below \"Minimum Balance\" i.e Rs. 1000, before this transaction.");
					balance -= 100;
				}
				System.out.println("Your updated balance is: " + balance.toString());
				if (balance >= 0)
					status = "active";
				else
					status = "frozen";
				input = 0;
			} else {
				System.out.println("Amount can not be 0 or negative. Please enter a positive amount.");
				do {
					System.out.println("Press 1 to try again");
					System.out.println("Press 0 to go back to previous menu");
					input = StdInReader.integerInput();
					if (input == 1 || input == 0)
						break;
					else
						continue;
				} while (true);
			}
		} while (input != 0);
	}

	public void withdraw() {
		Double amount;
		int input = 0;
		do {
			System.out.println("How much money you want to withdraw?");
			amount = StdInReader.doubleInput();
			if (amount > 0) {
				if (amount <= balance + 1000) {
					balance -= amount;
					Bank.setTotalMoneyDeposited(Bank.getTotalMoneyDeposited() - amount);
					System.out.println("Your updated balance is: " + balance.toString());
					if (balance < 0)
						status = "frozen";
				} else {
					System.out.println("You donot have enough balance to withdraw");
				}
			} else {
				System.out.println("Amount can not be 0 or negative. Please enter a positive amount.");
				do {
					System.out.println("Press 1 to try again");
					System.out.println("Press 0 to go back to previous menu");
					input = StdInReader.integerInput();
					if (input == 1 || input == 0)
						break;
					else
						continue;
				} while (true);
			}
		} while (input != 0);
	}

	public String getStatus() {
		return status;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public Double getRateOfInterest() {
		return rateOfInterest;
	}

	public boolean grantProfit(double interest) {
		profit += interest;
		balance += interest;
		return true;
	}

	public void setBalance(double d) {
		balance = d;
	}
}

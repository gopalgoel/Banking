import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	public String toString() {
		return "Account Number: " + accountNumber +
				"\nCurrent Balance: " + balance + 
				"\nAccount Status: " + status;
	}

	private static Double takeDoubleInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Double temp = null;
		do {
			try {
				temp = Double.parseDouble(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter integer value.");
				continue;
			} catch (IOException e) {
			}
			break;
		} while (true);
		return temp;
	}

	private static Integer takeIntegerInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer temp = null;
		do {
			try {
				temp = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Please enter integer value.");
				continue;
			} catch (IOException e) {
			}
			break;
		} while (true);
		return temp;
	}

	public void deposit() {
		Double amount;
		Integer input = 1;
		do {
			System.out.println("How much money you want to deposit?");
			amount = takeDoubleInput();
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
					input = takeIntegerInput();
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
			amount = takeDoubleInput();
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
					input = takeIntegerInput();
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

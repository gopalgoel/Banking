import java.io.*;
import java.util.ArrayList;

class Customer extends User {
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private ArrayList<Loan> loanList = new ArrayList<Loan>();

	public Customer(String name, String userName, String password) {
		super(name, userName, password);
	}

	private String getStringInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = null;
		try {
			temp = br.readLine();
		} catch (IOException e) {
		}
		return temp;
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

	public Account getAccount() {
		Integer accountNumber;
		Account temp = null;
		Integer input = 1;
		do {
			System.out.println("Enter your account no");
			accountNumber = takeIntegerInput();
			for (Account account : accounts) {
				if (account.getAccountNumber() == accountNumber) {
					temp = account;
				}
			}
			if (temp == null) {
				System.out.println("No account with that account Number");
				System.out.println("Press 1 to try again");
				System.out.println("Press 0 to go back to previous menu");
				input = takeIntegerInput();
			} else {
				break;
			}
		} while (input != 0);
		return temp;
	}

	public void showAllLoanDetails() {
		System.out.print("Total Loans Issued are: ");
		System.out.println(loanList.size());
		for (Loan loan : loanList) {
			if (loan.status == "active")
				System.out.println(loan);
		}
	}

	public double getTotalLoanAmount() {
		double temp = 0;
		for (Loan loan : loanList) {
			temp += loan.amount;
		}
		return temp;
	}

	public void openNewAccount() {
		Double amount;
		// Input Amount
		System.out
				.println("How much money would you like to deposit now? Minimum amount to open an account is Rs.1000");
		amount = takeDoubleInput();
		if (amount < 1000)
			System.out.println("Sorry we cant open an account with amount " + amount.toString());
		else {
			System.out.println("Okay! You have " + amount.toString() + " as opening balance");
			Account temp = new Account(amount);
			if (accounts.add(temp)) {
				Bank.setTotalMoneyDeposited(Bank.getTotalMoneyDeposited() + amount);
				System.out.println("Account created.");
				System.out.println(temp);
			}
		}
	}

	public void issueNewLoan() {
		if (loanList.size() > 10) {
			System.out.println("Sorry! You cannot have more than 10 loans");
			return;
		} else if (accounts.size() == 0) {
			System.out.println("Sorry! You must have an account to apply for loan");
			return;
		}
		String type;
		Double amount;
		Integer tenureInYears;
		do {
			System.out.println(
					"Enter Type of your loan:\nEnter \"HomeLoan\" for Home Loan\nEnter \"EducationLoan\" for Education Loan");
			type = getStringInput();
			if (type.equals("HomeLoan") || type.equals("EducationLoan"))
				break;
			else {
				System.out.println("Please enter one of the two mentioned types.\n Note: Enter type without quotes.");
			}
		} while (true);
		do {
			System.out.println("Enter loan amount");
			amount = takeDoubleInput();
			if (amount <= 0) {
				System.out.println("Sorry! Amount must be positive");
				continue;
			} else if (this.getTotalLoanAmount() + amount > 1000000) {
				System.out
						.println("Sorry! We donot lend more than one million rupees per customer. Try a small amount.");
			} else if (amount > Bank.getCashInHand()) {
				System.out.println("Sorry Bank doesn't have enough funds. You make take loan of maximum Rs. "
						+ Bank.getCashInHand());
			} else {
				break;
			}
		} while (true);

		do {
			System.out.println("Enter loan duration in years");
			tenureInYears = takeIntegerInput();
			if (tenureInYears <= 0) {
				System.out.println("Sorry! Duration must be positive");
			} else {
				break;
			}
		} while (true);

		if (type.equals("HomeLoan")) {
			HomeLoan temp = new HomeLoan(amount, tenureInYears);
			if (loanList.add(temp)) {
				Bank.setTotalMoneyLent(Bank.getTotalMoneyLent() + amount);
				System.out.println("Your loan is issued");
				System.out.println(temp);
			}
		}
		if (type.equals("EducationLoan")) {
			EducationLoan temp = new EducationLoan(amount, tenureInYears);
			if (loanList.add(temp)) {
				Bank.setTotalMoneyLent(Bank.getTotalMoneyLent() + amount);
				System.out.println("Your loan is issued");
				System.out.println(temp);
			}
		}
	}

	public void payLoanDueAmount() {
		int loanID = takeLoanID();
		for (Loan loan : loanList) {
			if (loan.getLoanID() == loanID) {
				System.out.println("From which account you would like to pay money?");
				Account temp = getAccount();
				if (loan.getDueAmount() > temp.getBalance()) {
					System.out.println("Sorry! Not enough balance in your account");
				} else {
					temp.setBalance(temp.getBalance() - loan.getDueAmount());
					Bank.setTotalMoneyLent(Bank.getTotalMoneyLent() - loan.getLoanAmount());
					Bank.setTotalMoneyDeposited(Bank.getTotalMoneyDeposited() - loan.getDueAmount());
					Bank.setProfit(Bank.getProfit() + (loan.getDueAmount() - loan.getLoanAmount()));
					System.out.println("Loan succesfully paid off");
					loan.status = "inactive";
					loanList.remove(loan);
				}
				return;
			}
		}
		// if no loan with that loan id.
		System.out.println("No loan with that loan ID");
		return;
	}

	private int takeLoanID() {
		System.out.println("Enter the loan ID");
		int temp = takeIntegerInput();
		return temp;
	}

	public void showCustomerDetails() {

	}

	public Integer getNoOfAccount() {
		return accounts.size();
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public Integer getNoOfLoans() {
		return loanList.size();
	}
}

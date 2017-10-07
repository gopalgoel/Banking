import java.io.*;
import java.util.ArrayList;

class Bank {
	private Admin admin;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private static Double profit = 0.0;
	private static Double cashInitially = 10000000.0;
	private static Double cashInHand = cashInitially;
	private static Double totalMoneyDeposited = 0.0;
	private static Double totalMoneyLent = 0.0;
	private static boolean bankStarted = false;
	public static Bank bank;
	public static int customerID = 0;

	private Bank() {
		admin = new Admin("Gopal Goel", "admin", "admin");
		admin.setBank(this);
		bank = this;
		bankStarted = true;
		customers.add(new Customer("Gopal Goel", "g", "g"));
	}

	public static Bank getBank() {
		if (bankStarted == false) {
			return new Bank();
		} else
			return bank;
	}

	public static Double getProfit() {
		return profit;
	}

	public static void setProfit(Double profit) {
		Bank.profit = profit;
	}

	public static Double getCashInHand() {
		setCashInHand();
		return cashInHand;
	}

	public static Double getTotalMoneyDeposited() {
		return totalMoneyDeposited;
	}

	public static void setCashInHand() {
		cashInHand = cashInitially + totalMoneyDeposited - totalMoneyLent;
	}

	public static void setTotalMoneyDeposited(Double amount) {
		totalMoneyDeposited = amount;
	}

	public static void setTotalMoneyLent(Double amount) {
		totalMoneyLent = amount;
	}

	public void showBankDetails(Admin admin) {
		if (admin != this.admin)
			return;
		System.out.println("Total no. of customers: " + customers.size());
		System.out.println("Total no. of accounts: " + getAccountsCount());
		System.out.println("Total no. of loans: " + getLoansCount());
		System.out.println("Total money deposited: " + getTotalMoneyDeposited());
		System.out.println("Total money lent: " + getTotalMoneyLent());
		System.out.println("Total cash in hand: " + getCashInHand());
		System.out.println("Total profit earned: " + getProfit());
	}

	public static Double getTotalMoneyLent() {
		return totalMoneyLent;
	}

	public Integer getAccountsCount() {
		Integer count = 0;
		for (Customer cus : customers) {
			count += cus.getNoOfAccount();
		}
		return count;
	}

	public Integer getLoansCount() {
		Integer count = 0;
		for (Customer cus : customers) {
			count += cus.getNoOfLoans();
		}
		return count;
	}

	public void addCustomers() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name;
		String userName;
		String password;

		// input name
		do {
			System.out.println("Your full name please?");
			name = new String(br.readLine().toUpperCase());
			if (name.matches("[A-Z]+\\s[A-Z]+")) {
				System.out.println("Welcome " + name);
				break;
			} else {
				System.out.println("Sorry the format of name should be \"FirstName LastName\"");
			}
		} while (true);

		// input username
		do {
			System.out.println("Choose a username containing only letters.");
			userName = new String(br.readLine());
			if (userName.matches("[a-zA-Z]+")) {
				if (checkUsername(userName)) {
					System.out.println("Username already exists. Please Choose another.");
				} else {
					System.out.println("Your username is: " + userName);
					break;
				}
			} else {
				System.out.println("Invalid username.");
			}
		} while (true);

		// input password
		do {
			System.out.println("Choose a password.");
			password = new String(br.readLine());
			System.out.println("Type your chosen password again.");
			String temp = new String(br.readLine());
			if (password.equals(temp)) {
				System.out.println("Password chosen succesfully.");
				break;
			} else {
				System.out.println("Passwords didn't match.");
			}
		} while (true);

		Customer newCustomer = new Customer(name, userName, password);
		if (customers.add(newCustomer)) {
			System.out.println("You are now our customer.");
			newCustomer.showCustomerDetails();
		}
	}

	private boolean checkUsername(String userName) {
		for (Customer customer : customers) {
			if (customer.userName.equals(userName))
				return true;
		}
		return false;
	}

	public Customer customerLogin() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Customer temp = null;
		String userName;
		String password;
		Integer input = 1;
		do {
			System.out.println("Enter username");
			userName = new String(br.readLine());
			System.out.println("Enter password.");
			password = new String(br.readLine());
			temp = null;
			for (Customer cust : customers) {
				if (cust.userName.equals(userName) && cust.password.equals(password)) {
					temp = cust;
				}
			}
			if (temp == null) {
				System.out.println("username and password didnt match.");
				do {
					System.out.println("Press 1 to try again");
					System.out.println("Press 0 to go back to previous menu");
					try {
						input = Integer.parseInt(br.readLine());
					} catch (NumberFormatException e) {
						System.out.println("Select one of the above options.");
						continue;
					}
					break;
				} while (true);
			} else {
				System.out.println("Logging in..");
				break;
			}
		} while (input != 0);
		return temp;
	}

	public Admin adminLogin() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Admin temp = null;
		String userName;
		String password;
		Integer input = 1;
		do {
			System.out.println("Enter username");
			userName = new String(br.readLine());
			System.out.println("Enter password.");
			password = new String(br.readLine());
			temp = null;
			if (admin.getUserName().equals(userName) && admin.getPassword().equals(password)) {
				temp = admin;
			}
			if (temp == null) {
				System.out.println("username and password didnt match.");
				System.out.println("Press 1 to try again");
				System.out.println("Press 0 to go back to previous menu");
				input = Integer.parseInt(br.readLine());
			} else {
				System.out.println("Logging in..");
				break;
			}
		} while (input != 0);
		return temp;
	}
	public void showProfit(Admin admin) {
		System.out.println("Profit Earned till date is: " + getProfit());
	}

	public void grantInterest(Admin admin) {
		if (admin != this.admin)
			return;
		Double totalInterestGranted = 0.0;
		for (Customer cus : customers) {
			for (Account acc : cus.getAccounts()) {
				double interest = 0;
				interest = (acc.getBalance() * acc.getRateOfInterest()) / 100.0;
				if (acc.grantProfit(interest)) {
					totalInterestGranted += interest;
				}
			}
		}
		Bank.setTotalMoneyDeposited(Bank.getTotalMoneyDeposited() + totalInterestGranted);
		Bank.setProfit(Bank.getProfit() - totalInterestGranted);
		System.out.println("Interest Granted");
	}

	public void showAllAccounts(Admin admin) {
		if (admin != this.admin)
			return;
		for (Customer cus : customers) {
			for (Account acc : cus.getAccounts()) {
				System.out.println("Account No: " + acc.getAccountNumber() + " " + "Balance: " + acc.getBalance());
			}
		}
	}

	public void changeInterestRate(Admin admin) {
		if (this.admin == admin) {
			System.out.println("Enter new Interest Rate in %");
			Integer temp = StdInReader.integerInput();
			Account.rateOfInterest = temp;
			System.out.println("Rate of Interest is now " + Account.rateOfInterest + "% ");
		}
	}

	public void changeLoanRate(Admin admin) {
		if (this.admin == admin) {
			int temp = -1;
			while (true) {
				System.out.println("Press 1 to change Home Loan Rate");
				System.out.println("Press 2 to change Education Loan Rate");
				System.out.println("Press 0 to go back to previous menu");
				temp = StdInReader.integerInput();
				if (temp == 1) {
					System.out.println("Enter new Loan Rate in %");
					Double temp2 = StdInReader.doubleInput();
					HomeLoan.rate = temp2;
					System.out.println("Rate of Loan is now " + HomeLoan.rate + "% ");
					break;
				} else if (temp == 2) {
					System.out.println("Enter new Loan Rate in %");
					Double temp2 = StdInReader.doubleInput();
					EducationLoan.rate = temp2;
					System.out.println("Rate of Loan is now " + EducationLoan.rate + "% ");
					break;
				} else if (temp == 0) {
					break;
				} else {
					System.out.println("Please enter one of the given options");
				}
			}
		}
	}
}

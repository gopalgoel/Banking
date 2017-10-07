import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		System.out.println("===============================");
		System.out.println("Welcome to the Bank of Westeros");
		System.out.println("===============================");
		Bank icici = Bank.getBank();
		int input1 = 0;
		int input2 = 0;
		int input3 = 0;
		do {
			showStartMenu();
			input1 = StdInReader.integerInput();
			if (input1 == 1) {
				do {
					showUserMenu();
					input2 = StdInReader.integerInput();
					if (input2 == 1) {
						Customer cust = icici.customerLogin();
						if (cust == null) {
						} else {
							do {
								showCustomerMenu();
								input3 = StdInReader.integerInput();
								if (input3 == 1) { // open account
									cust.openNewAccount();
								} else if (input3 == 2) { // deposit money
									Account temp = cust.getAccount();
									if (temp == null) {
									} else {
										temp.deposit();
									}
								} else if (input3 == 3) {
									Account temp = cust.getAccount();
									if (temp == null) {
									} else {
										temp.withdraw();
									}
								} else if (input3 == 4) {
									Account temp = cust.getAccount();
									if (temp == null) {
									} else {
										temp.showAccountDetails();
									}
								} else if (input3 == 5) {
									cust.issueNewLoan();
								} else if (input3 == 6) {
									cust.payLoanDueAmount();
								} else if (input3 != 0) {
									System.out.println("Please select one of the above option");
								}
							} while (input3 != 0);
						}
					} else if (input2 == 2) {
						icici.addCustomers();
					} else if (input2 != 0) {
						System.out.println("Please select one of the above option");
					}
				} while (input2 != 0);
			} else if (input1 == 2) {
				Admin admin = icici.adminLogin();
				if (admin == null) {
				} else {
					do {
						showAdminMenu();
						input2 = StdInReader.integerInput();
						if (input2 == 1) {
							icici.showBankDetails(admin);
						} else if (input2 == 2) {
							icici.showProfit(admin);
						} else if (input2 == 3) {
							icici.showAllAccounts(admin);
						} else if (input2 == 4) {
							icici.grantInterest(admin);
						} else if (input2 == 5) {
							icici.changeInterestRate(admin);
						} else if (input2 == 6) {
							icici.changeLoanRate(admin);
						} else if (input2 != 0) {
							System.out.println("Please select one of the above option");
						}
					} while (input2 != 0);
				}
			} else if (input1 != 0) {
				System.out.println("Please select one of the above option");
			}
		} while (input1 != 0);
	}

	public static void showStartMenu() {
		System.out.println();
		System.out.println("Press 1 if you are a user");
		System.out.println("Press 2 if you are a admin");
		System.out.println("Press 0 to exit");
	}

	public static void showAdminMenu() {
		System.out.println();
		System.out.println("Press 1 to show bank details");
		System.out.println("Press 2 to see profit earned");
		System.out.println("Press 3 to show all Accounts");
		System.out.println("Press 4 to grant interest to all Accounts");
		System.out.println("Press 5 to change interest rate");
		System.out.println("Press 6 to change loan rate");
		// System.out.println("Press 7 for other activities");
		System.out.println("Press 0 to go to previous menu.");
	}

	public static void showUserMenu() {
		System.out.println();
		System.out.println("Press 1 if you are already a customer of our bank");
		System.out.println("Press 2 to register youself as a customer");
		System.out.println("Press 0 to go back to previous menu");
	}

	public static void showCustomerMenu() {
		System.out.println();
		System.out.println("Press 1 to open a new account");
		System.out.println("Press 2 to deposit money");
		System.out.println("Press 3 to withdraw money");
		System.out.println("Press 4 to check account balance");
		System.out.println("Press 5 to apply for a loan");
		System.out.println("Press 6 to pay off old loan");
		System.out.println("Press 7 to show all my accounts and loans. // not yet working");
		System.out.println("Press 0 to go to previous menu");
	}
}

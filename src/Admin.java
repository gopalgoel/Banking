class Admin extends User {
	Bank bank;

	public Admin(String name, String userName, String password) {
		super(name, userName, password);
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}

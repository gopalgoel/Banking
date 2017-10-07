class Admin extends User {

	private Bank bank;

	
	public Admin(String name, String userName, String password) {
		super(name, userName, password);
	}

  /**
   * Get Admin's bank
   * @return the bank
   */
  public Bank getBank() {
    return bank;
  }

  /**
   * Set admin's bank
   * @param bank the bank to set
   */
  public void setBank(Bank bank) {
    this.bank = bank;
  }

}

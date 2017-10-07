class User {
	public String name;
	public String userName;
	public String password;

	public User(String name, String userName, String password) {
		this.name = name;
		this.userName = userName;
		this.password = password;
	}
	
	/**
	 * Get User's name
	 * @return Name as string
	 */
	public String getName() {
	  return name;
	}
	/**
	 * Get User's username
	 * @return Username as string
	 */
	public String getUserName() {
	    return userName;
	}

	/**
	 * Get User's password
	 * @return Password as string
	 */
	public String getPassword() {
	    return password;
	}

}

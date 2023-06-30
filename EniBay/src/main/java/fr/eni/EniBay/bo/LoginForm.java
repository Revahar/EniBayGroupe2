package fr.eni.EniBay.bo;

public class LoginForm {
	private String username;
	private String password;
	private boolean rememberMe;
	
	
	public LoginForm() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String email) {
		this.username = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		return "LoginForm [username=" + username + ", password=" + password + ", rememberMe=" + rememberMe + "]";
	}


}

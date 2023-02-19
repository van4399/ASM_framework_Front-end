package BEAN;



public class User {
	private String email;
	private String password;
	private String confirmPassword; 
	private String key;
	private String ip;
	public User() {
		
	}
	public User(String email, String password, String confirmPassword, String key) {
		super();
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.key = key;
	}
	
	public User(String email, String password, String confirmPassword, String key, String ip) {
		super();
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.key = key;
		this.ip = ip;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return this.getEmail() +"," + this.getKey();
	}
	
	
}

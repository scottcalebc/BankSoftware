package application.model;

public class Users {
	private String userName;
	private String password;
	private int userId;
	
	public static int id = 0;
	
	public Users(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.userId = id;
		
		id += 1;
		
	}
	
	public Users(String userName, String password, int userId) {
		this.userName = userName;
		this.password = password;
		this.userId = userId;
		
		id = userId + 1;
	}
	
	
	public int getUserId() {
		return this.userId;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	
	
	
	public String toString() {
		return userId + "," + userName + "," + password + "\n";
	}
}

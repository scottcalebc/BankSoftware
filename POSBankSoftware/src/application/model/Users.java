package application.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4271773951358323824L;
	private String userName;
	private String password;
	private int userId;
	private ArrayList<Account> accounts;
	
	public static int id = 0;
	
	public Users(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.userId = id;
		this.accounts = new ArrayList<Account>();
		
		id += 1;
		
	}
	
	public Users(String userName, String password, int userId) {
		this.userName = userName;
		this.password = password;
		this.userId = userId;
		this.accounts = new ArrayList<Account>();
		id = userId + 1;
	}
	
	
	public int getUserId() {
		return this.userId;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public ArrayList<Account> getAccounts() {
		return this.accounts;
	}
	
	public void addAccount(Account acct) {
		this.accounts.add(acct);
	}
	
	
	
	public String toString() {
		String out = userId + "," + userName + "," + password + "\n" ;
		
		if (!this.accounts.isEmpty()) {
			
			for (Account acct : this.accounts) {
				out += "|_____";
				out += acct.toString();
			}
		}
		return out;
	}
}

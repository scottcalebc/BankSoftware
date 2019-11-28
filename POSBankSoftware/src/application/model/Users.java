package application.model;


import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable, ShowData {
        /**
	 * 
	 */
	private static final long serialVersionUID = 5455019132955405860L;
	private String name;
    private ArrayList<Accounts> accounts;
    private String password;

    public Users(String name, String password){
        this.name = name;
        this.password = password;
        this.accounts = new ArrayList<Accounts>();
    }


    public String getNamFirst() { return name; }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public ArrayList<Accounts> getAccounts() {
    	return accounts;
    }
    
    public void setAccounts(ArrayList<Accounts> accounts) {
    	this.accounts = accounts;
    }
    
    public void addAccount(Accounts account) {
    	this.accounts.add(account);
    }
    
    public void removeAccount(Accounts account) {
    	this.accounts.remove(account);
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }


	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getAmount() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getTotal() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public double getAmountDouble() {
		// TODO Auto-generated method stub
		return 0;
	}
    

}

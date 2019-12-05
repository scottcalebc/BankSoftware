package application.model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * User objects for model
 * @author Nikki Thanapaisal
 * @author Jonathan Becker
 * @author Christopher Caleb Scott
 *
 */
public class Users implements Serializable, ShowData {

	/**
	 * Used to serialize object
	 */
	private static final long serialVersionUID = 5455019132955405860L;
	/**
	 * username of user
	 */
	private String name;
	/**
	 * List of accounts for user
	 */
    private ArrayList<Accounts> accounts;
    /**
     * Password of user
     */
    private String password;

    /**
     * Constructor
     * @param name
     * @param password
     */
    public Users(String name, String password){
        this.name = name;
        this.password = password;
        this.accounts = new ArrayList<Accounts>();
    }
    
    /**
     * Sets the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the name
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets all accounts
     * @return
     */
    public ArrayList<Accounts> getAccounts() {
    	return accounts;
    }
    
    /**
     * Sets all accoujts
     * @param accounts
     */
    public void setAccounts(ArrayList<Accounts> accounts) {
    	this.accounts = accounts;
    }
    
    /**
     * Adds an account to list
     * @param account
     */
    public void addAccount(Accounts account) {
    	this.accounts.add(account);
    }
    
    /**
     * Removes an account from list
     * @param account
     */
    public void removeAccount(Accounts account) {
    	this.accounts.remove(account);
    }
    
    /**
     * Gets the password
     * @return
     */
    public String getPassword() {
    	return password;
    }
    
    /**
     * Sets the password
     * @param password
     */
    public void setPassword(String password) {
    	this.password = password;
    }


	@Override
	/**
	 * Interface method not used
	 */
	public String getDate() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	/**
	 * Interface method not used
	 */
	public String getAmount() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	/**
	 * Interface method not used
	 */
	public String getTotal() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	/**
	 * Interface method not used
	 */
	public double getAmountDouble() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	/**
	 * Returns all accounts
	 * @return
	 */
	public ArrayList<ShowData> getChildren() {
		// TODO Auto-generated method stub
		return new ArrayList<ShowData>(this.accounts);
	}


	@Override
	/**
	 * Removes a specified account
	 * @param
	 */
	public void removeChild(ShowData child) {
		
		if (child instanceof Accounts) {
			System.out.println("Removing child " + child);
			this.removeAccount((Accounts)child);
		}
		
	}


	@Override
	/**
	 * Adds a specified account
	 * @param
	 */
	public void addChild(ShowData child) {
		if (child instanceof Accounts) {
			this.addAccount((Accounts)child);
		}
	}


	@Override
	/**
	 * Sums all totals from Accounts
	 * @return
	 */
	public double[] getTotals() {
		// TODO Auto-generated method stub
		double total = this.accounts.stream().mapToDouble(x -> x.getCurrBalance()).sum();
		double out = this.accounts.stream().mapToDouble(x -> x.getMoneyOut()).sum();
		double in = this.accounts.stream().mapToDouble(x -> x.getMoneyIn()).sum();
		return new double[]{total, in, out};
	}
	
	/**
	 * Debug output to print User account object and all accounts
	 * @return
	 */
	public String toString() {
		String out = this.name + " " + this.password + "\n";
		for(Accounts act : this.accounts) {
			out += "  |____" + act.toString();
		}
		
		return out;
	}
    

}

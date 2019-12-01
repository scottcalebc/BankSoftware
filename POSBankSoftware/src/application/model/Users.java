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


	@Override
	public ArrayList<ShowData> getChildren() {
		// TODO Auto-generated method stub
		return new ArrayList<ShowData>(this.accounts);
	}


	@Override
	public void removeChild(ShowData child) {
		
		if (child instanceof Accounts) {
			System.out.println("Removing child " + child);
			this.removeAccount((Accounts)child);
		}
		
	}


	@Override
	public void addChild(ShowData child) {
		if (child instanceof Accounts) {
			this.addAccount((Accounts)child);
		}
	}


	@Override
	public double[] getTotals() {
		// TODO Auto-generated method stub
		double total = this.accounts.stream().mapToDouble(x -> x.getCurrBalance()).sum();
		double out = this.accounts.stream().mapToDouble(x -> x.getMoneyOut()).sum();
		double in = this.accounts.stream().mapToDouble(x -> x.getMoneyIn()).sum();
		return new double[]{total, in, out};
	}
	
	
	public String toString() {
		String out = this.name + "\n";
		for(Accounts act : this.accounts) {
			out += "  |____" + act.toString();
		}
		
		return out;
	}
    

}

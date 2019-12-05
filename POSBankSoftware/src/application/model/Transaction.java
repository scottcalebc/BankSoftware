package application.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Transaction data 
 * @author Nikki Thanapaisal
 * @author Jonathan Becker
 * @author Christopher Caleb Scott
 * 
 *
 */
public class Transaction implements ShowData, Serializable{
    /**
	 * used to serialze object
	 */
	private static final long serialVersionUID = -850213061847820920L;
	/**
	 * Name of transaction
	 */
	private String name;
	/**
	 * Value of transaction
	 */
    private double transactionValue;
    /**
     * Date of transaction
     */
    private String date;


    /**
     * Constructor
     * @param name
     * @param transactionValue
     * @param date
     */
    public Transaction(String name, double transactionValue, String date) {
        this.name = name;
    	this.transactionValue = transactionValue;
        this.date = date;
    }

    /**
     * Gets the Value 
     * @return
     */
    public double getTransactionValue() {
        return transactionValue;
    }

    /**
     * Sets the valud
     * @param transactionValue
     */
    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }

    /**
     * Gets the Date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the Date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    
    /**
     * Gets the name
     * @return
     */
    public String getName() {
    	return this.name;
    }
    /**
     * Sets the name
     * @param name
     */
    public void setName(String name) {
    	this.name = name;
    }
    
    /**
     * Gets the amount as formatted string
     * @return
     */
    public String getAmount() {
    	return String.format("%.2f", this.transactionValue);
    }
    
    /**
     * Interface method not used
     */
    public String getTotal() {
    	return null;
    }
    
    /**
     * Gets the amount as a double
     */
    public double getAmountDouble() {
    	return this.transactionValue;
    }

	@Override
	/**
	 * Interface method not used
	 */
	public ArrayList<ShowData> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * interface method not used
	 */
	public void removeChild(ShowData child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Interface method not used
	 */
	public void addChild(ShowData child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * interface method not used
	 */
	public double[] getTotals() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Debug out put returns formated string of object
	 * @return
	 */
	public String toString() {
		return this.name + " " + this.getAmountDouble() + "\n";
	}
}

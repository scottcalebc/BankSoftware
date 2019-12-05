package application.model;

import java.util.ArrayList;

/**
 * Interface to allow each model object to be easily referenced and passed among
 * all controllers and items on views
 * @author Christopher Caleb Scott
 *
 */
public interface ShowData {
	
	/**
	 * Gets the name of the object
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * Gets the date of the object
	 * @return
	 */
	public abstract String getDate();
	
	/**
	 * Gets the amount of the object
	 * @return
	 */
	public abstract String getAmount();
	
	/**
	 * Gets the total of the object
	 * @return
	 */
	public abstract String getTotal();
	
	/**
	 * Gets the amount as a double of the object
	 * @return
	 */
	public abstract double getAmountDouble();
	
	/**
	 * Gets all children of the object
	 * @return
	 */
	public abstract ArrayList<ShowData> getChildren();
	
	/**
	 * Tells object to remove a specific object from its child list
	 * @param child
	 */
	public abstract void removeChild(ShowData child);
	
	/**
	 * Tells object to add a specific object to its child list
	 * @param child
	 */
	public abstract void addChild(ShowData child);
	
	/**
	 * Tells object to calculate totals
	 * @return
	 */
	public abstract double[] getTotals();
}

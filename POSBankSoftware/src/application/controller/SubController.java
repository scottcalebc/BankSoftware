package application.controller;

import application.model.ShowData;

/**
 * Interface to enable communication between a multiple Controllers passing ShowData objects between them
 * @author Christopher Caleb Scott
 *
 */
public interface SubController {
	
	/**
	 * Loads data inside Controller and onto View
	 * @param data
	 * @param mc
	 */
	public abstract void onLoad(ShowData data, MainController mc);
	
	/**
	 * Pass ShowData object to another view or back to MainController
	 * @return
	 */
	public abstract ShowData onExit();
	
	
}

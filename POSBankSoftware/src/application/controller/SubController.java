package application.controller;

import application.model.ShowData;

public interface SubController {
	
	public abstract void onLoad(ShowData data, MainController mc);
	
	public abstract ShowData onExit();
	
	
}

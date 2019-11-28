package application.controller;

import application.model.ShowData;

public interface SubController {
	
	public void onLoad(ShowData data, MainController mc);
	
	public ShowData onExit();
	
	
}

package com.robur58.web.view;

import java.io.Serializable;

public class UserInfo implements Serializable 
{
	public static String USERINFONAME = "UserInfo";
	
    private String displayNaam;
    private String username;
    private String sessionId;
  
    public UserInfo() {
    }
	
	public String getDisplayNaam() {
		return displayNaam;
	}
	
	public void setDisplayNaam(String displayNaam) {
		this.displayNaam = displayNaam;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
    
}

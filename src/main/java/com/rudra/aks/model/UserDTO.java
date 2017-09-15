package com.rudra.aks.model;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {

	private int userid;
	private String username;
	private String emailid;
	private String role;
	
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", username=" + username + ", emailid=" + emailid + ", role=" + role + "]";
	}
	
	
}

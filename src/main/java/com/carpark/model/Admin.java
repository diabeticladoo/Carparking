package com.carpark.model;

public class Admin {
	@Override
	public String toString() {
		return "Admin [uname=" + uname + ", pass=" + pass + "]";
	}
	private String uname,pass;
	public Admin(String UN) {
		uname = UN;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}

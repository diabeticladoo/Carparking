package com.carpark.model;

import java.util.ArrayList;

public class Worker {
	private int wid, pid, rateCount, numHrs;
	float rating;
	private String uname,pass,joined,services, fname;
	public Worker(String UN) {
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
	public int getNumHrs() {
		return numHrs;
	}
	public void setNumHrs(int numHrs) {
		this.numHrs = numHrs;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getJoined() {
		return joined;
	}
	public void setJoined(String joined) {
		this.joined = joined;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getRateCount() {
		return rateCount;
	}
	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setAll(int Wid, int Pid, String Uname, String Pass, String Fname, String Joined, String Services, float Rating, int RateCount) {
		if(Wid>0) wid=Wid; 
		if(Pid>0) pid = Pid;
		if(Uname!=null && !Uname.equals("")) uname = Uname;
		if(Pass!=null && !Pass.equals("")) pass = Pass;
		if(Fname!=null && !Fname.equals("")) fname = Fname;
		if(Joined!=null && !Joined.equals("")) joined = Joined;
		if(Services!=null && !Services.equals("")) services = Services;
		if(Rating>0) rating = Rating;
		if(RateCount>0) rateCount = RateCount;
	}
	public final static String servNames[] = {"Dry Cleaning","Car Washing","Repair Check","Pollution Check"};
	public static boolean[] servFromStr(String servStr) {
		boolean servs[] = new boolean[servStr.length()];
		for(int i = 0; i < servStr.length(); i++) {
			servs[i] = servStr.charAt(i) == '1';
		}
		return servs; 
	}
	public static String strFromServ(boolean[] serv) {
		String servStr= "";
		for(int i = 0; i < serv.length; i++) {
			servStr = servStr + (serv[i]==true? "1" : "0");
		}
		return servStr; 
	}
	
	@Override
	public String toString() {
		return "Worker [wid=" + wid + ", pid=" + pid + ", rating=" + rating + ", rateCount=" + rateCount + ", uname="
				+ uname + ", pass=" + pass + ", joined=" + joined + ", services=" + services + ", fname=" + fname + "]";
	}
}

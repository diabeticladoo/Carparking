package com.carpark.model;

import java.util.regex.*;

public class Park {
	private String PID, Loc, DTin, DTout, servStr;
	private double Cost;
	private float rating;
	private int uid, Emt, WaitingTime;
	public String getServStr() {
		return servStr;
	}
	public void setServStr(String servStr) {
		this.servStr = servStr;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Park(String Location, String DateTimeIn, String DateTimeOut) {
		Loc = Location; DTin = DateTimeIn; DTout = DateTimeOut;
		setCost(Park.getBill(DateTimeIn, DateTimeOut));
	}
	public Park(String Pid) {
		PID = Pid;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public int getEmt() {
		return Emt;
	}
	public void setEmt(int emt) {
		Emt = emt;
	}
	public String getLoc() {
		return Loc;
	}
	public void setLoc(String Loc) {
		this.Loc = Loc;
	}
	public String getDTin() {
		return DTin;
	}
	public void setDTin(String DTin) {
		this.DTin = DTin;
	}
	public String getDTout() {
		return DTout;
	}
	public void setDTout(String DTout) {
		this.DTout = DTout;
	}
	public void setDetails(String pID, String Loc, String DTin, String DTout) {
		if(pID!=null || pID!="")		setPID(pID);
		if(Loc!=null || Loc!="")		setLoc(Loc);
		if(DTin!=null || DTin!="")		setDTin(DTin);
		if(DTout!=null || DTout!="")	setDTout(DTout);
	}
	@Override
	public String toString() {
		return "Park [PID=" + PID + ", Location=" + Loc + ", Check-In Date and Time=" + DTin + ", Check-Out Date and Time=" + DTout+" ]";
	}
	public static double getBill(String CiDT, String CoDT) {
		Pattern p = Pattern.compile("(\\d+)-(\\d+)-(\\d+)[\\sT](\\d+):(\\d+)");
		Matcher m1 = p.matcher(CiDT);
		Matcher m2 = p.matcher(CoDT);
		m1.find();
		m2.find();
		int cidt = Integer.parseInt(m1.group(4)) * 60 + Integer.parseInt(m1.group(5));
		int codt = Integer.parseInt(m2.group(4)) * 60 + Integer.parseInt(m2.group(5));
		double hours = codt - cidt;
//		System.out.println(Math.ceil(hours/60));
		return Math.ceil(hours/60) * 25;
	}
	public int getWaitingTime(String CiDT) {
		Pattern p = Pattern.compile("(\\d+)-(\\d+)-(\\d+)[\\sT](\\d+):(\\d+)");
		Matcher m1 = p.matcher(CiDT);
		m1.find();
		Matcher m2 = p.matcher(DTout);
		m2.find();
		WaitingTime = 0;
		if(Integer.parseInt(m1.group(3)) == Integer.parseInt(m2.group(3))) {
				int codt = Integer.parseInt(m2.group(4)) * 60 + Integer.parseInt(m2.group(5));
				int cidt = Integer.parseInt(m1.group(4)) * 60 + Integer.parseInt(m1.group(5));
				WaitingTime = codt - cidt;
		}
		if(WaitingTime < 0)
			WaitingTime = 0;
		else
			WaitingTime = (int) Math.ceil(((double)WaitingTime)/60);
//		System.out.println(WaitingTime);
		return WaitingTime;
	}
	public int getWaitingTime() {
		return WaitingTime;
	}
	public double getCost() {
		return Cost;
	}
	public void setCost(double cost) {
		Cost = cost;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
}

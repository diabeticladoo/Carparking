package com.carpark.model;

import java.util.Random;

public class User {
	private String fn,ln,un,pw,addr,email,phno,carno,logw;
	int uid;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public User(String from, String entry) {
		if(from.equals("uname")) un = entry;
		if(from.equals("email")) email = entry;
		
	}
	public String getFn() {
		return fn;
	}
	public void setFn(String fn) {
		this.fn = fn;
	}
	public String getLn() {
		return ln;
	}
	public void setLn(String ln) {
		this.ln = ln;
	}
	public String getUn() {
		return un;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getLogw() {
		return logw;
	}
	public void setLogw(String logw) {
		this.logw = logw;
	}
	public void setDetails(String Fn, String Ln, String Un, String Pw, String Ad, String Em, String Ph, String Cr, String Lw) {
		if(Fn!=null && !Fn.equals("")) setFn(Fn);
		if(Ln!=null && !Ln.equals("")) setLn(Ln);
		if(Un!=null && !Un.equals("")) setUn(Un);
		if(Pw!=null && !Pw.equals("")) setPw(Pw);
		if(Ad!=null && !Ad.equals("")) setAddr(Ad);
		if(Em!=null && !Em.equals("")) setEmail(Em);
		if(Ph!=null && !Ph.equals("")) setPhno(Ph);
		if(Cr!=null && !Cr.equals("")) setCarno(Cr);
		if(Lw!=null && !Lw.equals("")) setLogw(Lw);
	}
	@Override
	public String toString() {
		return "User [fn=" + fn + ", ln=" + ln + ", un=" + un + ", pw=" + pw + ", addr=" + addr + ", email=" + email
				+ ", phno=" + phno + ", carno=" + carno + ", logw=" + logw + "]";
	}
	public static String genPromo() {
		String pro = "";
		for(int i = 0; i < 4; i++) {
			Random r = new Random();
			char c = (char) (r.nextInt(26)+'A');
			pro = pro + c;
		}
		return pro;
	}
}

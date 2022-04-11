package com.carpark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.carpark.helper.DateTime;
import com.carpark.model.Worker;

public class WorkerDAO implements DAO{

	public Worker fromCreds(String uname, String pass) {
		String sql = "select * from Workers where uname=? and pass=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setString(1, uname);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				Worker w = new Worker(uname);
				w.setAll(
						rs.getInt("wid"),
						rs.getInt("pid"),
						uname,
						pass,
						rs.getString("fname"),
						rs.getString("joined"),
						rs.getString("services"),
						rs.getFloat("rating"),
						rs.getInt("rateCnt")
					);
				return w;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public Worker fromWid(int Wid) {
		String sql = "select * from Workers where wid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1, Wid);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				Worker w = new Worker(rs.getString("uname"));
				w.setAll(
						rs.getInt("wid"),
						rs.getInt("pid"),
						"",
						rs.getString("pass"),
						rs.getString("fname"),
						rs.getString("joined"),
						rs.getString("services"),
						rs.getFloat("rating"),
						rs.getInt("rateCnt")
					);
				return w;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public ArrayList<Worker> listAll() {
		String sql = "select * from Workers";
		Connection con = null;
		PreparedStatement st = null;
		try {
			ArrayList<Worker> wlist = new ArrayList<Worker>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Worker w = new Worker(rs.getString("uname"));
				w.setAll(
						rs.getInt("wid"), 
						rs.getInt("pid"), 
						"", 
						rs.getString("pass"), 
						rs.getString("fname"), 
						rs.getString("joined"), 
						rs.getString("services"),
						rs.getFloat("rating"),
						rs.getInt("rateCnt")
					);
				wlist.add(w);
//				System.out.println(w);
			}
			return wlist;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	static public int maxID() {
		String sql = "select max(wid) as maxwid from Workers";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getInt("maxwid");
			}else {
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	public void addWorker(String Uname, String Pass, String Fname, String Joined,int Pid) {
		String sql = "insert into workers (uname,pass,fname,joined,pid) values(?,?,?,?,?)";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setString(1, Uname);
			st.setString(2, Pass);
			st.setString(3, Fname);
			st.setString(4, Joined);
			st.setInt(5, Pid);
			st.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void assignParkspace(String Pid, int Wid) {
		String sql = "update workers set pid=? where wid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(Pid));
			st.setInt(2, Wid);
			st.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void unassignParkspace(int Wid) {
		String sql = "update workers set pid=NULL where wid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1, Wid);
			st.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void removeWorker(int Wid) {
		String sql = "delete from Workers where wid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1, Wid);
			st.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setRating(int wid, float rating, float currRating, int currCnt) {
		String sql = "update Workers set rating=?,rateCnt=? where wid=?";

		currRating = (currRating*currCnt+rating)/(currCnt+1);
		currRating = Math.round(currRating*100)/100;
		currCnt++;
		
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setFloat(1,currRating);
			st.setInt(2, currCnt);
			st.setInt(3, wid);
			st.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void feedbackWid(int wid, int rating) {
		String sql = "select rating,rateCnt from Workers where wid=?";
		Connection con = null;
		PreparedStatement st = null;
		float currRating = -1;
		int currRateCnt = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1, wid);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				currRating = rs.getFloat("rating");
				currRateCnt = rs.getInt("rateCnt");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		setRating(wid,rating,currRating,currRateCnt);
		
	}
	public String getPid(int Wid) {
		String sql = "select pid from Workers where wid=?";
		Connection con = null;
		PreparedStatement st = null;
		float currRating = -1;
		int currRateCnt = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1, Wid);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getInt("pid")+"";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public void updateServ(int Wid, String servStr) {
		String sql = "update workers set services=? where wid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setString(1,servStr);
			st.setInt(2, Wid);
			st.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}

package com.carpark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.carpark.model.User;

public class UserDAO implements DAO{
	public static boolean alreadyExists(String field, String entry) {
		String sql = "select "+field+" from UserReg where "+field+"=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setString(1, entry);
			ResultSet rs = st.executeQuery();
			return rs.next();
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
		return false;
	}
	public void addUser(User u) {
		String sql = "insert into UserReg "
				+ "(uname,pass,fname,lname,address,email,phno,carno,loginw) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setString(1, u.getUn()!=null? u.getUn(): "NULL");
			st.setString(2, u.getPw()!=null ? u.getPw() : "NULL");
			st.setString(3, u.getFn()!=null? u.getFn() : "NULL");
			st.setString(4, u.getLn()!=null? u.getLn() : "NULL");
			st.setString(5, u.getAddr()!=null? u.getAddr() : "NULL");
			st.setString(6, u.getEmail()!=null? u.getEmail() : "NULL");
			st.setString(7, u.getPhno()!=null? u.getPhno() : "NULL");
			st.setString(8, u.getCarno()!=null? u.getCarno() : "NULL");
			st.setString(9, u.getLogw()!=null? u.getLogw() : "NULL");
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
	public User fromCreds(String uname, String pass) {
		String sql = "select * from UserReg where uname=? and pass=?";
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
				User u = new User("uname",uname);
				u.setDetails(
						rs.getString("fname"),
						rs.getString("lname"),
						"",
						pass,
						rs.getString("address"),
						rs.getString("email"),
						rs.getString("phno"),
						rs.getString("carno"),
						rs.getString("loginw")
					);
				u.setUid(rs.getInt("uid"));
				System.out.println(u.getUid() + " " + rs.getInt("uid"));
				return u;
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
	public User fromNonCred(String email) {
		String sql = "select * from UserReg where email=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				User u = new User("email",email);
				u.setDetails(
						rs.getString("fname"),
						rs.getString("lname"),
						email,
						"",
						rs.getString("address"),
						"",
						rs.getString("phno"),
						rs.getString("carno"),
						rs.getString("loginw")
					);
				u.setUid(rs.getInt("uid"));
				System.out.println(u.getUid() + " " + rs.getInt("uid"));
				return u;
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
	public int getPromoCnt(int Uid) {
		String sql = "select promoCnt from UserReg where uid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1,Uid);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getInt("promoCnt");
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
	public String getPromo(int Uid) {
		String sql = "select promo from UserReg where uid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1,Uid);
			ResultSet rs = st.executeQuery();
			if(rs.next() && rs.getString("promo")!=null) {
				return rs.getString("promo");
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
		return "";
	}
	public void changePromoCnt(int Uid, int delta) {
		String sql = "update UserReg set promocnt = promocnt + ? where uid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1,delta);
			st.setInt(2, Uid);
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
	public void setPromo(int Uid, String Promo) {
		String sql = "update UserReg set promo = ? where uid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setString(1,Promo);
			st.setInt(2, Uid);
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
	public void removePromo(int Uid) {
		String sql = "update UserReg set promo=NULL where uid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1, Uid);
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

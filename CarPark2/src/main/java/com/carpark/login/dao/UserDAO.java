package com.carpark.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.carpark.model.User;

public class UserDAO {
	String url = "jdbc:mysql://localhost:3306/carpark";
	String username = "root";
	String password = "1234";
	public User fromCred(String uname, String pass) {
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
				User u = new User(uname,pass);
				if(rs.getString("fname")!=null) {
					u.setFn(rs.getString("fname"));
				}
				if(rs.getString("lname")!=null) {
					u.setLn(rs.getString("lname"));
				}
				if(rs.getString("address")!=null) {
					u.setAddr(rs.getString("address"));
				}
				if(rs.getString("email")!=null) {
					u.setEmail(rs.getString("email"));
				}
				if(rs.getString("phno")!=null) {
					u.setPhno(rs.getString("phno"));
				}
				if(rs.getString("carno")!=null) {
					u.setCarno(rs.getString("carno"));
				}
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
}

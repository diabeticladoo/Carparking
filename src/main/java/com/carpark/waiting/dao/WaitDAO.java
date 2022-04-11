package com.carpark.waiting.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.carpark.dao.DAO;
import com.carpark.model.Park;

public class WaitDAO implements DAO{
	public void addWait(int Uid, String Loc, String Cin, String Cout) {
		String sql = "insert into waitingList values(?,?,?,?)";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1, Uid);
			st.setString(2, Loc);
			st.setString(3, Cin);
			st.setString(4, Cout);
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

	public ArrayList<Park> getWait(int Uid) {
		String sql = "select loc,cin,cout from waitinglist where uid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			ArrayList<Park> bookings = new ArrayList<Park>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			st = con.prepareStatement(sql);
			st.setInt(1, Uid);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Park p = new Park(
						rs.getString("loc"),
						rs.getString("cin"),
						rs.getString("cout")
					);
				bookings.add(p);
			}
			return bookings;
		}
		catch(Exception e) {
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

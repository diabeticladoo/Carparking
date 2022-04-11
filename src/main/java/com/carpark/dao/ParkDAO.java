package com.carpark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.carpark.model.Park;
import com.carpark.model.Worker;

public class ParkDAO implements DAO {
	public ArrayList<Park> fromBooking(String Loc, String CiDT, String CoDT) {
		String sql = "select parkspace.pid as parkid,cin,max(cout) as cout,avg(cin>? or cout<?)=1 as available,uid,wid "
				+ "from parkspace,booking " + "where parkspace.pid=booking.pid and loc=? " + "group by parkid";
		// String sql = "select booking.pid,cin,cout,(cin>? or cout<?) as Available from
		// booking where pid in (select pid from parkspace where loc=?);";
		Connection con = null;
		PreparedStatement st = null;
		try {
			WorkerDAO wdao = new WorkerDAO();
			ArrayList<Park> p = new ArrayList<Park>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setString(1, CoDT);
			st.setString(2, CiDT);
			st.setString(3, Loc);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Park Slot = new Park(Loc, CiDT, CoDT);
				if (rs.getString("parkid") != null) {
					Slot.setPID(rs.getString("parkid"));
				}
				Slot.setLoc(Loc);
				if (rs.getString("cin") != null) {
					Slot.setDTin(rs.getString("cin"));
				}
				if (rs.getString("cout") != null) {
					Slot.setDTout(rs.getString("cout"));
				}
				Slot.getWaitingTime(CiDT);
				Slot.setUid(rs.getInt("uid"));
				Slot.setEmt(rs.getInt("available"));

				Worker w = wdao.fromWid(rs.getInt("wid"));
				if (w == null) {
					Slot.setRating(-1);
					Slot.setServStr("0000");
				} else {
					Slot.setRating(w.getRating());
					Slot.setServStr(w.getServices());
				}
				p.add(Slot);
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Park bookingFromPid(String Pid, String Loc, String CiDT, String CoDT) {
		String sql = "select cin,max(cout) as cout,avg(cin>=? or cout<=?)=1 as available,uid "
				+ "from booking where pid=? group by pid";
		// String sql = "select booking.pid,cin,cout,(cin>? or cout<?) as Available from
		// booking where pid in (select pid from parkspace where loc=?);";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setString(1, CoDT);
			st.setString(2, CiDT);
			st.setString(3, Pid);
			ResultSet rs = st.executeQuery();
			Park Slot = new Park(Loc, CiDT, CoDT);
			Slot.setPID("" + Pid);
			if (rs.next()) {
				Slot.setLoc(Loc);
				if (rs.getString("cin") != null) {
					Slot.setDTin(rs.getString("cin"));
				}
				if (rs.getString("cout") != null) {
					Slot.setDTout(rs.getString("cout"));
				}
				Slot.setUid(rs.getInt("uid"));
				Slot.setEmt(rs.getInt("available"));

			}else {
				Slot.setEmt(1);
			}
			Slot.getWaitingTime(CiDT);
			return Slot;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Park> fromBooking2(String Loc, String CiDT, String CoDT) {
		String sql = "select pid, wid from parkspace where loc=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			WorkerDAO wdao = new WorkerDAO();
			ArrayList<Park> p = new ArrayList<Park>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setString(1, Loc);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Park Slot = bookingFromPid("" + rs.getInt("pid"), Loc, CiDT, CoDT);
				if(Slot!=null) {
					
				}
				Worker w = wdao.fromWid(rs.getInt("wid"));
				if (w == null) {
					Slot.setRating(-1);
					Slot.setServStr("0000");
				} else {
					Slot.setRating(w.getRating());
					Slot.setServStr(w.getServices());
				}
				p.add(Slot);
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public ArrayList<Park> bookingFromPid(String Pid, String Date) {
		String sql = "select cin, cout from booking where pid=? and cin>? and cout<?";
		Connection con = null;
		PreparedStatement st = null;
		String Date1 = Date+" 00:00";
		String Date2 = Date+" 23:59";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setString(1, Pid);
			st.setString(2, Date1);
			st.setString(3, Date2);
			ResultSet rs = st.executeQuery();
			ArrayList<Park> Slots = new ArrayList<Park>();

			while (rs.next()) {
				Park Slot=new Park("",rs.getString("cin"),rs.getString("cout"));
				Slots.add(Slot);
			}
			return Slots;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public ArrayList<Park> bookingFromPid(String Pid) {
		String sql = "select cin, cout from booking where pid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setString(1, Pid);
			ResultSet rs = st.executeQuery();
			ArrayList<Park> Slots = new ArrayList<Park>();

			while (rs.next()) {
				Park Slot=new Park("",rs.getString("cin"),rs.getString("cout"));
				Slots.add(Slot);
			}
			return Slots;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public ArrayList<String> getLocs() {
		String sql = "select distinct(loc) as location from parkspace;";
		Connection con = null;
		PreparedStatement st = null;
		try {
			ArrayList<String> locs = new ArrayList<String>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				locs.add(rs.getString("location"));
			}
			return locs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Park> getBookings(int Uid) {
		String sql = "select booking.pid as parkid,loc,cin,cout,paid from booking,parkspace "
				+ "where booking.pid=parkspace.pid and uid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			ArrayList<Park> bookings = new ArrayList<Park>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setInt(1, Uid);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Park p = new Park(rs.getString("loc"), rs.getString("cin"), rs.getString("cout"));
				p.setPID(rs.getString("parkid"));
				p.setEmt(rs.getInt("paid"));
				bookings.add(p);
			}
			return bookings;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public ArrayList<Park> atLoc(String Loc) {
		String sql = "select pid,loc from parkspace where loc=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			ArrayList<Park> spaces = new ArrayList<Park>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setString(1, Loc);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String Pid = rs.getInt("pid")+"";
				Park p = new Park(Pid);
				int empty = bookingFromPid(Pid).size()>0 ? 0 : 1;
				p.setEmt(empty);
				spaces.add(p);
			}
			return spaces;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public int getWid(int Pid) {
		String sql = "select wid from parkspace where pid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setInt(1, Pid);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt("wid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public void addBooking(Park slot) {
		String sql = "insert into booking values(?,?,?,?,?)";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(slot.getPID()));
			st.setString(2, slot.getDTin());
			st.setString(3, slot.getDTout());
			st.setInt(4, slot.getUid());
			st.setInt(5, 0);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addParkspace(String Loc) {
		String sql = "insert into parkspace (loc) values(?)";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setString(1, Loc);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	public void assignWorker(String Pid, int Wid) {
		String sql = "update parkspace set wid=? where pid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setInt(1, Wid);
			st.setInt(2, Integer.parseInt(Pid));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void unassignWorker(String Pid) {
		String sql = "update parkspace set wid=NULL where pid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(Pid));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void removeParkspace(String Pid) {
		String sql = "delete from parkspace where pid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(Pid));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void removeBooking(String Pid) {
		String sql = "delete from booking where pid=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(Pid));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void payBooking(String Pid, String Cout) {
		String sql = "update booking set paid=1 where pid=? and cout=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(Pid));
			st.setString(2, Cout);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void removeBooking(String Pid, String Cout) {
		String sql = "delete from booking where pid=? and cout=?";
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(Pid));
			st.setString(2, Cout);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	static public boolean checkWid(int Pid) {
		ParkDAO pdao = new ParkDAO();
		return pdao.getWid(Pid) > 0;
	}
}

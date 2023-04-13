package com.palle.crudoperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {

	private static final String url = "jdbc:mysql://localhost:3306/palle";
	private static final String userName = "root";
	private static final String password = "1234";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static Connection getConnection() throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = null;

		try {

			conn = DriverManager.getConnection(url, userName, password);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public String creatTable() throws ClassNotFoundException, SQLException {

		try {
			Connection conn = Student.getConnection();

			String cQuery = "create table student(sNo int primary key auto_increment, "
					+ "sName varchar(255) not null,subject varchar(255) not null, " + "emailId varchar(255) unique);";

			ps = conn.prepareStatement(cQuery);
			ps.executeUpdate(cQuery);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return "Table is created Successfully!";

	}

	public String save(int sNo, String sName, String subject, String emailId) throws ClassNotFoundException {

		try {
			Connection conn = Student.getConnection();

			String iQuery = "insert into Student Values(?,?,?,?);";

			ps = conn.prepareStatement(iQuery);

			ps.setInt(1, sNo);
			ps.setString(2, sName);
			ps.setString(3, subject);
			ps.setString(4, emailId);
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return "Values are Inserted Successfully!";
	}

	public String update(int sNo, String subject, String emailId) throws ClassNotFoundException {

		Connection conn = Student.getConnection();

		try {

			String uQuery = "update student set subject=?, emailId=? where sNo=?;";

			ps = conn.prepareStatement(uQuery);

			ps.setInt(1, sNo);
			ps.setString(2, subject);
			ps.setString(3, emailId);
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return "Successfully Updated!";
	}

	public String delete(int sNo) throws ClassNotFoundException {

		Connection conn = Student.getConnection();

		try {

			String dQuery = "delete from student where sNo=?;";

			ps = conn.prepareStatement(dQuery);
			ps.setInt(1, sNo);
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return "Values are deleted Successfully!";
	}

	public void getAllData() throws ClassNotFoundException {

		Connection conn = Student.getConnection();

		try {
			int records=2;
			int page=2;

			String sQuery = "select * from Student limit"+" "+records+" "+ "offset"+" "+page+";";

			ps = conn.prepareStatement(sQuery);

			rs = ps.executeQuery();

			while (rs.next()) {

				System.out.println(
						rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			try {

				if (ps != null) {
					ps.close();
				}
				
				if (rs != null) {
					rs.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

}

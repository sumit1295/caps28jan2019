package com.caps.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;

public class MyFirstJDBCProgram {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			/*
			 * 1. Load the Driver
			 */
			
			java.sql.Driver driverRef = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");

			/*
			 * 2. Get the DB Connection via Driver
			 */
			
			String dbUrl="jdbc:mysql://localhost:3306/caps28jan2019_db"
			              +"?user=root&password=root";
			con=DriverManager.getConnection(dbUrl);
			System.out.println("Connected...");
			
		 	/*String dbUrl="jdbc:mysql://localhost:3306/caps28jan2019_db";
					
			con = DriverManager.getConnection(dbUrl,"root","root"); 
			System.out.println("Connected...");*/
   
			//3 rd version
			/*String dbUrl="jdbc:mysql://localhost:3306/caps28jan2019_db";
			FileReader in=new FileReader("C:/Users/SUMIT KUMAR/Desktop/file.properties");
			Properties prop=new Properties();
			prop.load(in);
			con = DriverManager.getConnection(dbUrl,prop); 
			System.out.println("Connected...");*/
   
			 
			
			
			/*
			  3. Issue the SQL query via connection
			 * 
			 */
			
			String sql="select * from student_info";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			/*
			 * 4. Process the results
			 */

			while(rs.next()){
				int REGNO = rs.getInt("REGNO");
				String FNAME = rs.getString("FNAME");
				String LNAME = rs.getString("LNAME");
				String PASSWORD = rs.getString("PASSWORD");
				String ISADMIN = rs.getString("ISADMIN");

				System.out.println(REGNO);
				System.out.println(FNAME);
				System.out.println(LNAME);
				System.out.println(PASSWORD);
				System.out.println(ISADMIN);
				System.out.println("*********************");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			// 5. Close all the JDBC Objects
			if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
	}
}
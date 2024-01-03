package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SelectTest7 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//load jdbc driver class optional
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
			//Crate statement obj;
			if(con!=null) 
				st=con.createStatement();
			//preapre resultset obj
			if(st!=null) 
			rs=st.executeQuery("SELECT COUNT(*) FROM EMPLOYEE");
	    	if(rs!=null) {
	    	   rs.next();
	    	   System.out.println("Number of record :"+rs.getInt(1));	    	
	    	   }
	  	
	    }//try
			catch( ClassNotFoundException cnf) { //known exception
				cnf.printStackTrace();
			}
		catch(SQLException se) {//known exception
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
		
	}

}

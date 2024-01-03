package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateValuesRetrieveTest {
	private static final String GET_DATE_VALUES="SELECT * FROM JDBC_PERSON_DATE"; 

	public static void main(String[] args)throws Exception {
		Connection con= null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
			if(con!=null)
				ps=con.prepareStatement(GET_DATE_VALUES);
			if(ps!=null)
				rs=ps.executeQuery();
		    if(rs!=null) {
		    	while(rs.next()) {
		    	//	System.out.println(rs.getInt(1)+"  "+rs.getString(2)); 
		    		//read date value as java.sql.Date class obj
		    		java.sql.Date sqdob=rs.getDate(3);
		    		java.sql.Date sqdoj=rs.getDate(4);
		    		java.sql.Date sqdom=rs.getDate(5);
		    		//convert to util
		    		java.util.Date udob=sqdob;
		    		java.util.Date udoj=sqdoj;
		    		java.util.Date udom=sqdom;
		    		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
		    		String sdob=sdf.format(udob);
		    	   String sdoj=sdf.format(udoj);
		    	   String sdom=sdf.format(udom);
		    	   System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+sdob+"  "+sdoj+"  "+sdom);
		    	}//while
		    }//if
		}//try
       catch(SQLException se){
    	   se.printStackTrace();
       }
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
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
				if(ps!=null)
					ps.close();
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
	}//main

}//class

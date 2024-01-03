package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InSensitiveRSTest {
private static final String GET_STUD_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
      try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
    		   PreparedStatement ps=con.prepareStatement(GET_STUD_QUERY,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		  //Scrollable ResultSet object
    		  ResultSet rs=ps.executeQuery();
    		  ){
    	  if(rs!=null) {
    		  System.out.println("Records top to bottom :");
    		  int count=0;
    		  while(rs.next()) {
    			  rs.refreshRow();
        		  if(count==0) {
        			  System.out.println("Modify db table records");
        			  Thread.sleep(3000);
        		  }
    			  count++;
    			  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
    		  }
    		
    	  }//if rs
      }//try
      catch(SQLException se) {
    	  se.printStackTrace();
      }//catch
      catch(Exception e) {
    	  e.printStackTrace();
      }//catch

	}//main

}//class

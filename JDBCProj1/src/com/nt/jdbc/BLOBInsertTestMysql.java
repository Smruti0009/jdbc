package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBInsertTestMysql {
		private static final String INSERT_ACTOR_QUERY="INSERT INTO JDBC_ACTOR_INFO(ACTORNAME,ACTORADDRS,PHOTO) VALUES(?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///smrutidb1","root","Mysql@9k#");
				PreparedStatement ps=con.prepareStatement(INSERT_ACTOR_QUERY);
				) {
			
			//read input values
			 String actorName=null,actorAddrs=null,photoLocation=null;
			if(sc!=null) {
				System.out.println("Enter actor name : ");
				actorName=sc.next();
				System.out.println("Enter actor addrs : ");
				actorAddrs=sc.next();
				System.out.println("Enter actor photo location : ");
				photoLocation=sc.next();
			}//if sc
			
			//create stream pointing to photo file
			InputStream is=new FileInputStream(photoLocation);
			
			// set query param values
			if(ps!=null) {
				ps.setString(1,actorName);
				ps.setString(2, actorAddrs);
				ps.setBinaryStream(3,is);
				
				//execute the query
				int count=0;
				if(ps!=null) 
					count=ps.executeUpdate();
				if(count==0) 
					System.out.println("Record not Inserted.");
				else
					System.out.println("Record inserted.");
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
		    e.printStackTrace();
		}
	

	}

}

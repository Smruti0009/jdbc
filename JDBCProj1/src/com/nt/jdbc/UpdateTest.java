package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {
		
	public static void main(String[] args) {
				Scanner sc=null;
				Connection con=null;
				Statement st=null;
				
				try {
					//read input
					sc= new Scanner(System.in);
					int no=0;
					String addrs=null;
					float avgm=0.0f;
					if(sc!=null) {
						System.out.println("Enter student no : ");
						no=sc.nextInt();
					System.out.println("Enter student address : ");
					addrs=sc.next();
					addrs="'"+addrs+"'";
					System.out.println("Enter avg : ");
					avgm=sc.nextFloat();
					}//if
					//load jdbc driver class optional
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Establish connection
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
					//Crate statement obj;
					if(con!=null) 
						st=con.createStatement();
					//prapare sql query
					String query="UPDATE STUDENT SET SADD="+addrs+", AVG="+avgm+" WHERE SNO="+no;
					System.out.println(query);
					//send and execute query
					int count =0;
					if(st!=null) {
						count=st.executeUpdate(query);
					}
						if(count==0)
							System.out.println("Record not updated");
						else
							System.out.println("Record updated");
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
					try {
						if(sc!=null)
							sc.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}//finally

			}//main

		}//class


package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ps_UpdateTest {
		private static final String UPDATE_STUDENT_BY_SNO="UPDATE STUDENT SET SADD=?, AVG=?  WHERE SNO=?";
	public static void main(String[] args) {
				Scanner sc=null;
				Connection con=null;
				PreparedStatement ps=null;
				
				try {
					//read input
					sc= new Scanner(System.in);
					int no=0;
					String newaddrs=null;
					float newavgm=0.0f;
					if(sc!=null) {
						System.out.println("Enter student no : ");
						no=sc.nextInt();
					System.out.println("Enter student address : ");
					newaddrs=sc.next();
					System.out.println("Enter avg : ");
					newavgm=sc.nextFloat();
					}//if
					//load jdbc driver class optional
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Establish connection
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
					//Crate statement obj;
					if(con!=null) 
						ps=con.prepareStatement(UPDATE_STUDENT_BY_SNO);
					//send and execute query
					int count=0;
					if(ps!=null) {
						ps.setString(1, newaddrs);
						ps.setFloat(2, newavgm);
						ps.setInt(3, no);
						count=ps.executeUpdate();
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



package com.pp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VaccineEligibilityCheckServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get print writer
		PrintWriter pw = res.getWriter();
		// set response content type
		res.setContentType("text/html");
		// read form data(req param value)
		String name = req.getParameter("pname");
		String addrs = req.getParameter("paddrs");
		String aged = req.getParameter("page");
		//int age = Integer.parseInt(aged);
		int age=0;
		//server side form validation status
		String csvStatus=req.getParameter("vflag");
		
		if(csvStatus.equalsIgnoreCase("no")) {
         // Form validation logics (Server side)
		System.out.println("Server side form validation");
		  List<String> errorsList=new ArrayList();
		  if(name==null || name.length()==0 || name.equalsIgnoreCase("")) //required name rule
		  errorsList.add("Person name is required."); 
		  if(addrs==null ||addrs.length()==0 || addrs.equalsIgnoreCase(""))
		  errorsList.add("Person address is required."); 
		  else if(addrs.length()<10) //min length rule
		  errorsList.add("Person address must have minimum 10 characters."); 
		  if(aged==null || aged.length()==0 || aged.equalsIgnoreCase(""))
		  errorsList.add("person age is required."); 
		  else { 
			  try {
		  age=Integer.parseInt(aged);
		  if(age<=0 || age>125)
		  errorsList.add("Person age must be in range of 1-125");
		  
		  } catch(NumberFormatException nfe) {
		  errorsList.add("Person age must be numeric value."); 
		  }//catch
		  }//else
		  
		  if(errorsList.size()>0) { 
			  pw.println("<ul style='color:red'>"); 
		  for(String  errMsg:errorsList) 
			  pw.println("<li>"+errMsg+"</li>"); 
		  pw.println("</ul>");
		  return ; //block control going further }
	} //if
		}
		
		else
			age=Integer.parseInt(aged);

		// write b.logic(request processing logic)
		if (age < 18)
			pw.println("<h1 style='color:red;text-align:center'> Mr./Miss " + name + ", from " + addrs
					+ "  you are not eligible for corona vaccination </h1>");
		else
			pw.println("<h1 style='color:red;text-align:center'> Mr./Mrs./Miss " + name + ", from " + addrs
					+ "  you are eligible for corona vaccination, Make it happen </h1>");

		// add graphical hyperlink
		pw.println("<a href ='corona_vaccine.html'> <img src='images/home.png'> </a>");
		// close stream
		pw.close();
	}// doGet
}// Class

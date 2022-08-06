package com.DBOperations;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/find")
public class SearchByIDServ extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int sid=Integer.parseInt(req.getParameter("id"));	
			
		PrintWriter out= res.getWriter();
		res.setContentType("text/html");

		Properties props=new Properties();
		InputStream in=getServletContext().getResourceAsStream("/WEB-INF/application.properties");
		props.load(in);
		
		Connection conn=DBConfig.getConnection(props);
		
		if(conn!=null) {
			
			try { 			 
				 Statement stmt = conn.createStatement(); 
				 ResultSet  rs=stmt.executeQuery("select * from eproduct where id="+sid); 
				 
				 while(rs.next()) {
					 out.println("<center><h3><b>Data fetched successfully</b></h3></center>");
					 out.print("<center>"+rs.getInt(1)+",  "+rs.getString(2)+",  "+rs.getBigDecimal(3)+",  "+rs.getTimestamp(4)+"</center><br>");	 
				 }
			} catch (SQLException e) { 
				e.printStackTrace(); 
			} 
		} else
			out.print("Connection not Established");	
	}
}
	
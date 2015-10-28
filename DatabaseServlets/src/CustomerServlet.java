

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Connection getConnection()
  	{
  		Connection conn = null;
  		try
  		{
  			//URL of Oracle database server
  			String url = "jdbc:oracle:thin:system/password@localhost"; 
  			 try {
  					Class.forName("oracle.jdbc.driver.OracleDriver");
  				} catch (ClassNotFoundException e1) {
  					// TODO Auto-generated catch block
  					e1.printStackTrace();
  				}
  			//properties for creating connection to Oracle database
  			Properties props = new Properties();
  			props.setProperty("user", "testuserdb");
  			props.setProperty("password", "password");

  			//creating connection to Oracle database using JDBC
  			conn = DriverManager.getConnection(url,props);
  			
  		}
  		catch(SQLException e)
  		{
  			System.out.println(e.getMessage());
  			e.printStackTrace();
  			return null;
  		}
  		
  		return conn;
  	}
      
      
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		// TODO Auto-generated method stub
  		// Set response content type
  	      response.setContentType("text/html");
  	      
  	      // Actual logic goes here.
  	      String name = "ass",city = null,state = null,address = null,pc=null;
  	      String custid = "2";
  	    	//URL of Oracle database server
  	        String url = "jdbc:oracle:thin:system/password@localhost"; 
  	        try {
  				Class.forName("oracle.jdbc.driver.OracleDriver");
  			} catch (ClassNotFoundException e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
  	        //properties for creating connection to Oracle database
  	        Properties props = new Properties();
  	        props.setProperty("user", "testuserdb");
  	        props.setProperty("password", "password");
  	      
  	        //creating connection to Oracle database using JDBC
  	        Connection conn = null;
  			try {
  				conn = DriverManager.getConnection(url,props);
  			} catch (SQLException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}

  	        String sql ="select * from demo_customers where CUSTOMER_ID = "+ request.getParameter("custid");

  	        //creating PreparedStatement object to execute query
  	        PreparedStatement preStatement = null;
  	        ResultSet result = null;
  	        try {
  				preStatement = conn.prepareStatement(sql);
  				
  				result = preStatement.executeQuery();
  				result.next();
  				name = result.getString(2)+" "+result.getString(3);
				address = result.getString(4);
				city = result.getString(6);
				state = result.getString(7);
				pc=result.getString(8);
  			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	     
  	    PrintWriter out = response.getWriter();
  	   
		
		
  	    out.println("<!DOCTYPE html>");
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("<title>" + "Bloody Servlets" + "</title>");
		out.println(" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">");
		out.println("<body>");
		
		out.println(" <div class=\"container\">");
		out.println("<table class='table-bordered' border=1 width=50% height=50%>");
		out.println("<tr><th>Customer Name</th><th>Address</th><th>City</th><th>State</th><th>Postal Code</th><tr>");
		
		out.println("<tr>"+"<td>"+name+"</td>"+"<td>"+address+"</td>"+"<td>"+city+"</td>"+"<td>"+state+"</td>"+"<td>"+pc+"</td>"+"<tr>");
		out.println("</div>");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script>");
		out.println("</body>");
		out.println("</html>");
  	     
  	}

  	/**
  	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  	 */
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		doGet(request,response);
  		
  		// TODO Auto-generated method stub
  	}

}

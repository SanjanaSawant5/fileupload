<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Accept & Reject Form</title>
</head>
<body>

<h1 style="background-color: SteelBlue; text-align: center; color: white; padding: 30px; font-size: 30px;">Admin Portal</h1>
	<form action="SearchServlet" method="POST">
	
    <label for="propertycode">Property code:</label>
		<select name="propertycode" id="propertycode">
			<option value="-1">Select property code</option>
			
			
			<%
				try {  
					String Query = "select * from propertyname";
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkingportal","root","Intern@1234");
					Statement stm = conn.createStatement();
					ResultSet rs =  stm.executeQuery(Query);
					while(rs.next()) {
			%>
			<option value="<%=rs.getInt("id")%>"><%=rs.getString("code")%></option>
			<%
					}
				}
				catch(Exception ex) {
					ex.printStackTrace();
					out.println("Error  " + ex.getMessage());
				}
			%>
		</select>
		
    
    <label for="propertyname">Property Name:</label>
		<select name="propertyname" id="propertyname">
		<option value="-1">Select Property Name</option>
			
			<%
				try {  
					String Query = "select * from propertyname";
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkingportal","root","Intern@1234");
					Statement stm = conn.createStatement();
					ResultSet rs =  stm.executeQuery(Query);
					while(rs.next()) {
			%>
			<option value="<%=rs.getInt("id")%>"><%=rs.getString("property")%></option>
			<%
					}
				}
				catch(Exception ex) {
					ex.printStackTrace();
					out.println("Error  " + ex.getMessage());
				}
			%>
		</select>
     
   <button type="submit">Submit</button> </form>
	
</body>
<style>
 
body {
  font-family: Arial, sans-serif;
  background-color: #f5f5f5;
}

form {
  width: 50%;
  margin: 0 auto;
  text-align: center;
}

input[type="submit"] {
  background-color: LightSkyBlue;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type="submit"]:hover {
  background-color: #4682B4;
}
</style>
</html>
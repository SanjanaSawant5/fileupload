 <%@page import="org.fileupload.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.util.*"%>
    <%@ page import="java.util.Date" %>
    <%@ page import="java.text.SimpleDateFormat" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Information</title>
<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet">
</head>
<body>
    <%
    User user = (User) session.getAttribute("user");
	%>
<div class=desktop___1_1_35>
  <div class=group_3_1_36>
    <div class=desktop___1_1_37>
      
      <div class="rectangle_2_1_38"></div>
      
      <div class="rectangle_3_1_39"></div>
      <span  class="triumph_residential_1_40">Triumph Residential</span>
      
      <span  class="welcome_tenant_name_1_41"> Welcome <%= user.getName() %></span>
      
      <div class=group_2_1_42>
        <div class="rectangle_4_1_43"></div>
      </div>
      
      <span  class="tenant_name___1_51"> Tenant Name: <%= user.getName()%></span>
      
      <span  class="property_name___1_52"> Property Name: <%= user.getPropertyname()%></span>
      
      <span  class="unit___1_53"> Unit: <%= user.getUnit()%></span>
      
      <span  class="vehicle_model___1_54"> vehicle model: <%= user.getVehiclemodel1()%></span>
      
      <span  class="vehicle_registration_number___1_55"> vehicle registration number: <%= user.getVehiclenumber1()%></span>
      
      <span  class="vehicle_license_number___1_56"> vehicle license number: <%= user.getLicensenumber1()%></span>
      
      <span class="vehicle_registration_expire_date___1_57"> Vehicle registration expires on: 
      <% 
      String expireDateStr = user.getRegistrationexpiredate1();
      boolean isExpired = false;
    
      if(expireDateStr != null && !expireDateStr.isEmpty()) {
      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expireDate = dateFormat.parse(expireDateStr);
        Date currentDate = new Date();
        
        if(expireDate.before(currentDate)) {
          isExpired = true;
        }
        
        expireDateStr = dateFormat.format(expireDate); // Format the date object to a string
       } catch(Exception e) {
        e.printStackTrace();
       }
     }
  
     if(isExpired) { 
      out.print("<span style=\"color: red; animation: blinker 1.5s linear infinite; font-family: sans-serif;} @keyframes blinker { 50% { opacity: 0; }\">Expired</span>");
     } else { 
      out.print(expireDateStr);
     } 
     %></span>
     
      <span class="vehicle_insurance_expire_date___1_58"> Vehicle insurance expires on:  
      <% 
      String expireDateStr1 = user.getInsuranceexpiredate1();
      boolean isExpire = false;
    
      if(expireDateStr != null && !expireDateStr.isEmpty()) {
      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expireDate = dateFormat.parse(expireDateStr);
        Date currentDate = new Date();
        
        if(expireDate.before(currentDate)) {
          isExpired = true;
        }
        
        expireDateStr = dateFormat.format(expireDate); // Format the date object to a string
       } catch(Exception e) {
        e.printStackTrace();
       }
      }
  
      if(isExpired) { 
      out.print("<span style=\"color: red; animation: blinker 1.5s linear infinite; font-family: sans-serif;} @keyframes blinker { 50% { opacity: 0; }\">Expired</span>");
      } else { 
      out.print(expireDateStr);
     } 
     %></span>

      
      <span  class="status___1_59">status1: <%=user.getStatus1()%></span>
      
       <div class=group_2_2_42>
        <div class="rectangle_4_2_43"></div>
      </div>
      
      <span  class="vehicle_model___2_54"> vehicle model2: <%= user.getVehiclemodel2()%></span>
      
      <span  class="vehicle_registration_number___2_55"> vehicle registration2 number: <%= user.getVehiclenumber2()%></span>
      
      <span  class="vehicle_license_number___2_56"> vehicle license2 number: <%= user.getLicensenumber2()%></span>
      
      <span class="vehicle_registration_expire_date___2_57"> Vehicle registration2 expires on:  
      <% 
       String expireDateStr2 = user.getRegistrationexpiredate2();
       boolean isExpired1 = false;
    
       if(expireDateStr2 != null && !expireDateStr2.isEmpty()) {
       try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expireDate = dateFormat.parse(expireDateStr2);
        Date currentDate = new Date();
        
        if(expireDate.before(currentDate)) {
          isExpired = true;
        }
        
        expireDateStr = dateFormat.format(expireDate); // Format the date object to a string
       } catch(Exception e) {
        e.printStackTrace();
       }
     }
  
     if(isExpired1) { 
      out.print("<span style=\"color: red; animation: blinker 1.5s linear infinite; font-family: sans-serif;} @keyframes blinker { 50% { opacity: 0; }\">Expired</span>");
     } else { 
      out.print(expireDateStr2);
     } 
     %></span>
     
      <span class="vehicle_insurance_expire_date___2_58"> Vehicle insurance2 expires on:  
      <% 
      String expireDateStr3 = user.getInsuranceexpiredate2();
      boolean isExpired2 = false;
    
      if(expireDateStr3 != null && !expireDateStr3.isEmpty()) {
      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expireDate = dateFormat.parse(expireDateStr3);
        Date currentDate = new Date();
        
        if(expireDate.before(currentDate)) {
          isExpired = true;
        }
        
        expireDateStr = dateFormat.format(expireDate); // Format the date object to a string
       } catch(Exception e) {
        e.printStackTrace();
       }
      }
  
     if(isExpired2) { 
      out.print("<span style=\"color: red; animation: blinker 1.5s linear infinite; font-family: sans-serif;} @keyframes blinker { 50% { opacity: 0; }\">Expired</span>");
     } else { 
      out.print(expireDateStr3);
    } 
    %></span>
   
      <span  class="status___2_59"> status2: <%= user.getStatus2()  %></span> 
      
      <div class="rectangle_6_1_62"></div>
      
      <span  class="registration_document_1_61"> Registration Document: </span>
       
      
      <span  class="insurance_document_1_63"> Insurance Document: </span>
       
      
      <div class="rectangle_7_1_64"></div>
      <div class="rectangle_8_1_67"></div>
      
      <div class="rectangle_6_2_62"></div>
      
      <span  class="registration_document_2_61"> Registration Document: </span>
       
      
      <span  class="insurance_document_2_63"> Insurance Document: </span>
       
      
      <div class="rectangle_7_2_64"></div>
      <div class="rectangle_8_2_67"></div>
      <div class="line_1_14_59"></div>
      <div class="rectangle_9_14_61"></div>
      <form action="LogoutServlet" method="GET">
  
  <button type="submit">Logout</button> </form></div> 
</body>
</html>
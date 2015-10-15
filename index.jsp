<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        
<%@page import="server.*"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Flight Calculator</title>
	<style media="screen" type="text/css">
		textarea { 
    		resize: none;
    	}
	</style>
</head>
<body>		
	<% 
	List<Result> resultList; 
	String from;
	String to;
	String method;
	try {
		from = (String)request.getAttribute("from");
		to = (String)request.getAttribute("to");
		method = (String)request.getAttribute("method");
    	resultList = (List<Result>)request.getAttribute("resultList");    	
    %>
	<h2>Flight Calculator</h2>
	<hr>
    <form name="calculator "method="post" action="/calc/submit/">    
    	<p>From:<Br>
   		<textarea required name="from_city" cols="40" rows="1"><%= from == null ? "" : from %></textarea>
   		<p>To:<Br>
   		<textarea required name="to_city" cols="40" rows="1"><%= to == null ? "" : to %></textarea> 
   		<p>Method:<Br>
    	<select name="calc_method">
    	<%    
    	CalculatorFactory calculatorFactory=CalculatorFactory.getInstance();
    	Set<String> calcSet = calculatorFactory.getFactoryList(); 
    	for(String currentMethod : calcSet) {
    	%>
    		<option value="<%= currentMethod %>"
    		<%if(method != null && method.equals(currentMethod)) {
    		%> selected="selected" <% } %>><%= currentMethod %></option>
    	<% } %>    		
    		<option value="All" <% if(method != null && method.equals("All")) { %> selected="selected" <% } %>>All</option>
		</select>
		<p><input type="submit" value="Calculate"></p>
    </form>
    <% if(resultList != null) { %> 
    		<table bgcolor=black border=0 cellpadding=2 cellspacing=1>
    		<tr>    			
    			<td bgcolor=white>Method</td>
    			<td bgcolor=white>Distance</td>    		
    		</tr>
    		<% for(Result result : resultList) {  %>
    		<tr>    			
    			<td bgcolor=white><%=result.getMethod() %></td>
    			<td bgcolor=white><%=result.getDistance() %></td>
    		</tr>
    		<% 
    		} %>
    		</table>
    		<%
    	}
    }
    	catch (NullPointerException ex){ }
    %>
</body>
</html>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%!
		Connection connection;
		Statement stmt;
		ResultSet resultSet;
	
		private String name, id, pw, pNum, pNum2, pNum3, gender;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>modify.jspe</title>
</head>
<body>
	<%
		id = (String)session.getAttribute("id");
		name =  (String)session.getAttribute("name");
		
		String query = "select * from members where id = '" + id + "' ";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
		stmt = connection.createStatement();
		resultSet = stmt.executeQuery(query);
	
		while(resultSet.next()){
			
			name = resultSet.getString("name");
			id = resultSet.getString("id");
			pw = resultSet.getString("pw");
			pNum = resultSet.getString("pNum");
			pNum2 = resultSet.getString("pNum2");
			pNum3 = resultSet.getString("pNum3");
			gender = resultSet.getString("gender");
		}
	%>

	
		<form action="ModifyOk" method="post">
		이름 : <input type="text" name="name" size=10 value=<%= name %>><br/> 
		아이디 : <%= id %><br/> 
		비밀번호 : <input type="password" name="pw" size=10><br/> 
		번호 : <select name="pNum">
				<option value="010" selected="delected">010</option>
				<option value="011">011</option> 
				<option value="016">016</option>
				<option value="017">017</option> 
				<option value="018">018</option> 
			  </select> -
			  	<input type="text" name="pNum2" size=5> -
				<input type="text" name="pNum3" size=5><br/>
				
				<% if(gender.equals("man")) { %>
		성별 : <input type="radio" name="gender" value="man" checked="checked">남자
			  <input type="radio" name="gender" value="woman">여자<br/>
			  <% } else {  %>
			  	성별 : <input type="radio" name="gender" value="man" checked="checked">남자
			  <input type="radio" name="gender" value="woman" checked="checked">여자<br/>
			  <% } %>
			  <input type="submit" value="수정"> <input type="reset" value="취소">
	</form>
</body>
</html>
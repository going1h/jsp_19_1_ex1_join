<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%!
    	String name, id, pw;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>loginResult.jsp</title>
</head>
<body>
	<%
	name =  (String)session.getAttribute("name");
	id = (String)session.getAttribute("id");
	pw = (String)session.getAttribute("pw");
	%>
	<%=name %>�� �α��ο� �����ϼ̽��ϴ�.<br>
	<%=name %>���� ID�� "<%=id %>"�Դϴ�.<br>
	�����Ͻ� ȸ�������� �����ϽǷ��� �Ʒ� [ȸ����������]�� Ŭ�����ּ���<br><br>

<h2><a href="modify.jsp">[ȸ����������]</a></h2>
	
</body>
</html>
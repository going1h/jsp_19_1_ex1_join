package com.javaex.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class joinOK
 */
@WebServlet("/JoinOK")
public class JoinOK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private Connection connection; //DB����,���� �ϴ� ��ü
	private Statement stmt; //query�� ���� ��ü
	
	private String name, id, pw, pNum,pNum2,pNum3,gender;
	
    public JoinOK() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet!");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		actionDo(request, response);
		System.out.println("doPost!");
		
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		
		name = request.getParameter("name");
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		pNum = request.getParameter("pNum");
		pNum2 = request.getParameter("pNum2");
		pNum3 = request.getParameter("pNum3");
		gender = request.getParameter("gender");
		
		String query = "insert into members values('" + name + "','" + id + "', '" + pw + "', '" + pNum + "', '" + pNum2 + "', '" + pNum3 + "', '" + gender + "')";
		
		try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
	         stmt = connection.createStatement();
	         
	         int i = stmt.executeUpdate(query);//query�� �����ϸ� ���� 1�� ��ȯ
	         
	         if(i == 1) {
	            System.out.println("DB ���� ����!!!");
	            response.sendRedirect("joinResult.jsp");//DB���忡 �����ϸ� joinResult.jsp�� �̵�
	         } else {
	            System.out.println("DB ���� ����!!!");
	            response.sendRedirect("join.html");//DB���忡 �����ϸ� join.html�� �ٽ� �̵�
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if(stmt != null) stmt.close();
	            if(connection != null) connection.close();
	         } catch (Exception e){
	            e.printStackTrace();
	         }
	      }
	}

}

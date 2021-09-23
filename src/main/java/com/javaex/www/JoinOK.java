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
	
	private Connection connection; //DB연결,접속 하는 객체
	private Statement stmt; //query에 관한 객체
	
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
	         
	         int i = stmt.executeUpdate(query);//query가 성공하면 정수 1을 반환
	         
	         if(i == 1) {
	            System.out.println("DB 저장 성공!!!");
	            response.sendRedirect("joinResult.jsp");//DB저장에 성공하면 joinResult.jsp로 이동
	         } else {
	            System.out.println("DB 저장 실패!!!");
	            response.sendRedirect("join.html");//DB저장에 실패하면 join.html로 다시 이동
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

package com.javaex.www;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifyOk
 */
@WebServlet("/ModifyOk")
public class ModifyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connection connection;
	private Statement stmt;
	
	private HttpSession httpSession;
	private String name, id, pw, pw2, pNum, pNum2, pNum3, gender;
    public ModifyOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		actionDo(request,response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		httpSession = request.getSession(); // getSession으로 현재값을 불러오는 과정이 필요함. 중요!
		
		name = request.getParameter("name");
		// id = request.getParameter("id");
		id = (String)httpSession.getAttribute("id");
		pw2 = (String)httpSession.getAttribute("pw");
		pw = request.getParameter("pw");
		pNum = request.getParameter("pNum");
		pNum2 = request.getParameter("pNum2");
		pNum3 = request.getParameter("pNum3");
		gender = request.getParameter("gender");
		
		if(pw2.equals(pw)) { // 입력된 pw와 세션의 pw 비교를 해서 맞을경우 정보수정 equals는 객체비교하는 것
			// equals(변수)
		
			String query = "update members set name='"+ name + "' , pNum = '" + pNum + "' , pNum2 = '" + pNum2 + "', pNum3 = '" + pNum3 + "' , gender = '" + gender + "' where id = '" + id + "'	";
		
			try {
		         Class.forName("oracle.jdbc.driver.OracleDriver");
		         connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
		         stmt = connection.createStatement();
		         
		         int i = stmt.executeUpdate(query);//query가 성공하면 정수 1을 반환
		         
		         if(i == 1) {
		            System.out.println("정보 수정 성공!!!");
		            httpSession.setAttribute("nama", name); // 세션 값까지 수정해주기
		            response.sendRedirect("modifyResult.jsp");//정보 수정에 성공하면 modifyRwsult.jsp로 이동
		         } else {
		            System.out.println("정보 수정 실패!!!");
		            response.sendRedirect("modify.jsp");// 정보 수정에 실패하면 modify.jsp로 이동하여 다시 정보 입력
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
		} else {
			System.out.println("비밀번호가 틀립니다.");
			response.sendRedirect("modify.jsp");// 정보 수정에 실패하면 modify.jsp로 이동하여 다시 정보 입력
		}
	}
}

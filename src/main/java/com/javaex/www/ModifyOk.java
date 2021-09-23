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
		httpSession = request.getSession(); // getSession���� ���簪�� �ҷ����� ������ �ʿ���. �߿�!
		
		name = request.getParameter("name");
		// id = request.getParameter("id");
		id = (String)httpSession.getAttribute("id");
		pw2 = (String)httpSession.getAttribute("pw");
		pw = request.getParameter("pw");
		pNum = request.getParameter("pNum");
		pNum2 = request.getParameter("pNum2");
		pNum3 = request.getParameter("pNum3");
		gender = request.getParameter("gender");
		
		if(pw2.equals(pw)) { // �Էµ� pw�� ������ pw �񱳸� �ؼ� ������� �������� equals�� ��ü���ϴ� ��
			// equals(����)
		
			String query = "update members set name='"+ name + "' , pNum = '" + pNum + "' , pNum2 = '" + pNum2 + "', pNum3 = '" + pNum3 + "' , gender = '" + gender + "' where id = '" + id + "'	";
		
			try {
		         Class.forName("oracle.jdbc.driver.OracleDriver");
		         connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
		         stmt = connection.createStatement();
		         
		         int i = stmt.executeUpdate(query);//query�� �����ϸ� ���� 1�� ��ȯ
		         
		         if(i == 1) {
		            System.out.println("���� ���� ����!!!");
		            httpSession.setAttribute("nama", name); // ���� ������ �������ֱ�
		            response.sendRedirect("modifyResult.jsp");//���� ������ �����ϸ� modifyRwsult.jsp�� �̵�
		         } else {
		            System.out.println("���� ���� ����!!!");
		            response.sendRedirect("modify.jsp");// ���� ������ �����ϸ� modify.jsp�� �̵��Ͽ� �ٽ� ���� �Է�
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
			System.out.println("��й�ȣ�� Ʋ���ϴ�.");
			response.sendRedirect("modify.jsp");// ���� ������ �����ϸ� modify.jsp�� �̵��Ͽ� �ٽ� ���� �Է�
		}
	}
}

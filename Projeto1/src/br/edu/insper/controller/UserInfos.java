package br.edu.insper.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.javabeans.Users;
import br.edu.insper.model.DAO;

/**
 * Servlet implementation class UserInfos
 */
@WebServlet("/userInfos")
public class UserInfos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String adress = (String)request.getAttribute("adress");
		Integer userId = (Integer)request.getAttribute("userId");
		
		if (adress == null || userId == null) {
			adress = (String)request.getParameter("adress");
			userId = Integer.valueOf(request.getParameter("userId"));
		}
		
		Users user = null;
		try {
			user = dao.getUserById(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String userName = user.getName();
		String userPassword = user.getPassword();	
		
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("userPassword", userPassword);
		try {
			request.setAttribute("users", dao.getUsers());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.getRequestDispatcher(adress).forward(request, response);
		
		try {
			dao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

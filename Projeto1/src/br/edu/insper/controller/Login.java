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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		doGet(request, response);
		
		String nameUser = request.getParameter("nameUser");
		String passUser	= request.getParameter("passwordUser");
		
		Users user = new Users();
		user.setName(nameUser);
		user.setPassword(passUser);
		
		DAO dao = null;
		try {
			
			dao = new DAO();
			
			if(nameUser.contentEquals("") || passUser.contentEquals("")) {
				
				request.setAttribute("login", false);
				request.getRequestDispatcher("./login.jsp").forward(request, response);
			} 
			else if(dao.login(user)) {

				Integer userId = dao.getUserIdByLogin(user);
				request.setAttribute("userId", userId);
				request.setAttribute("adress", "home.jsp");
				request.getRequestDispatcher("/userInfos").forward(request, response);
			
			} else {
				
				request.setAttribute("cadastro", 5);
				request.setAttribute("login", false);
				request.getRequestDispatcher("./login.jsp").forward(request, response);
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

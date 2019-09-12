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
 * Servlet implementation class Cadastro
 */
@WebServlet("/cadastro")
public class Cadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cadastro() {
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

		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		String newName = request.getParameter("newNameUser");
		String newPassword = request.getParameter("newPasswordUser");
		String confirmNewPassword = request.getParameter("confirmNewPasswordUser");
		
		if (newName.contentEquals("") || newPassword.contentEquals("") || confirmNewPassword.contentEquals("")) {
			
			request.setAttribute("cadastro", 1);
			request.getRequestDispatcher("./login.jsp").forward(request, response);
			
		} else
			try {
				if (dao.getUserName(newName)) {
					request.setAttribute("cadastro", 2);
					request.getRequestDispatcher("./login.jsp").forward(request, response);
					
				} 
				else if (!newPassword.contentEquals(confirmNewPassword)) {
					request.setAttribute("cadastro", 3);
					request.getRequestDispatcher("./login.jsp").forward(request, response);
					
				} 
				else {
					
					Users newUser = new Users();
					newUser.setName(newName);
					newUser.setPassword(newPassword);

					try {
						dao.cadastrarNewUser(newUser);
						dao.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					request.setAttribute("cadastro", 0);
					request.getRequestDispatcher("./login.jsp").forward(request, response);
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

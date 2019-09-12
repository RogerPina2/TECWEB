package br.edu.insper.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.javabeans.Users;
import br.edu.insper.model.DAO;

/**
 * Servlet implementation class CompartilharPost
 */
@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
			
		DAO dao;
		try {
			dao = new DAO();

			String userNameToAdd = request.getParameter("userNameToAdd");
			List<Users> usersAdd = new ArrayList<>();
			
			String usersAdicionados = request.getParameter("usersAdicionados");
			
			if(usersAdicionados != null && !usersAdicionados.contentEquals("")) {
				String[] array = usersAdicionados.split("/");
				
				Integer i = 0;
				while(i < array.length) {
					
					String userName = String.valueOf(array[i]);
					
					if(!userName.contentEquals(userNameToAdd)) {
						usersAdd.add(dao.getUserByName(String.valueOf(array[i])));
					}
					
					i++;
				}
			}
			
			usersAdd.add(dao.getUserByName(userNameToAdd));
						
			request.setAttribute("users", dao.getUsers());
			request.setAttribute("usersAdd", usersAdd);
			request.getRequestDispatcher("criarNovoPostCompartilhado.jsp").forward(request, response);
			
			dao.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package br.edu.insper.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.javabeans.Posts;
import br.edu.insper.javabeans.Users;
import br.edu.insper.model.DAO;

/**
 * Servlet implementation class VerPosts
 */
@WebServlet("/verPosts")
public class VerPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPosts() {
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
		
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		
		DAO dao = null;
		try {
			dao = new DAO();
			
			Users user = dao.getUserById(userId);
			request.setAttribute("userId", userId);
			request.setAttribute("userName", user.getName());
			
			List<Posts> posts = null;
			posts = (List<Posts>)dao.getPosts(userId);
			request.setAttribute("Posts", posts);
			request.getRequestDispatcher("meusPosts.jsp").forward(request, response);
					
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

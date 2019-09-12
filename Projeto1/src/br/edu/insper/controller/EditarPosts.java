package br.edu.insper.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.model.DAO;

/**
 * Servlet implementation class EditarPosts
 */
@WebServlet("/editarPosts")
public class EditarPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPosts() {
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
		
		String nomePost = request.getParameter("namePost");
		String textoPost = request.getParameter("textPost");
		
		Integer postId = Integer.valueOf(request.getParameter("postId"));
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		
		
		if(nomePost.contentEquals("") || textoPost.contentEquals("")) {
			
			request.setAttribute("blankSpace", true);
			request.setAttribute("userId", userId);
			request.setAttribute("postId", postId);
			request.getRequestDispatcher("editarPost.jsp").forward(request, response);
			
		} else {
		
			DAO dao;
			try {
				dao = new DAO();
				dao.editPosts(postId, nomePost, textoPost, userId, data, hora);
				dao.close();
				
				request.setAttribute("userId", userId);
				request.getRequestDispatcher("/verPosts").forward(request, response);;
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

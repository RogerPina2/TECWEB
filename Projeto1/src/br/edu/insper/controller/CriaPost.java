package br.edu.insper.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * Servlet implementation class CriaPost
 */
@WebServlet("/criaPost")
public class CriaPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriaPost() {
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
		
		String nomePost = request.getParameter("namePost");
		String textoPost = request.getParameter("textPost");
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		Boolean shared = Boolean.valueOf(request.getParameter("shared"));
		List<Users> users =  new ArrayList<>();
		try {
			users = dao.getUsers();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if(nomePost.contentEquals("") || textoPost.contentEquals("")){

			request.setAttribute("users", users);
			request.setAttribute("blankSpace", true);
			request.setAttribute("userId", userId);
			if(!shared) {
				request.getRequestDispatcher("createNewIndividualPost.jsp").forward(request, response);
			} 
			else {
				request.getRequestDispatcher("criarNovoPostCompartilhado.jsp").forward(request, response);
			}
			
		} else {
			Date dataHoraAtual = new Date();
			String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
			String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
			
			Posts newPost = new Posts();
			newPost.setName(nomePost);
			newPost.setText(textoPost);
			newPost.setIdUser(userId);
			newPost.setData(data);
			newPost.setHora(hora);
			newPost.setShared(shared);
			
			try {
				dao.cadastrarNovoPost(newPost);
				dao.close();
				
				request.setAttribute("users", users);
				request.setAttribute("mensagem", true);
				request.setAttribute("userId", userId);
				
				if(!shared) {
					request.getRequestDispatcher("createNewIndividualPost.jsp").forward(request, response);
				} 
				else {
					request.getRequestDispatcher("criarNovoPostCompartilhado.jsp").forward(request, response);
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
		}	
	}

}

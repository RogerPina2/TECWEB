package br.edu.insper.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostInfos
 */
@WebServlet("/postInfos")
public class PostInfos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostInfos() {
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
		Integer postId = Integer.valueOf(request.getParameter("postId"));
		String postNome = request.getParameter("postNome");
		String postTexto = request.getParameter("postTexto");
		
		String adress = request.getParameter("adress");
		
		request.setAttribute("userId", userId);
		request.setAttribute("postId", postId);
		request.setAttribute("postNome", postNome);
		request.setAttribute("postTexto", postTexto);
		request.getRequestDispatcher(adress).forward(request, response);
	}

}

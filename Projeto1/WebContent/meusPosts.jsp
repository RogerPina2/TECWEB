<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="br.edu.insper.javabeans.Posts" %>
<!DOCTYPE html>
<html>
<head>
<link href="css/meusPosts.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Criar Novo Post</title>
</head>
	<body>
		<div class="header">
			<div class="box">
				<h1>
					<a href="index.html">
						SharePost
					</a>
				</h1>
			</div>
		</div>
		<div class="body">
			
			<% Integer userId = (Integer)request.getAttribute("userId"); %>
			<% String userName = (String)request.getAttribute("userName"); %>
			<% List<Posts> posts = (List<Posts>)request.getAttribute("Posts"); %>
			<% if(posts.size() != 0){
				for(Posts post:posts){
					
					Integer postId = post.getId();
					String postNome = post.getName();
					String postTexto = post.getText();
					String postData = post.getData();
					String postHora = post.getHora();
				%>
			<div class="box">
				<%
					out.print("<br>Post criado por " + userName + " dia " + postData + " as " + postHora + "</br>");
					out.print("<br>Nome do Post: " + postNome + "</br>");
					out.print("<br>Conteúdo do Post: " + postTexto + "</br>");
				%>
			
			
				<form action="./postInfos" method="post">
					<input type="hidden" name="userId" value="<%= userId %>">
					<input type="hidden" name="postId" value="<%= postId %>">
					<input type="hidden" name="postNome" value="<%= postNome %>">
					<input type="hidden" name="postTexto" value="<%= postTexto %>">
					<input type="hidden" name="adress" value="/editarPost.jsp">
					 
					<input type="submit" value="Editar">
				</form>
		
				<form action="./deletarPost" method="post">
					<input type="hidden" name="userId" value="<%= userId %>">
					<input type="hidden" name="postId" value="<%= postId %>">
					<input type="submit" value="Apagar">
				</form>
			</div>
			<% }
			} else {
				out.print("<br>Você não possui Posts</br>");
			}%>
		</div>
		<div class="footer">
			<br>
			<form action="./userInfos" method="post">
			
				<input type="hidden" name="userId" value="<%= userId %>">
				<input type="hidden" name="adress" value="home.jsp">
				
				<input type="submit" value="Voltar">
			</form>
		</div>
	</body>
</html>
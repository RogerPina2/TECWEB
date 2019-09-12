<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="br.edu.insper.javabeans.Users" %>
<!DOCTYPE html>
<html>
<head>
	<link href="css/home.css" rel="stylesheet">
	<meta charset="UTF-8">
	<title>SharePosts</title>
</head>
	<body>
		<% Integer userId = (Integer)request.getAttribute("userId"); %>
		<% String userName = (String)request.getAttribute("userName"); %>
		
	
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
			<% out.print("<h1>Bem Vindo, " + userName + "!</h1>"); %> 
		
			<br>
			<form action="./createNewIndividualPost.jsp">
				<% session.setAttribute("userId", userId); %>
				<% session.setAttribute("mensagem", false); %>
				Criar novo Post Individual: <button type="submit">CRIAR</button>
			</form>
			
			<br>
			<form action="./userInfos" method="post">
				<input type="hidden" name="userId" value="<%= userId %>">
				<input type="hidden" name="adress" value="criarNovoPostCompartilhado.jsp">
				Criar novo Post Compartilhado: <button type="submit">CRIAR</button>
			</form>
			
			<br>
			<form action="./verPosts" method="post">
				<input type="hidden" name="userId" value="<%= userId %>">
				Ver meus Posts: <button type="submit">VER</button>
			</form>
			
			<br>
			<form action="./login.jsp">
				<input type="submit" value="SAIR">
			</form>		
		</div>
	</body>
</html>
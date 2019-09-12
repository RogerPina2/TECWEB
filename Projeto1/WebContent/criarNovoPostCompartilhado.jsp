<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="br.edu.insper.javabeans.Users" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/criarNovoPostCompartilhado.css" rel="stylesheet">
<title>Criar Novo Post Compartilhado</title>
</head>
<body>
	<div class="header">
		<div class="box">
			<h1>
				<a href="index.html"> SharePost </a>
			</h1>
		</div>
	</div>
	<div class="body">
		
		<% Integer userId = (Integer)session.getAttribute("userId"); %>
	
		<div class="column">	
			<form action="./criaPost" method="post">
				<h1>Criar novo Post Compartilhado</h1>
				
				Nome do Post: <input type="text" name="namePost" size="20">
				<br>
				Insira o texto: <input type="text" name="textPost" size="40">
				
				<% if((Boolean)request.getAttribute("blankSpace") != null) {
					Boolean blankSpace = (Boolean)request.getAttribute("blankSpace");
					if(blankSpace){
						out.print("<br>Não é permitido espaço em Branco");
					}
				} %>
				
				<br><input type="submit" value="Create Post">
		
				<% Boolean mensagem = null; %>
				<% if ((Boolean)request.getAttribute("mensagem") == null){
					mensagem = (Boolean)session.getAttribute("mensagem");
				} else {
					mensagem = (Boolean)request.getAttribute("mensagem");
			    }
				if(mensagem == true){
					out.print("Mensagem salva com sucesso");
				} %>
				
				<input type="hidden" name="shared" value="<%= true %>">
				<input type="hidden" name="userId" value="<%= userId %>">
			</form>
				
			<form action="./userInfos" method="post">
				<input type="hidden" name="userId" value="<%= userId %>">
				<input type="hidden" name="adress" value="home.jsp">
				
				<input type="submit" value="Voltar">
			</form>
		</div>
		
		<% String usersAdicionados = new String(); %> 
		
		<div class="column">
			<div class="columnB">
				<h1>Usuários adicionados no seu Post</h1>
				<% if((List<Users>)request.getAttribute("usersAdd") != null){
					List<Users> usersAdd = (List<Users>)request.getAttribute("usersAdd");
					if(usersAdd.size() != 0) {
						for(Users userAdd: usersAdd){
							
							String userAddName = userAdd.getName();
							usersAdicionados += userAdd.getName() + "/";
							%>
							<form action="./delUser" method="post">
								<%= userAddName %>
								<input type="hidden" name="usersAdicionados" value="<%= usersAdicionados %>">
								<input type="hidden" name="userNameToDel" value="<%= userAddName %>">
								<input type="submit" value="DEL">
							</form>
							<%
						}							
					} else {
						out.print("Você não compartilhou com nenhum usuário!");
					}
				} %>
			</div>
		</div>
		<div class="column">
			<div class="columnC">
				<h1>Adicionar Usuários ao Post</h1>
				<% List<Users> users = (List<Users>)request.getAttribute("users");%>
				<% for(Users user: users){
					String userName = user.getName();
					%>
					<form action="./addUser" method="post">
						<%= userName %>
						<input type="hidden" name="usersAdicionados" value="<%= usersAdicionados %>">
						<input type="hidden" name="userNameToAdd" value="<%= userName %>">
						<input type="submit" value="ADD">
					</form>
					<%
				} %>
			</div>
		</div>
	</div>
	
	<input type="hidden" name="users" value="<%= users %>">

</body>
</html>
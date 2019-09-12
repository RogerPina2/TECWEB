<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Criar Novo Post</title>
</head>
	<body>
		
		<% Integer userId = (Integer)session.getAttribute("userId"); %>
	
		<h1>Create your new Individual Post</h1>
	
		<form action="./criaPost" method="post">
			<br>Nome do Post: <input type="text" name="namePost">
			<br>Insira o texto: <input type="text" name="textPost">
			
			<% if((Boolean)request.getAttribute("blankSpace") != null){
				Boolean blankSpace = (Boolean)request.getAttribute("blankSpace");
				if(blankSpace){
					out.print("Não é permitido espaço em Branco");
				}
			}%>
			
			<br><input type="submit" value="Salvar Post">

			<% Boolean mensagem = null; %>
			<% if ((Boolean)request.getAttribute("mensagem") == null){
				mensagem = (Boolean)session.getAttribute("mensagem");
			} else {
				mensagem = (Boolean)request.getAttribute("mensagem");
		    } %>
			
			<% if(mensagem == true){
				out.print("Mensagem salva com sucesso");
			} %>
			
			<input type="hidden" name="shared" value="<%= false %>">
			<input type="hidden" name="userId" value="<%= userId %>">
		</form>
		
		<form action="./userInfos" method="post">
			<input type="hidden" name="userId" value="<%= userId %>">
			<input type="hidden" name="adress" value="home.jsp">
			
			<input type="submit" value="Voltar">
		</form>
	</body>
</html>
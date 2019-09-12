<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Post</title>
</head>
	<body>
		<form action="./editarPosts" method="post">
		
			<% Integer userId = (Integer)request.getAttribute("userId"); %>
			<% Integer postId = (Integer)request.getAttribute("postId"); %>
			<% String postNome = (String)request.getAttribute("postNome"); %>
			<% String postTexto = (String)request.getAttribute("postTexto"); %>

			<br>Nome do Post: <input type="text" name="namePost" value="<%= postNome %>">
			<br>Insira o texto: <input type="text" name="textPost" value="<%= postTexto %>">
			
			<% if((Boolean)request.getAttribute("blankSpace") != null){
				Boolean blankSpace = (Boolean)request.getAttribute("blankSpace");
				if(blankSpace){
					out.print("Não é permitido espaço em Branco");
				}
			}%>
			
			<input type="hidden" name="userId" value="<%= userId %>">
			<input type="hidden" name="postId" value="<%= postId %>">
			
			<br><input type="submit" value="Salvar Post Editado">

		</form>
		<form action="./verPosts" method="post">
			<input type="hidden" name="userId" value="<%= userId %>">
			<input type="submit" value="Voltar">
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="css/login.css" rel="stylesheet">
		<meta charset="UTF-8">
		<title>Log In</title>
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
			<div class="box">
				<h1>FAÇA SEU LOGIN</h1>
				<form action="./login" method="post">
					<br>
					Usuário: <input type="text" name="nameUser">
					<br>
					Senha: <input type="password" name="passwordUser">
					<br>
					<input type="submit" value="Entrar">
			
					<% if((Boolean)request.getAttribute("login") != null){
						Boolean login = (Boolean)request.getAttribute("login");
						if(!login){
							out.print("Login Inválido!");
						}
					} %>
				</form>
			</div>
			
			<div class="box">
				<h1>FAÇA SEU CADASTRO</h1>
				<form action="./cadastro" method="post">
					<br>
					Usuário: <input type="text" name="newNameUser">
					<br>
					Senha: <input type="password" name="newPasswordUser">
					<br>
					Confirme sua Senha: <input type="password" name="confirmNewPasswordUser">
					<br>
					<input type="submit" value="Criar Cadastro">
					
					<% if((Integer)request.getAttribute("cadastro") != null){
						Integer cadastro = (Integer)request.getAttribute("cadastro");
						if (cadastro == 0){
							out.print("Cadastro bem sucedido!");
						}
						else if(cadastro == 1){
							out.print("Algo deu errado!");
						} 
						else if (cadastro == 2){
							out.print("Nome de Usuário já utilizado!");
						} 
						else if (cadastro == 3){
							out.print("Senha não bate!");
						}
					}%>
				</form>
			</div>
		</div>
	</body>
</html>
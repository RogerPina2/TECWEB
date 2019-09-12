package br.edu.insper.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.insper.javabeans.Posts;
import br.edu.insper.javabeans.SharedPosts;
import br.edu.insper.javabeans.Users;

public class DAO {
	private Connection connection = null;
	public DAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/Projeto1", "root", "rKTr12pNKa");
	}

	// ============================== USERS ==============================
	
	public List<Users> getUsers() throws SQLException {
		
		List<Users> users = new ArrayList<Users>();
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Users user = new Users();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			users.add(user);
		}
		
		rs.close();
		stmt.close();
		
		return users;
	}
	
	public void cadastrarNewUser(Users user) throws SQLException {
		
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users (name, password) VALUES (?, ?)");
		
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getPassword());
		stmt.execute();
		stmt.close();
	}
	
	public boolean login(Users user) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE name = ? AND password = ?");
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getPassword());
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getUserName(String nome) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
		stmt.setString(1, nome);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Users getUserById(Integer idUser) throws SQLException {
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
		stmt.setInt(1, idUser);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			Users user = new Users();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			
			rs.close();
			stmt.close();
			return user;
			
		} else {
			rs.close();
			stmt.close();
			
			return null;
		}
	}
	
	public Users getUserByName(String name) throws SQLException {
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
		stmt.setString(1, name);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			Users user = new Users();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			
			rs.close();
			stmt.close();
			return user;
		} else {
			rs.close();
			stmt.close();
			
			return null;
		}
	}
	
	public Integer getUserIdByLogin(Users user) throws SQLException {
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE name = ? AND password = ?");
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getPassword());
		
		ResultSet rs = stmt.executeQuery();
		
		Integer userId = null;
		while(rs.next()) {
			userId = rs.getInt("id");
		}
		
		rs.close();
		stmt.close();
		
		return userId;
	}
	
	// ============================== POSTS ==============================
	
	public List<Posts> getPosts(Integer idUser) throws SQLException{
		
		List<Posts> posts = new ArrayList<Posts>();
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Posts WHERE idUser = ?");
		stmt.setInt(1, idUser);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Posts post = new Posts();
			post.setId(rs.getInt("id"));
			post.setName(rs.getString("name"));
			post.setText(rs.getString("text"));
			post.setIdUser(rs.getInt("idUser"));
			post.setData(rs.getString("data"));
			post.setHora(rs.getString("hora"));
			post.setShared(rs.getBoolean("shared"));
			posts.add(post);
		}
		
		rs.close();
		stmt.close();
		
		return posts;
	}
	
	public Posts getPostById(Integer postId) throws SQLException {
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Posts WHERE id = ?");
		stmt.setInt(1, postId);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			Posts post = new Posts();
			post.setId(rs.getInt("id"));
			post.setName(rs.getString("name"));
			post.setText(rs.getString("text"));
			post.setIdUser(rs.getInt("idUser"));	
			post.setData(rs.getString("data"));
			post.setHora(rs.getString("hora"));
			rs.close();
			stmt.close();
			return post;
			
		} else {
			rs.close();
			stmt.close();
			
			return null;
		}	
	}
	
	public void cadastrarNovoPost(Posts post) throws SQLException {
		String sql = "INSERT INTO Posts (name, text, idUser, data, hora, shared) VALUES (?,?,?,?,?,?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, post.getName());
		stmt.setString(2, post.getText());
		stmt.setInt(3, post.getIdUser());
		stmt.setString(4, post.getData());
		stmt.setString(5, post.getHora());
		stmt.setBoolean(6, post.getShared());
		stmt.execute();
		stmt.close();
	}
	
	public void deletePost(Integer id, Integer idUser) throws SQLException{

		PreparedStatement stmt = connection.prepareStatement("DELETE FROM Posts WHERE id = ? AND idUser = ?");
		stmt.setInt(1, id);
		stmt.setInt(2, idUser);
		stmt.execute();
		stmt.close();
	}
	
	public void editPosts(Integer id, String name, String text, Integer idUser, String data, String hora) throws SQLException{

		PreparedStatement stmt = connection.prepareStatement("UPDATE Posts SET name = ?, text = ?, data = ?, hora = ? WHERE id = ? AND idUser = ?");
		stmt.setString(1, name);
		stmt.setString(2, text);
		stmt.setString(3, data);
		stmt.setString(4, hora);
		stmt.setInt(5, id);
		stmt.setInt(6, idUser);
		stmt.execute();
		stmt.close();
	}
	
	// ============================== SHARED POSTS ==============================
	
	public void cadastrarNovoSharedPost(Posts post, String usersIds) throws SQLException {
		String sql = "INSERT INTO SharedPosts (postId, usersIds) VALUES (?,?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, post.getId());
		stmt.setString(2, usersIds);
		stmt.execute();
		stmt.close();
	}
	
	public void close() throws SQLException {
		connection.close();
	}

}

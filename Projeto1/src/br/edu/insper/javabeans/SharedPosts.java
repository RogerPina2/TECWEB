package br.edu.insper.javabeans;

public class SharedPosts {
	private Integer id;
	private Integer postId;
	private String usersIds;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getUsersIds() {
		return usersIds;
	}
	public void setUsersIds(String usersIds) {
		this.usersIds = usersIds;
	}
	
}

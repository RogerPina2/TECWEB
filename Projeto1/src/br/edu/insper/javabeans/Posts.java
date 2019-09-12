package br.edu.insper.javabeans;

public class Posts {
	private Integer id;
	private String name;
	private String text;
	private Integer idUser;
	private String data;
	private String hora;
	private Boolean shared;
	
	
	public Integer getId() { return this.id; }
	public void setId(Integer id) {this.id = id; }
	
	public String getName() { return this.name; }
	public void setName(String name) {this.name = name; }
	
	public String getText() { return this.text; }
	public void setText(String text) {this.text = text; }
	
	public Integer getIdUser() { return idUser; }
	public void setIdUser(Integer idUser) { this.idUser = idUser; }
	
	public String getData() { return data; }
	public void setData(String data) { this.data = data; }
	
	public String getHora() { return hora; }
	public void setHora(String hora) { this.hora = hora; }
	
	public Boolean getShared() { return shared; }
	public void setShared(Boolean shared) { this.shared = shared; }
	
}

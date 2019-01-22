package Shakti.HibernateApp.entities;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	
	private int id;
	private String name, password, salt;
	
	public User() {}
	
	public User(String n, String p, String s) {
		name = n;
		password = p;
		salt = s;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int i) {
		id = i;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pass) {
		password = pass;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String tlas) {
		salt = tlas;
	}

}

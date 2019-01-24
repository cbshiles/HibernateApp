package Shakti.HibernateApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User{
	
	private int id;
	private String name, pass;
	
	public User() {}
	
	public User(String n, String p) {
		name = n;
		pass = p;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId(){
		return id;
	}
	
	public void setId(int i) {
		id = i;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String p) {
		pass = p;
	}





}

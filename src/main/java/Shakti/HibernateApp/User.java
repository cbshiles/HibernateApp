package Shakti.HibernateApp;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	
	private int id;
	private String name;
	private Integer link;
	
	public User() {}
	
	public User(String n, Integer l) {
		name = n;
		link = l;
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
	
	public Integer getLink() {
		return link;
	}
	
	public void setLink(Integer uid) {
		link = uid;
	}

}

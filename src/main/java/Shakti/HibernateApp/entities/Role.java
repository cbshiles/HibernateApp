package Shakti.HibernateApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	private String id, description;
	
	public Role() {}
	
	public Role(String i, String d) {
		id = i;
		description = d;
	}
	
	@Id
	public String getId() {
		return id;
	}
	
	public void setId(String i) {
		id = i;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String d) {
		description = d;
	}

}

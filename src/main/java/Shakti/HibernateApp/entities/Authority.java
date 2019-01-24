package Shakti.HibernateApp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority{
	
	private static final long serialVersionUID = 2872444021517872036L;
	private String id, description;
	
	public Authority() {}
	
	public Authority(String i, String d) {
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

	@Transient
	@Override
	public String getAuthority() {
		return id;
	}

}

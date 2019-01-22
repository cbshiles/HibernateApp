package Shakti.HibernateApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stuff")
public class Stuff {
	
	private int id;
	private Integer n;
	
	public Stuff() {}
	
	public Stuff(Integer n) {
		this.n = n;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int i) {
		id = i;
	}
	
	public void setN(Integer n) {
		this.n = n;
	}
	
	public Integer getN() {
		return n;
	}

}

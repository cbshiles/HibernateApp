package Shakti.HibernateApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import Shakti.HibernateApp.entities.Stuff;

public interface StuffRepo extends Repo<Stuff, Integer>{

	@Modifying
	@Query("DELETE FROM Stuff") //HQL uses class names
	void truncate();
	
	public List<Stuff> findByN(Integer n);
}

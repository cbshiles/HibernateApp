package Shakti.HibernateApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import Shakti.HibernateApp.User;

public interface UserRepository extends Repo<User, Integer> {
	
	@Modifying
	@Query("DELETE FROM User")
	void truncate();
	
	List<User> findByLink(Integer link);
	

}
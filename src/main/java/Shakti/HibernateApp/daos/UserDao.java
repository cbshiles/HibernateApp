package Shakti.HibernateApp.daos;


import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Shakti.HibernateApp.User;
import Shakti.HibernateApp.repositories.UserRepository;

@Service
public class UserDao {
	
	@Autowired UserRepository repo;
	
	@Transactional
	public void createUser(String name, Integer link) {
		User u = new User(name, link);
		repo.save(u);
		//Session s = SessionService.getSession();
		//s.persist(u);
	}
	
	@Transactional(readOnly = true)
	public Optional<User> getUser(int id) {
		return repo.findById(id);
		//Session s = SessionService.getSession();
		//return s.get(User.class, id);
	}
	
	//@Transactional
	//@Query("DELETE FROM user")
	

}

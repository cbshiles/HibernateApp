package Shakti.HibernateApp.daos;


import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Shakti.HibernateApp.User;
import Shakti.HibernateApp.repositories.UserRepository;

@Service
public class UserDao extends Dao<User, Integer> {
	
	@Autowired UserRepository repo;

	@PostConstruct //occurs after auto-wiring
	private void setRepo() {
		setRepo(repo);
	}
	
	@Transactional
	public User createUser(String name, Integer link) {
		return save(new User(name, link));
	}

	@Transactional
	public void truncate() {
		repo.truncate();
	}
	
	@Transactional(readOnly = true)
	public List<User> getLinkedUsers(Integer linkId) {
		return repo.findByLink(linkId);
	}
	
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return repo.findAll();
	}
}

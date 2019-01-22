package Shakti.HibernateApp.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Shakti.HibernateApp.entities.Stuff;
import Shakti.HibernateApp.repositories.Repo;

public class Dao <V, K> {
	
	@Autowired
	protected Repo<V, K> repo;
	
	protected void setRepo(Repo<V, K> oper) {
		repo = oper;
	}
	
	@Transactional(readOnly = true)
	public Optional<V> find(K id) {
		return repo.findById(id);
	}
	
	@Transactional
	public V save(V o) {
		return repo.save(o);
	}
	
	@Transactional(readOnly = true)
	public List<V> getAllUsers() {
		return repo.findAll();
	}

}

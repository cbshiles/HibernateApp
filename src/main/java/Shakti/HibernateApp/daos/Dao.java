package Shakti.HibernateApp.daos;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import Shakti.HibernateApp.repositories.Repo;

public class Dao <V, K> {
	
	protected Repo<V, K> repo;
	
	protected void setRepo(Repo<V, K> oper) {
		repo = oper;
	}
	
	@Transactional
	public Optional<V> find(K id) {
		return repo.findById(id);
	}
	
	@Transactional(readOnly = true)
	public V save(V o) {
		return repo.save(o);
	}

}

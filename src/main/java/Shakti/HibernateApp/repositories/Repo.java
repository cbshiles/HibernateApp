package Shakti.HibernateApp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*
 * Base class for project's repositories.
 * 
 * Add methods here to define them for all repos.
 */
@NoRepositoryBean
public interface Repo <V, K> extends CrudRepository<V, K>{
	
	List<V> findAll();

}

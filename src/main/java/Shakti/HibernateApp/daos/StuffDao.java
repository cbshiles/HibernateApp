package Shakti.HibernateApp.daos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Shakti.HibernateApp.entities.Stuff;
import Shakti.HibernateApp.repositories.StuffRepo;

@Service
public class StuffDao extends Dao<Stuff, Integer> {

	@Transactional
	public Stuff createStuff(Integer n) {
		return save(new Stuff(n));
	}

	@Transactional
	public void truncate() {
		//use cast to access methods not defined in base class
		((StuffRepo)repo).truncate(); 
	}
	
	@Transactional(readOnly = true)
	public List<Stuff> getStuffN(Integer n) {
		return ((StuffRepo)repo).findByN(n);
	}
	
}

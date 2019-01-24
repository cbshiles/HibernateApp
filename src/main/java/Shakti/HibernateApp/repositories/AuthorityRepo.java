package Shakti.HibernateApp.repositories;

import java.util.List;
import java.util.Optional;

import Shakti.HibernateApp.entities.Authority;

public interface AuthorityRepo extends Repo<Authority, String>{

		public Optional<Authority> findById(String name);

		public List<Authority> findById(List<String> names);
}

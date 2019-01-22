package Shakti.HibernateApp.repositories;

import java.util.List;
import java.util.Optional;

import Shakti.HibernateApp.entities.Role;

public interface RoleRepo extends Repo<Role, String>{

		public Optional<Role> findById(String name);

		public List<Role> findById(List<String> names);
}

package Shakti.HibernateApp.repositories;

import java.util.List;

import Shakti.HibernateApp.entities.User;

public interface UserRepo extends Repo<User, Integer> {
	
	List<User> findByName(String name);

}
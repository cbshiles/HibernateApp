package Shakti.HibernateApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import Shakti.HibernateApp.entities.User;

public interface UserRepo extends Repo<User, Integer> {
	
	List<User> findByName(String name);

}
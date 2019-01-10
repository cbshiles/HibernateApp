package Shakti.HibernateApp.repositories;

import org.springframework.data.repository.CrudRepository;

import Shakti.HibernateApp.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
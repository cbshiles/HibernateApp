package Shakti.HibernateApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Shakti.HibernateApp.entities.Authority;
import Shakti.HibernateApp.entities.User;
import Shakti.HibernateApp.entities.UserToAuthority;
import Shakti.HibernateApp.repositories.AuthorityRepo;
import Shakti.HibernateApp.repositories.UserRepo;
import Shakti.HibernateApp.repositories.UserToAuthorityRepo;

@Service
public class LoginService {
	
	@Autowired UserRepo userRepo;
	@Autowired AuthorityRepo authRepo;
	@Autowired UserToAuthorityRepo userToRoleRepo;
	@Autowired PasswordEncoder pwEncoder;
	
	@Transactional
	public int createNewUser(String name, String pass) throws NamingException{
		List<Authority> auths = new ArrayList<>();
		auths.add(new Authority("USER", "whatevs"));
		return createNewUser(name, pass, auths);
	}
	
	@Transactional
	public int createNewUser(String name, String pass, List<Authority> auths) throws NamingException {
		if (userExists(name)) throw new NamingException("Requested username already exists!");
		int uid = userRepo.save(new User(name, pwEncoder.encode(pass))).getId();
		for (Authority a: auths) {
			userToRoleRepo.save(new UserToAuthority(uid, a.getId()));
		}
		return uid;
	}
	
	@Transactional(readOnly = true)
	public boolean userExists(String name) {
		List<User> userLzt = userRepo.findByName(name);
		return userLzt.size() > 0;
	}
	
	@Transactional(readOnly = true)
	public User findUser(String name) {
		List<User> userLzt = userRepo.findByName(name);
		return userLzt.size() > 0 ? userLzt.get(0) : null;
	}
	
	/*
	@Transactional(readOnly = true)
	public int login(String name, String pass) throws AuthenticationException{
		List<User> userLzt = userRepo.findByName(name);
		if (userLzt.size() == 0) throw new AuthenticationException("User not found.");
		if (userLzt.size() > 1) throw new AuthenticationException("Duplicate user.");
		
		User u = userLzt.get(0);	
		if (!pwEncoder.matches(pass, u.getPassword())) 
			throw new AuthenticationException("Could not authenticate user");
		return u.getId();	
	}
	*/
	
	@Transactional(readOnly = true)
	public List<Authority> getRolesForUser(int uid){
		List<UserToAuthority> utrLzt = userToRoleRepo.findById_UserId(uid);
		List<String> roleIds = utrLzt.stream().map(utr -> utr.getAuthorityId()).collect(Collectors.toList());
		return authRepo.findById(roleIds);
	}
	
	@Transactional
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}

}

package Shakti.HibernateApp.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Shakti.HibernateApp.entities.Role;
import Shakti.HibernateApp.entities.User;
import Shakti.HibernateApp.entities.UserToRole;
import Shakti.HibernateApp.repositories.RoleRepo;
import Shakti.HibernateApp.repositories.UserRepo;
import Shakti.HibernateApp.repositories.UserToRoleRepo;

@Service
public class LoginService {
	
	@Autowired UserRepo userRepo;
	@Autowired RoleRepo roleRepo;
	@Autowired UserToRoleRepo userToRoleRepo;
	@Autowired CryptService crypt;
	
	@Transactional
	public int createNewUser(String name, String pass, List<Role> roles) throws NamingException {
		if (userExists(name)) throw new NamingException("Requested username already exists!");
		String salt = crypt.getSalt();
		int uid = userRepo.save(new User(name, crypt.hashWord(pass, salt), salt)).getId();
		for (Role r: roles) {
			userToRoleRepo.save(new UserToRole(uid, r.getId()));
		}
		return uid;
	}
	
	@Transactional(readOnly = true)
	public boolean userExists(String name) {
		List<User> userLzt = userRepo.findByName(name);
		return userLzt.size() > 0;
	}
	
	@Transactional(readOnly = true)
	public int login(String name, String pass) throws AuthenticationException{
		List<User> userLzt = userRepo.findByName(name);
		if (userLzt.size() == 0) throw new AuthenticationException("User not found.");
		if (userLzt.size() > 1) throw new AuthenticationException("Duplicate user.");
		
		User u = userLzt.get(0);
		if (!u.getPassword().equals(pass)) 
			throw new AuthenticationException("Could not authenticate user");
		return u.getId();	
	}
	
	@Transactional(readOnly = true)
	public List<Role> getRolesForUser(int uid){
		List<UserToRole> utrLzt = userToRoleRepo.findById_UserId(uid);
		List<String> roleIds = utrLzt.stream().map(utr -> utr.getRoleId()).collect(Collectors.toList());
		return roleRepo.findById(roleIds);
	}

}

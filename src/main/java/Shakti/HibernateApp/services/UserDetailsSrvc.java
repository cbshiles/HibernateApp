package Shakti.HibernateApp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Shakti.HibernateApp.auth.UserDeets;
import Shakti.HibernateApp.entities.Authority;
import Shakti.HibernateApp.entities.User;
import Shakti.HibernateApp.entities.UserToAuthority;
import Shakti.HibernateApp.repositories.AuthorityRepo;
import Shakti.HibernateApp.repositories.UserRepo;
import Shakti.HibernateApp.repositories.UserToAuthorityRepo;

@Service
public class UserDetailsSrvc implements UserDetailsService{
	
	@Autowired UserRepo userRepo;
	@Autowired AuthorityRepo authRepo;
	@Autowired UserToAuthorityRepo userToRoleRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> userLzt = userRepo.findByName(username);
		if (userLzt.size() == 0) throw new UsernameNotFoundException("User not found~");
		User u = userLzt.get(0);
		List<Authority> auths = getRolesForUser(u.getId());
		return new UserDeets(u, auths);
	}
	
	@Transactional(readOnly = true)
	private List<Authority> getRolesForUser(int uid){
		List<UserToAuthority> utrLzt = userToRoleRepo.findById_UserId(uid);
		List<String> roleIds = utrLzt.stream().map(utr -> utr.getAuthorityId()).collect(Collectors.toList());
		return authRepo.findById(roleIds);
	}

}

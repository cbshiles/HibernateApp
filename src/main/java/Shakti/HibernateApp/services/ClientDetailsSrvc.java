package Shakti.HibernateApp.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Shakti.HibernateApp.Props;
import Shakti.HibernateApp.auth.ClientDeets;
import Shakti.HibernateApp.auth.UserDeets;
import Shakti.HibernateApp.entities.Authority;
import Shakti.HibernateApp.entities.User;
import Shakti.HibernateApp.entities.UserToAuthority;
import Shakti.HibernateApp.repositories.AuthorityRepo;
import Shakti.HibernateApp.repositories.UserRepo;
import Shakti.HibernateApp.repositories.UserToAuthorityRepo;

@Service
public class ClientDetailsSrvc implements ClientDetailsService{
	
	@Autowired UserRepo userRepo;
	@Autowired AuthorityRepo authRepo;
	@Autowired UserToAuthorityRepo userToRoleRepo;
	
    @Value("${app.clientId}")
    private String clientId;
 
    @Value("${app.clientSecret}")
    private String clientSecret;
    
    @Autowired
    private Props props;

	@Override
	public ClientDetails loadClientByClientId(String username) throws UsernameNotFoundException {
		List<User> userLzt = userRepo.findByName(username);
		if (userLzt.size() == 0) throw new UsernameNotFoundException("User not found~");
		User u = userLzt.get(0);
		List<GrantedAuthority> auths = new ArrayList<>();
		getRolesForUser(u.getId()).forEach(a -> auths.add(a));
		return new ClientDeets(clientId, clientSecret, auths, props.redirectUri());
	}
	
	@Transactional(readOnly = true)
	private List<Authority> getRolesForUser(int uid){
		List<UserToAuthority> utrLzt = userToRoleRepo.findById_UserId(uid);
		List<String> roleIds = utrLzt.stream().map(utr -> utr.getAuthorityId()).collect(Collectors.toList());
		return authRepo.findById(roleIds);
	}

}

package Shakti.HibernateApp.auth;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;

import Shakti.HibernateApp.entities.Authority;
import Shakti.HibernateApp.entities.User;

public class UserDeets implements UserDetails{
	
	private static final long serialVersionUID = 9171297864338664046L;
	private List<Authority> authorities;
	private User u;
	
	public UserDeets(User u, List<Authority> auths) {
		this.u = u;
		authorities = auths;
	}
	
	public UserDeets(Map<String, String> authInfo, OAuth2AccessToken accessToken) {
		//wat do?
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	@Column(name = "name")
	public String getUsername() {
		return u.getName();
	}

	@Override
	public String getPassword() {
		return u.getPass();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
		//		int cmp = accountExpiration.compareTo(LocalDateTime.now());
		//return cmp > 0;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

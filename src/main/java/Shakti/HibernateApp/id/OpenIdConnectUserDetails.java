package Shakti.HibernateApp.id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class OpenIdConnectUserDetails implements UserDetails {
	
	private static final long serialVersionUID = -4409011019721224330L;
	
	private String userId;
    private String username;
    private OAuth2AccessToken token;
    private LocalDateTime credentialExpiration, accountExpiration;
 
    public OpenIdConnectUserDetails(Map<String, String> userInfo, OAuth2AccessToken t) {
        userId = userInfo.get("sub");
        username = userInfo.get("email");
        token = t;
        
        String str = "2099-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        credentialExpiration = accountExpiration = LocalDateTime.parse(str, formatter);
    }
    
    public String getUserId() {
    	return userId;
    }
    
    public OAuth2AccessToken getToken() {
    	return token;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		int cmp = accountExpiration.compareTo(LocalDateTime.now());
		return cmp > 0;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		int cmp = credentialExpiration.compareTo(LocalDateTime.now());
		return cmp > 0;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

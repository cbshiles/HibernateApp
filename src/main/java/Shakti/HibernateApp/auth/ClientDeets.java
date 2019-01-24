package Shakti.HibernateApp.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

public class ClientDeets implements ClientDetails{

	private static final long serialVersionUID = 3392377928138399824L;

	private String clientId, secret;
	private Set<String> grantTypes, redirectUris;
	private List<GrantedAuthority> authorities;
	
	public ClientDeets(String clientId, String secret, List<GrantedAuthority> authorities, String redirectUri) {
		this.clientId = clientId;
		this.secret = secret;
		grantTypes = new HashSet<>(Arrays.asList("ADMIN", "USER"));
		this.authorities = authorities;
		redirectUris = new HashSet<>();
		redirectUris.add(redirectUri);
	}
	
	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public Set<String> getResourceIds() {
		return null;
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return secret;
	}

	@Override
	public boolean isScoped() {
		return false;
	}

	@Override
	public Set<String> getScope() {
		return null;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return grantTypes;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return redirectUris;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return 120;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return 60;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return null;
	}

}

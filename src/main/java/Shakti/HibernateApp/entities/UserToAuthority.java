package Shakti.HibernateApp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_to_authority")
public class UserToAuthority {
	
	@EmbeddedId
	UserToAuthorityId id;
	
	public UserToAuthority() {}
	
	public UserToAuthority(int u, String r) {
		id = new UserToAuthorityId(u, r);
	}
	
	public int getUserId() {
		return id.userId;
	}
	
	public void setUserId(int u) {
		id.userId = u;
	}

	public String getAuthorityId() {
		return id.authorityId;
	}
	
	public void setAuthorityId(String r) {
		id.authorityId = r;
	}
	
	@Embeddable
	public static class UserToAuthorityId implements Serializable {

		private static final long serialVersionUID = 4041938521460858618L;
		
		@Column(name = "user_id", nullable = false)
		protected int userId;
		
		@Column(name = "authority_id", nullable = false)
		protected String authorityId;
		
		public UserToAuthorityId() {}
		
		public UserToAuthorityId(int u, String r) {
			userId = u;
			authorityId = r;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == null) return false;
			if (o == this) return true;
			if (getClass() != o.getClass()) return false;
			UserToAuthorityId oid = (UserToAuthorityId)o;
			return userId == oid.userId && authorityId.equals(oid.authorityId);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result	+ (((Integer)userId).hashCode());
			result = prime * result	+ authorityId.hashCode();
			return result;
		}

	}
}

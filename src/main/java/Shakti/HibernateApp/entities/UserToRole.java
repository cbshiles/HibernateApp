package Shakti.HibernateApp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_to_role")
public class UserToRole {
	
	@EmbeddedId
	UserToRoleId id;
	
	public UserToRole() {}
	
	public UserToRole(int u, String r) {
		id = new UserToRoleId(u, r);
	}
	
	public int getUserId() {
		return id.userId;
	}
	
	public void setUserId(int u) {
		id.userId = u;
	}

	public String getRoleId() {
		return id.roleId;
	}
	
	public void setRoleId(String r) {
		id.roleId = r;
	}
	
	@Embeddable
	public static class UserToRoleId implements Serializable {

		private static final long serialVersionUID = 4041938521460858618L;
		
		@Column(name = "user_id", nullable = false)
		protected int userId;
		
		@Column(name = "role_id", nullable = false)
		protected String roleId;
		
		public UserToRoleId() {}
		
		public UserToRoleId(int u, String r) {
			userId = u;
			roleId = r;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == null) return false;
			if (o == this) return true;
			if (getClass() != o.getClass()) return false;
			UserToRoleId oid = (UserToRoleId)o;
			return userId == oid.userId && roleId.equals(oid.roleId);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result	+ (((Integer)userId).hashCode());
			result = prime * result	+ roleId.hashCode();
			return result;
		}

	}
}

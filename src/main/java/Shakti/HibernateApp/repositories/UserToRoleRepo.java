package Shakti.HibernateApp.repositories;

import java.util.List;

import Shakti.HibernateApp.entities.UserToRole;

public interface UserToRoleRepo extends Repo<UserToRole, UserToRole.UserToRoleId>{

	List<UserToRole> findById_UserId(int u);
	
	List<UserToRole> findById_RoleId(int r);
}

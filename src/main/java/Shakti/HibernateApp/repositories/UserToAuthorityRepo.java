package Shakti.HibernateApp.repositories;

import java.util.List;

import Shakti.HibernateApp.entities.UserToAuthority;

public interface UserToAuthorityRepo extends Repo<UserToAuthority, UserToAuthority.UserToAuthorityId>{

	List<UserToAuthority> findById_UserId(int u);
	
	List<UserToAuthority> findById_AuthorityId(int r);
}

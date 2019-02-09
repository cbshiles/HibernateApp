package Shakti.HibernateApp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import Shakti.HibernateApp.daos.StuffDao;
import Shakti.HibernateApp.entities.Stuff;
import Shakti.HibernateApp.repositories.AuthorityRepo;
import Shakti.HibernateApp.services.UserDetailsSrvc;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest extends TestCase {
	
	private static final Logger log = LoggerFactory.getLogger(AppTest.class);
	
	@Autowired 
	private StuffDao stuffDao;
	
	@Autowired UserDetailsSrvc loginService;

	@Autowired AuthorityRepo roleRepo;
	
	@Test
	public void createStuff() {
		Integer i=7;
		Stuff s = stuffDao.createStuff(i);
		log.debug("S id: "+s.getId());
	    assertThat(s.getN()).isEqualTo(i);
	}
	
	@Test
	public void clearTable() {
		stuffDao.createStuff(null);
		List<Stuff> lzt = stuffDao.getAllUsers();
		assertThat(lzt.size()).isGreaterThan(0);
		stuffDao.truncate();
		lzt = stuffDao.getAllUsers();
		assertThat(lzt.size()).isEqualTo(0);
	}

	/*
	@Test
	public void loginFail() {
		boolean err = false;
		try {
			loginService.login("beets", "123");
		}catch (AuthenticationException ex) {
			err = true;
		}
		assertTrue(err);
	}
	
	@Test
	public void loginSuccess() {
		String user = "max";
		String pass = "plank";
		
		User oldUser = loginService.findUser(user);
		if (oldUser != null) {
			loginService.deleteUser(oldUser.getId());
		}
		
		Optional<Role> role = roleRepo.findById("USER");
		assertTrue(role.isPresent());
		List<Role> lzt = new ArrayList<Role>();
		lzt.add(role.get());
		int id = -1;
		try {
			id = loginService.createNewUser(user, pass, lzt);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
		assertThat(id).isGreaterThan(0);
		
		boolean err = false;
		try {
			loginService.login(user, pass);
		}catch (AuthenticationException ex) {
			err = true;
		}
		assertFalse(err);
	}
	*/
}

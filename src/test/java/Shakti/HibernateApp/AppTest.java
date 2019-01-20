package Shakti.HibernateApp;

import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import Shakti.HibernateApp.daos.UserDao;
import Shakti.HibernateApp.entities.User;
import junit.framework.TestCase;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest extends TestCase {
	
	private static final Logger log = LoggerFactory.getLogger(AppTest.class);
	
	@Autowired 
	private UserDao userDao;
	
	@PostConstruct //occurs after auto-wiring
	public void clear() {
		userDao.truncate();
	}

	@Test
	public void createUser() {
		String name = "Sam";
		User u = userDao.createUser(name, null);
		log.debug("Sam id: "+u.getId());
	    assertThat(u.getName()).isEqualTo(name);
	}
	
	@Test
	public void createLinkedUser() {
		User sue = userDao.createUser("Susie", null);
		User bill = userDao.createUser("Bill", sue.getId());
		List<User> linked = userDao.getLinkedUsers(sue.getId());
		assertThat(linked.size()).isEqualTo(1);
		assertThat(linked.get(0).getId()).isEqualTo(bill.getId());
	}
	
	@Test
	public void clearTable() {
		userDao.createUser("Soup", null);
		List<User> lzt = userDao.getAllUsers();
		assertThat(lzt.size()).isGreaterThan(0);
		userDao.truncate();
		lzt = userDao.getAllUsers();
		assertThat(lzt.size()).isEqualTo(0);
	}
}

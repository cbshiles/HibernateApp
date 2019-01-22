package Shakti.HibernateApp;

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
import junit.framework.TestCase;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest extends TestCase {
	
	private static final Logger log = LoggerFactory.getLogger(AppTest.class);
	
	@Autowired 
	private StuffDao stuffDao;

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
}

package Shakti.HibernateApp;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import Shakti.HibernateApp.daos.UserDao;
import junit.framework.TestCase;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppTest extends TestCase {
	
	@Autowired UserDao userDao;
	
    @Autowired
    private MockMvc mvc;
	
	@PostConstruct //occurs after auto-wiring
	public void clear() {
		userDao.truncate();
	}

	@Test
	public void createUser() {
		String name = "Sam";
		User u = userDao.createUser(name, null);
		System.out.println("Sam id: "+u.getId());
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
	
    @Test 
    public void simpleMvcTest() throws Exception{
    	mvc.perform(get("/hello")
    	.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name", is("Bob")));
    }
}

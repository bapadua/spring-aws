package br.com.bapadua.aws.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repository;
	
	private static User defaultUser;
	
	@Before
	public void setUp() {		
		defaultUser = new User();
		defaultUser.setId(1L);
		defaultUser.setName("Test");
		defaultUser.setEmail("me@bapadua.com");
		defaultUser.setPassword("test123");
		defaultUser.setRole(Role.ADMIN);
	}

	@Test
	public void aSaveTest() {	
		User to = new User();
		to = repository.save(defaultUser);
		
		assertTrue(to.getId().equals(defaultUser.getId()));
		
	}
	
	@Test
	public void bUpdateTest() {
		defaultUser.setId(1L);
		defaultUser.setName("Update test");
		
		User update = repository.save(defaultUser);
		
		assertTrue(update.getName().equalsIgnoreCase("Update test"));
	}
	
	@Test
	public void cGetByIdTest() {
		Optional<User> result = repository.findById(1L);
		if(result.isPresent()) {
			User user = result.get();
			assertTrue(user.getName().equalsIgnoreCase("Update Test"));
		}
	}
	
	@Test
	public void dUserList() {
		List<User> users = repository.findAll();
		assertTrue(users.size() == 1);
	}
	
	@Test
	public void eLoginTest() {
		Optional<User> result = repository.login("me@bapadua.com", "test123");
		if(result.isPresent()) {
			User user = result.get();
			assertTrue(user.getId().longValue() == 1L);
		}
	}
	
	@Test
	public void fUpdateRole() {
		int updateRole = repository.updateRole(defaultUser.getId(), Role.SIMPLE);
		assertTrue(updateRole == 1);
	}
}

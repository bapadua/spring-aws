package br.com.bapadua.aws.repository;

import static org.junit.Assert.assertTrue;

import java.util.Date;
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

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.enums.RequestState;
import br.com.bapadua.aws.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTests {
	
	@Autowired
	private RequestRepository repository;
	
	@Autowired
	private UserRepository userRepo;
	
	private static User user;
	
	private static Request request;
	
	@Before
	public void setUp() {
		/**
		 * USUARIO PADRAO
		 */
		user = new User();
		user.setId(1L);
		user.setName("Test");
		user.setPassword("123");
		user.setEmail("me@bapadua.com");
		user.setRole(Role.SIMPLE);
		
		User defaultUser = userRepo.save(user);
		user = defaultUser;
		
		/**
		 * REQUEST PADRAO
		 */
		request = new Request(
				1L, 
				"Notebook", 
				"HP410 i5", 
				new Date(),
				RequestState.OPEN, 
				user, 
				null);
		
	}
	
	
	@Test
	public void aSaveTest() {
	
		
		Request save = repository.save(request);
		
		assertTrue(save.getId().longValue() == 1L);
	}
	
	@Test
	public void bUpdateTest() {
		request.setDescription("HP420 i5");
		Request save = repository.save(request);
		
		assertTrue(save.getDescription().equals(save.getDescription()));
		
	}
	
	@Test
	public void cGetByIdTest() {
		Optional<Request> result = repository.findById(1L);
		if(result.isPresent()) {
			Request req = result.get();
			
			assertTrue(req.getDescription().equals("HP420 i5"));
		}
	}
	
	@Test
	public void dListTest() {
		List<Request> findAll = repository.findAll();
		assertTrue(findAll.size() == 1);
	}
	
	@Test
	public void eUpdateStatusTest() {
		int affectedRows = repository.updateStatus(1L, RequestState.IN_PROGRESS);
		assertTrue(affectedRows == 1);
	}

}

package br.com.bapadua.aws.repository;

import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.enums.RequestState;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestRepositoryTests {
	
	@Autowired
	private RequestRepository repository;
	
	
	public void saveTest() {
		User owner = new User();
		owner.setId(1L);
		owner.setEmail("me@bapadua.com");
		
		Request request = new Request();
		request.setId(1L);
		request.setSubject("H2 20i");
		request.setDescription("Laptop HP i5");
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
	}
	
	public void updateTest() {}
	
	public void getByIdTest() {}
	
	public void listTest() {}

}

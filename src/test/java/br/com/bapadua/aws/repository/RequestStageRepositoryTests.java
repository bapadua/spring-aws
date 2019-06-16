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
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.enums.RequestState;
import br.com.bapadua.aws.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestStageRepositoryTests {
	
	@Autowired
	private RequestStageRepository repository;
	
	@Autowired
	private RequestRepository requestRep;
	
	private static RequestStage stage;
	
	private static Request request;
	
	@Autowired
	private UserRepository userRepo;
	
	private static User user;

	@Before
	public void setUp() {
		user = new User();
		user.setId(1L);
		user.setEmail("me@bapadua.com");
		user.setName("Test");
		user.setPassword("123");
		user.setRole(Role.SIMPLE);
		userRepo.save(user);
		
		request = new Request();
		request.setId(1L);
		request.setOwner(user);	
		request.setCreationDate(new Date());
		request.setState(RequestState.IN_PROGRESS);
		request.setSubject("Pagamento aceito");
		request.setDescription("Notebook HP420");		
		
		requestRep.save(request);	
		
	}
	
	@Test
	public void aSave() {
		stage = new RequestStage();
		stage.setRequest(request);
		stage.setRequestDate(new Date());
		stage.setDescription("Pronto para envio");
		stage.setOwner(user);
		stage.setState(request.getState());
		RequestStage save = repository.save(stage);
		
		assertTrue(save.getId() == 1L);
	}
	
	@Test
	public void bUpdate() {
		stage.setDescription("Produto enviado para a transportadora");
		RequestStage update = repository.save(stage);
		assertTrue(update.getDescription().equals("Produto enviado para a transportadora"));
	}
	
	@Test
	public void cGetById() {
		Optional<RequestStage> result = repository.findById(1L);
		if(result.isPresent()) {
			RequestStage rs = result.get();
			assertTrue(rs.getDescription().equals("Produto enviado para a transportadora"));
		}
	}
	
	@Test
	public void dListByRequest() {
		List<RequestStage> list = repository.findAllByRequestId(request.getId());
		assertTrue(list.size() == 1);
	}
}

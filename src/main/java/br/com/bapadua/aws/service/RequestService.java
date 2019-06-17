package br.com.bapadua.aws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.enums.RequestState;
import br.com.bapadua.aws.repository.RequestRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class RequestService {
	@Autowired
	private RequestRepository requestRepository;
	
	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		return requestRepository.save(request);
	}
	
	public Request getById(Long id) throws ObjectNotFoundException {
		Optional<Request> result = requestRepository.findById(id);
		if(result.isPresent()) {
			return result.get();			
		}
		throw new ObjectNotFoundException("Não encontrado");
	}
	
	public List<Request> findAll() {
		return requestRepository.findAll();
	}
	
	public List<Request> findByOwner(User owner) {
		 return requestRepository.findAllByOwnerId(owner.getId());
	}
}

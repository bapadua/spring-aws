package br.com.bapadua.aws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.enums.RequestState;
import br.com.bapadua.aws.exception.NotFoundException;
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

	public Request update(Request request) {
		//return requestRepository.updateStatus(request.getOwner().getId(), request.getState().ordinal());
		return null;
	}

	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Pedido não encontrado: " + id));
	}

	public List<Request> findAll() {
		return requestRepository.findAll();
		}

	public List<Request> findByOwner(Long id) {
		return requestRepository.findAllByOwnerId(id);
	}
}

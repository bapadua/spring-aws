package br.com.bapadua.aws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.enums.RequestState;
import br.com.bapadua.aws.exception.NotFoundException;
import br.com.bapadua.aws.pagemodel.PageModel;
import br.com.bapadua.aws.pagemodel.PageRequestModel;
import br.com.bapadua.aws.repository.RequestRepository;

@Service
public class RequestService {
	@Autowired
	private RequestRepository requestRepository;

	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());

		return requestRepository.save(request);
	}

	public int update(Request request) {
		return requestRepository.updateStatus(request.getOwner().getId(), request.getState());
	}

	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Pedido não encontrado: " + id));
	}

	public PageModel<Request> findAll(PageRequestModel pages) {
		Pageable pageable = PageRequest.of(pages.getPage(), pages.getSize());
		Page<Request> result = requestRepository.findAll(pageable);
		PageModel<Request> pm = new PageModel<Request>((int)result.getTotalElements(),
				result.getSize(), 
				result.getTotalPages(), 
				result.getContent());
		return pm;
	}
	
	public PageModel<Request> lazyList(Long id, PageRequestModel pages) {
		Pageable pageable = PageRequest.of(pages.getPage(), pages.getSize());
		Page<Request> result = requestRepository.findAllByOwnerId(id, pageable);
		PageModel<Request> pm = new PageModel<Request>((int)result.getTotalElements(), 
				result.getSize(), 
				result.getTotalPages(), 
				result.getContent());
		
		return pm;
	}

	public List<Request> findByOwner(Long id) {
		return requestRepository.findAllByOwnerId(id);
	}
}

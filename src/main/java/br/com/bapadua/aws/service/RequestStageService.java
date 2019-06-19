package br.com.bapadua.aws.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.domain.enums.RequestState;
import br.com.bapadua.aws.exception.NotFoundException;
import br.com.bapadua.aws.pagemodel.PageModel;
import br.com.bapadua.aws.pagemodel.PageRequestModel;
import br.com.bapadua.aws.repository.RequestRepository;
import br.com.bapadua.aws.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired
	private RequestStageRepository stageRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	
	public RequestStage save(RequestStage stage) {
		stage.setRequestDate(new Date());
		
		RequestState state = stage.getState();
		requestRepository.updateStatus(
				stage
				.getRequest()
				.getId(), 
				state);
		
		return stageRepository.save(stage);
	}
	
	public RequestStage getById(Long id) {
		Optional<RequestStage> result = stageRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Estado da requisição não encontrado" + id));
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
	
	public PageModel<RequestStage> byRequestList(Long id, PageRequestModel pages) {
		Pageable pageable = PageRequest.of(pages.getPage(), pages.getSize());
		Page<RequestStage> result = stageRepository.findByRequestStagesId(id, pageable);
		PageModel<RequestStage> pm = new PageModel<RequestStage>((int) result.getTotalElements(),
				result.getSize(), 
				result.getTotalPages(),
				result.getContent());
		
		return pm;
	}
}

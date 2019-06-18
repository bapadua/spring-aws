package br.com.bapadua.aws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.domain.enums.RequestState;
import br.com.bapadua.aws.exception.NotFoundException;
import br.com.bapadua.aws.repository.RequestRepository;
import br.com.bapadua.aws.repository.RequestStageRepository;
import javassist.tools.rmi.ObjectNotFoundException;

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
	
	public List<RequestStage> listAllByRequest(Request request) {
		Long id = request.getId();
		return stageRepository.findByRequestStagesId(id);
	}
}

package br.com.bapadua.aws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.domain.enums.RequestState;
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
	
	public RequestStage getById(Long id) throws ObjectNotFoundException {
		Optional<RequestStage> result = stageRepository.findById(id);
		if(result.isPresent()) {
			return result.get();			
		}
		throw new ObjectNotFoundException("Não foi possível realizar a consulta");
	}
	
	public List<RequestStage> listAllByRequest(Request request) {
		Long id = request.getId();
		return stageRepository.findAllByRequestId(id);
	}
}

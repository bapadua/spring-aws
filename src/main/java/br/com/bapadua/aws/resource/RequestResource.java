package br.com.bapadua.aws.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.repository.RequestStageRepository;
import br.com.bapadua.aws.service.RequestService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private RequestStageRepository stageRepository;

	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(requestService.save(request));
	}
	
	@PutMapping
	public ResponseEntity<Request> update(@RequestBody Request request) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(requestService.update(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(requestService.getById(id));
	}

	@GetMapping
	public ResponseEntity<List<Request>> getAll() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(requestService.findAll());
	}

	@GetMapping("/{id}/stages")
	public ResponseEntity<List<RequestStage>> listByRequestId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.accepted().body(stageRepository.findByRequestStagesId(id));
	}

}

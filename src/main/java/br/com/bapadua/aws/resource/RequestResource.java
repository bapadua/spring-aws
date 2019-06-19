package br.com.bapadua.aws.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.pagemodel.PageModel;
import br.com.bapadua.aws.pagemodel.PageRequestModel;
import br.com.bapadua.aws.service.RequestService;
import br.com.bapadua.aws.service.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private RequestStageService requestStageService;

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
	public ResponseEntity<PageModel<Request>> getAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> list = requestService.findAll(pr);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/{id}/stages")
	public ResponseEntity<PageModel<RequestStage>> listByRequestId(
			@PathVariable(name = "id", required = true) Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<RequestStage> list = requestStageService.byRequestList(id, pr);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}

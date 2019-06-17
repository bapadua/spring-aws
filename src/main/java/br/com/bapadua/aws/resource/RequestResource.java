package br.com.bapadua.aws.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.service.RequestService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

	@Autowired
	private RequestService requestService;

	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(requestService.save(request));
	}
}

package br.com.bapadua.aws.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bapadua.aws.domain.RequestStage;
import br.com.bapadua.aws.repository.RequestStageRepository;

@RestController
@RequestMapping("/request/stages")
public class RequestStageResource {

	@Autowired
	private RequestStageRepository stageRepository;

	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody RequestStage stage) {
		return ResponseEntity.status(HttpStatus.CREATED).body(stageRepository.save(stage));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id) {
		Optional<RequestStage> result = stageRepository.findById(id);
		if(result.isPresent()) {
			return ResponseEntity.ok().body(result.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}

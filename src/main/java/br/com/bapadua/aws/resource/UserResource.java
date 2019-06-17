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
import org.springframework.web.bind.annotation.RestController;

import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.dto.UserLoginDTO;
import br.com.bapadua.aws.service.UserService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User save = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user) {
		user.setId(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.save(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDTO login) {
		User user = userService.login(login);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
	}
}

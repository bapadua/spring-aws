package br.com.bapadua.aws.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.dto.UserLoginDTO;
import br.com.bapadua.aws.domain.dto.UserUpdateDTO;
import br.com.bapadua.aws.pagemodel.PageModel;
import br.com.bapadua.aws.pagemodel.PageRequestModel;
import br.com.bapadua.aws.service.RequestService;
import br.com.bapadua.aws.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private RequestService requestService;

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

	@GetMapping
	public ResponseEntity<PageModel<User>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> list = userService.lazyList(pr);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
	}

	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> getAllRequestsByUser(@PathVariable(name = "id") Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> list = requestService.lazyList(id, pr);

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody UserUpdateDTO role) {
		User user = new User();
		user.setId(id);
		user.setRole(role.getRole());
		userService.updateRole(user, role.getRole());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody @Valid UserLoginDTO login) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.login(login));
	}
}

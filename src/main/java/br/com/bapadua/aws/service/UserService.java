package br.com.bapadua.aws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.dto.UserLoginDTO;
import br.com.bapadua.aws.exception.NotAllowedException;
import br.com.bapadua.aws.exception.NotFoundException;
import br.com.bapadua.aws.repository.UserRepository;
import br.com.bapadua.aws.service.util.HashUtil;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		String secureHash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(secureHash);
		
		return userRepository.save(user);
	}
	
	public User getById(Long id) {
		Optional<User> result = userRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Usuário não encontrado: " + id));
	}
	
	public List<User> listAll() {
		return userRepository.findAll();
	}
	
	public User login(UserLoginDTO login) {
		String secureHash = HashUtil.getSecureHash(login.getPassword());
		login.setPassword(secureHash);
		
		Optional<User> result = userRepository.login(login.getEmail(), login.getPassword());
		return result.orElseThrow(()-> new NotAllowedException("Usuário/Senha inválidos"));
	}

}

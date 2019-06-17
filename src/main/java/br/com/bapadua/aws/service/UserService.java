package br.com.bapadua.aws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.UserLogin;
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
	
	public User getById(Long id) throws ObjectNotFoundException {
		Optional<User> result = userRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		throw new ObjectNotFoundException("Usuario não encontrato");
	}
	
	public List<User> listAll() {
		return userRepository.findAll();
	}
	
	public User login(UserLogin login) throws IllegalAccessError {
		String secureHash = HashUtil.getSecureHash(login.getPassword());
		login.setPassword(secureHash);
		
		Optional<User> result = userRepository.login(login.getEmail(), login.getPassword());
		if(result.isPresent()) {
			return result.get();
		}
		throw new IllegalAccessError("Credenciais inválidas");
	}

}

package br.com.bapadua.aws.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bapadua.aws.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM TB_USER u WHERE u.email = ?1 AND u.password = ?2")
	public Optional<User> login(String email, String password);
	

}

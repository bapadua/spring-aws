package br.com.bapadua.aws.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.bapadua.aws.domain.User;
import br.com.bapadua.aws.domain.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM TB_USER u WHERE u.email = ?1 AND u.password = ?2")
	public Optional<User> login(String email, String password);
	
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE TB_USER u SET u.role = ?2 WHERE id = ?1")
	public int updateRole(Long user, Role role);

}

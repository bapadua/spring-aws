package br.com.bapadua.aws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bapadua.aws.domain.Request;
import br.com.bapadua.aws.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
	
	@Query("UPDATE TB_REQUEST r SET r.state = ?2 WHERE r.id = ?1")
	public Request updateStatus(Long id, RequestState state);

	public List<Request> findAllRequestsByOwnerId(Long id);

}

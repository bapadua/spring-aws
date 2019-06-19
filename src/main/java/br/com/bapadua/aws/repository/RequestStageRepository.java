package br.com.bapadua.aws.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bapadua.aws.domain.RequestStage;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {
	
	public List<RequestStage> findByRequestStagesId(Long id);
	
	public Page<RequestStage> findByRequestStagesId(Long id, Pageable pages);
	
}

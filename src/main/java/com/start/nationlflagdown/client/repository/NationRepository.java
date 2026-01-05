package com.start.nationlflagdown.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.nationlflagdown.client.domain.NationVO;

public interface NationRepository extends JpaRepository<NationVO, Long>, NationRepositoryCustom{
	
	

}

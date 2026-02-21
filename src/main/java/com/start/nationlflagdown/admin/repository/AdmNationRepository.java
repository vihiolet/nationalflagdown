package com.start.nationlflagdown.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.nationlflagdown.admin.domain.AdmNationVO;

public interface AdmNationRepository extends JpaRepository<AdmNationVO, Long>, AdmRepositoryCustom{
	
	boolean existsByNationCode(String nationCode);

}

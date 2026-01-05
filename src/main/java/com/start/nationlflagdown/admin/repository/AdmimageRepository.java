package com.start.nationlflagdown.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.nationlflagdown.admin.domain.AdmImageVO;

public interface AdmimageRepository extends JpaRepository<AdmImageVO, Long>{
	
	List<AdmImageVO> findByNationNationId(Long nationId);

}
